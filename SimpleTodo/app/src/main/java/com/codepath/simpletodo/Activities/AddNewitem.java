package com.codepath.simpletodo.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.codepath.simpletodo.Database.TodoDao;
import com.codepath.simpletodo.Models.Task;
import com.codepath.simpletodo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddNewitem extends AppCompatActivity {

    int mItemPosition;
    private final int REQUEST_CODE = 20;
    private Toolbar mToolbar;
    private Task testTask;
    private ImageButton mSave;
    private TodoDao todoDao;
    private String mTaskName;
    private String mDueDate;
    private String mPriorityLevel;
    private String mNotes;
    private long mCompleted;
    private EditText edTaskName;
    private EditText edDueDate;
    private EditText edPriorityLevel;
    private EditText edNotes;
    private EditText edCompleted;
    private long taskList_id;
    String mItemName;
    private Spinner mStatus, mPriority;
    List Status,Priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
       // mToolbar = findViewById(R.id.toolbar);
      //  mToolbar.setTitle("SimpleTodo");
        Status = new ArrayList();
        Priority = new ArrayList();
        Intent intent = getIntent();
        taskList_id = intent.getLongExtra("taskListId", 2);
        mItemName = getIntent().getStringExtra("item_name");
        mItemPosition = getIntent().getIntExtra("item_pos", 0);
        todoDao = new TodoDao(this);
        edTaskName = (EditText)findViewById(R.id.task_name);
        // edDueDate = (EditText)findViewById(R.id.task_due_date);
        mSave = (ImageButton)findViewById(R.id.save);
        edNotes = (EditText)findViewById(R.id.task_notes);
        edDueDate = (EditText)findViewById(R.id.task_due_date);
        mStatus = (Spinner)findViewById(R.id.task_status);
        mPriority = (Spinner)findViewById(R.id.task_priority_level);
        Status.add("TO-DO");
        Status.add("DONE");

        Priority.add("LOW");
        Priority.add("MEDIUM");
        Priority.add("HIGH");
        mPriority.setSelection(0);
        mStatus.setSelection(0);
        ArrayAdapter statusAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Status);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatus.setAdapter(statusAdapter);

        ArrayAdapter priorityAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Priority);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPriority.setAdapter(priorityAdapter);

        edDueDate.setOnClickListener(new View.OnClickListener() {
            final Calendar cal = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(v.getContext(),
                        datePickerListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(false);
                datePicker.setTitle("Select the date");
                datePicker.show();
            }
        });
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewItem();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_item, menu);
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
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);

            edDueDate.setText(day1 + "/" + month1 + "/" + year1);

        }
    };
    public void NameDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Please enter a task name");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();

            }
        });


        AlertDialog alertDialog1 = alertDialogBuilder.create();
        alertDialog1.show();
    }
    public void saveNewItem(){
        Task mTask = new Task();
        String temp = edTaskName.getText().toString();
        mTask.setTaskName(temp);
        mTask.setDueDate(edDueDate.getText().toString());
        mTask.setPriorityLevel(String.valueOf(mPriority.getSelectedItem()));
        mTask.setNotes(edNotes.getText().toString());
        mTask.setListId(taskList_id);
        mTask.setCompleted(String.valueOf(mStatus.getSelectedItem()));
        if(temp == null||temp.isEmpty()){
            NameDialog();
        }else{
            todoDao.addTaskToList(mTask);
            finish();
        }
        //mTask.setCompleted(edTaskName.getText().toString());

    }
}
