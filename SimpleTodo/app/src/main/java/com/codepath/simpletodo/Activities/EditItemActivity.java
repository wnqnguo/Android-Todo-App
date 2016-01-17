package com.codepath.simpletodo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.codepath.simpletodo.Database.TodoDao;
import com.codepath.simpletodo.Models.Task;
import com.codepath.simpletodo.R;

public class EditItemActivity extends AppCompatActivity {
    EditText mEditText;
    String mItemName;
    int mItemPosition;
    private final int REQUEST_CODE = 20;
    private Toolbar mToolbar;
    private Task testTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        mItemName = getIntent().getStringExtra("item_name");
        mItemPosition = getIntent().getIntExtra("item_pos", 0);
        mEditText = (EditText)findViewById(R.id.task_name);
        mEditText.setText(mItemName);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        testTask = new Task(1,"eat cereal");
        testTask.setDueDate("2/15/2016");
        testTask.setNotes("sdsdfsfs");
        testTask.setCompleted(0);
        testTask.setListId(1);
        TodoDao tdDao = new TodoDao(EditItemActivity.this);
        tdDao.addTaskToList(testTask);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // testTask = new
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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
    public void saveNewItem(View v){
        Intent i = new Intent(EditItemActivity.this, MainActivity.class);
        String name = mEditText.getText().toString();
        i.putExtra("edited_item_name", name); // pass arbitrary data to launched activity
        i.putExtra("edited_item_pos",  mItemPosition);
        setResult(RESULT_OK, i);
        finish();
    }
}
