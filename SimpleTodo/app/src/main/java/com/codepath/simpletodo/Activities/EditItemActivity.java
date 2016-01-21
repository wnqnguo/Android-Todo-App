package com.codepath.simpletodo.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.Calendar;

import com.codepath.simpletodo.Database.TodoDao;
import com.codepath.simpletodo.Models.Task;
import com.codepath.simpletodo.R;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity {

    String mItemName;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent = getIntent();
        taskList_id = intent.getLongExtra("taskListId",0);
        mItemName = getIntent().getStringExtra("item_name");
        mItemPosition = getIntent().getIntExtra("item_pos", 0);
        todoDao = new TodoDao(this);
        edTaskName = (EditText)findViewById(R.id.task_name);
       // edDueDate = (EditText)findViewById(R.id.task_due_date);
        edPriorityLevel = (EditText)findViewById(R.id.task_priority_level);
        edNotes = (EditText)findViewById(R.id.task_notes);
        edDueDate = (EditText)findViewById(R.id.task_due_date);

       // edCompleted = (EditText)findViewById(R.id.task_completed);

       /* int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);*/
        //showDate(year, month+1, day);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSave = (ImageButton) findViewById(R.id.save);
        TodoDao tdDao = new TodoDao(EditItemActivity.this);

        setSupportActionBar(mToolbar);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        // testTask = new
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edDueDate.setOnClickListener(new View.OnClickListener() {
            final Calendar cal =  Calendar.getInstance();
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
        Task mTask = new Task();
        mTask.setTaskName(edTaskName.getText().toString());
        mTask.setDueDate(edDueDate.getText().toString());
        mTask.setPriorityLevel(edPriorityLevel.getText().toString());
        mTask.setNotes(edNotes.getText().toString());
        mTask.setListId(taskList_id);
        todoDao.addTaskToList(mTask);
        //mTask.setCompleted(edTaskName.getText().toString());
        finish();
    }
    private void showDate(int year, int month, int day) {
        edDueDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    // Listener
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
}
