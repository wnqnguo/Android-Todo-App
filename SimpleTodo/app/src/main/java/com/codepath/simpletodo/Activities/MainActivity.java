package com.codepath.simpletodo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.codepath.simpletodo.Models.List;
import com.codepath.simpletodo.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> listsAdapter;
    ListView lvLists;
    private final int REQUEST_CODE = 20;
    private Toolbar mToolbar;
    ArrayList<List> mLists;
    ArrayAdapter<List> mListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvLists = (ListView) findViewById(R.id.lvItems);
        readItems();
        listsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvLists.setAdapter(listsAdapter);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setupListViewListener();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_add){
            Intent intent = new Intent(this, CreateNewList.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        listsAdapter.add(itemText);
        etNewItem.setText("");
        etNewItem.setTag(listsAdapter.getCount() - 1);
        writeItems();

    }

    private void setupListViewListener() {
        lvLists.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        items.remove(position);
                        listsAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                });
        lvLists.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object obj = listsAdapter.getItem(position);
                        //getView(position,view,parent);
                        String name = obj.toString();
                        Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                        intent.putExtra("item_name", name);
                        intent.putExtra("item_pos", position);
                        startActivityForResult(intent, REQUEST_CODE);

                    }

                });
    }
    private void readItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
           items = new ArrayList<String>(FileUtils.readLines(todoFile));
        }catch(IOException e){
           items = new ArrayList<String>();
        }

    }
    private void writeItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile, items);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String name = data.getExtras().getString("edited_item_name");
            int item_position = data.getExtras().getInt("edited_item_pos");
            int code = data.getExtras().getInt("code", 0);
            // Toast the name to display temporarily on screen
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            updateItemName(item_position, name);
        }
    }
    public void updateItemName(int position, String item_name) {

        TextView v = (TextView) lvLists.getChildAt(position);
         v.setText(item_name);
         items.add(position + 1, item_name);
         items.remove(position);
         writeItems();
    }

}
