package com.codepath.simpletodo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codepath.simpletodo.Models.List;
import com.codepath.simpletodo.Models.Task;

/**
 * Created by wguo on 1/15/2016.
 */
public class DataSource {
    public static final String LOGTAG="TODO_DB";
    private SQLiteOpenHelper dbhelper;
    private SQLiteDatabase database;

    public DataSource(Context context){
        dbhelper = new DatabaseHelper(context);
    }

    public void open(){
        Log.i(LOGTAG, "Database opened");
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database closed");
        dbhelper.close();
    }
    public void saveList(List list){
        ContentValues values = new ContentValues();
        values.clear();
        values.put(DatabaseHelper.L_NAME, task.getTaskName());
    }
    public void addTaskToList(Task task){
        ContentValues values = new ContentValues();
        values.clear();
        values.put(DatabaseHelper.TASK_NAME, task.getTaskName());
        values.put(DatabaseHelper.TASK_LIST_ID, task.getListId());
        values.put(DatabaseHelper.TASK_DUE_DATE, task.getDueDate());
        values.put(DatabaseHelper.TASK_NOTES, task.getNotes());
        values.put(DatabaseHelper.TASK_PRIORITY, task.getPriorityLevel()));
        values.put(DatabaseHelper.TASK_COMPLETED, task.isCompleted());

        database.insertWithOnConflict(DatabaseHelper.TASK_TABLE, null, values,SQLiteDatabase.CONFLICT_IGNORE);
    }


}
