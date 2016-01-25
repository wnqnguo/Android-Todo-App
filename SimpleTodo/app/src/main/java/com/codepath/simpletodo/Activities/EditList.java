package com.codepath.simpletodo.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.simpletodo.Database.TodoDao;
import com.codepath.simpletodo.Models.TaskList;
import com.codepath.simpletodo.Models.Task;
import com.codepath.simpletodo.R;

import java.io.File;
import java.util.ArrayList;

public class EditList extends AppCompatActivity {

    private TextInputLayout inputLayoutTaskName;
    private EditText inputTaskName;
    private ArrayList<Task> tasks;
    ArrayAdapter<Task> tasksAdapter;
    ListView lvTasks;
    private final int REQUEST_CODE = 20;
    private Toolbar mToolbar;
    TaskList mtaskList;
    private ImageButton mAdd;
    private ImageButton mSave;
    private String mTaskListName;
    private long taskList_id;
    private ArrayList<TaskList> mList;
    private TodoDao todoDao;
    private ImageButton deleteTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_list);
        mToolbar.setTitle("SimpleTodo");
        deleteTask = (ImageButton)findViewById(R.id.task_delete);
        inputTaskName = (EditText)findViewById(R.id.list_name);
        Intent intent = getIntent();
        taskList_id = intent.getLongExtra("taskListId", 0);
        mTaskListName = intent.getStringExtra("taskListName");
        inputTaskName.setText(mTaskListName);
        todoDao = new TodoDao(this);
        mList = todoDao.getAllLists();
        mtaskList = new TaskList();
        inputLayoutTaskName = (TextInputLayout) findViewById(R.id.input_layout_name);

        inputTaskName.addTextChangedListener(new MyTextWatcher(inputTaskName));
        lvTasks = (ListView) findViewById(R.id.lvTasks);
        todoDao = new TodoDao(this);

        tasks = new ArrayList<Task>();
        tasksAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1,tasks);
        lvTasks.setAdapter(tasksAdapter);

        mAdd = (ImageButton)findViewById(R.id.add);
        mSave = (ImageButton)findViewById(R.id.save);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTask();
            }
        });
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = inputTaskName.getText().toString();
                if(temp==null||temp.isEmpty()){
                    NameWarning();
                }else{
                    updateList();
                    restartList();
                    finish();
                }

            }
        });
        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarningDialog();


            }
        });

      //  setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_list, menu);
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
    public void addNewTask() {
      //  EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
      //  String itemText = etNewItem.getText().toString();
      //  tasksAdapter.add(itemText);
     //   etNewItem.setText("");
      //  etNewItem.setTag(tasksAdapter.getCount() - 1);
        long temp =0;
        if(taskList_id==0) {

            mTaskListName = inputTaskName.getText().toString();
            mtaskList.setListName(mTaskListName);
            taskList_id = todoDao.saveList(mtaskList);
        }
        Intent intent = new Intent(EditList.this, AddNewitem.class);
        intent.putExtra("taskListId", (long) taskList_id);
        startActivity(intent);
    }


    public void updateList(){
        if(taskList_id==0){
            mTaskListName = inputTaskName.getText().toString();
            mtaskList.setListName(mTaskListName);
            taskList_id = todoDao.saveList(mtaskList);
        }else{
            mTaskListName = inputTaskName.getText().toString();
            mtaskList.setListName(mTaskListName);
            todoDao.upDateListById(taskList_id, mTaskListName);
        }

        //update taskName here
        finish();
    }
    public void WarningDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this list and all its associated to-do items?");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                todoDao.deleteList(taskList_id);

                restartList();
                arg0.dismiss();
                finish();


            }
        });
        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();

            }
        });


        AlertDialog alertDialog1 = alertDialogBuilder.create();
        alertDialog1.show();
    }

    public void NameWarning(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Please enter a valid list name");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                arg0.dismiss();

            }
        });


        AlertDialog alertDialog1 = alertDialogBuilder.create();
        alertDialog1.show();
    }
    public void restartList(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//
    }
}
