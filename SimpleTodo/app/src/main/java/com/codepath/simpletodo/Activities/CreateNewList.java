package com.codepath.simpletodo.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.simpletodo.Database.DataSource;
import com.codepath.simpletodo.Database.TodoDao;
import com.codepath.simpletodo.Models.List;
import com.codepath.simpletodo.Models.Task;
import com.codepath.simpletodo.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateNewList extends AppCompatActivity {

    private TextInputLayout inputLayoutTaskName;
    private EditText inputTaskName;
    private ArrayList<Task> tasks;
    ArrayAdapter<Task> tasksAdapter;
    ListView lvTasks;
    private final int REQUEST_CODE = 20;
    private Toolbar mToolbar;
    List testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        testList = new List(1,"Saturday");
        TodoDao tdDao = new TodoDao(CreateNewList.this);
        tdDao.saveList(testList);

        inputLayoutTaskName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputTaskName = (EditText) findViewById(R.id.input_name);
        inputTaskName.addTextChangedListener(new MyTextWatcher(inputTaskName));
        lvTasks = (ListView) findViewById(R.id.lvTasks);
        readItems();
        tasks = new ArrayList<Task>();
        tasksAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1,tasks);
        lvTasks.setAdapter(tasksAdapter);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_list, menu);
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

        return super.onOptionsItemSelected(item);
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

            }
        }
    }
    public void onAddTask(View v) {
      //  EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
      //  String itemText = etNewItem.getText().toString();
      //  tasksAdapter.add(itemText);
     //   etNewItem.setText("");
      //  etNewItem.setTag(tasksAdapter.getCount() - 1);
        Intent intent = new Intent(CreateNewList.this, EditItemActivity.class);
        /*intent.putExtra("item_name", name);
        intent.putExtra("item_pos", position);*/
        startActivityForResult(intent, REQUEST_CODE);
        writeItems();

    }

    private void setupListViewListener() {
        lvTasks.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        tasks.remove(position);
                        tasksAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                });
        lvTasks.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object obj = tasksAdapter.getItem(position);
                        //getView(position,view,parent);
                        String name = obj.toString();
                        Intent intent = new Intent(CreateNewList.this, EditItemActivity.class);
                        intent.putExtra("item_name", name);
                        intent.putExtra("item_pos", position);
                        startActivityForResult(intent, REQUEST_CODE);

                    }

                });
    }
    private void readItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        //read from db
        tasks = new ArrayList<Task>();

    }
    private void writeItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
       // write to DB

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
           // updateItemName(item_position, name);
        }
    }
   /* public void updateItemName(int position, String item_name) {

        TextView v = (TextView) lvTasks.getChildAt(position);
        v.setText(item_name);
        tasks.add(position + 1, item_name);
        tasks.remove(position);
        writeItems();
    }*/
}
