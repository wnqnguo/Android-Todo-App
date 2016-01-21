package com.codepath.simpletodo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codepath.simpletodo.Models.TaskList;
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
    public long saveList(TaskList list){
        ContentValues values = new ContentValues();
        values.clear();
        values.put(DatabaseHelper.LIST_NAME, list.getListName());
        long listId =  database.insert(DatabaseHelper.LIST_TABLE, null, values);
        return listId;
    }
    public long addTaskToList(Task task){
        ContentValues values = new ContentValues();
        values.clear();
        values.put(DatabaseHelper.TASK_NAME, task.getTaskName());
        values.put(DatabaseHelper.TASK_LIST_ID, task.getListId());
        values.put(DatabaseHelper.TASK_DUE_DATE, task.getDueDate());
        values.put(DatabaseHelper.TASK_NOTES, task.getNotes());
        values.put(DatabaseHelper.TASK_PRIORITY, task.getPriorityLevel());
        values.put(DatabaseHelper.TASK_COMPLETED, task.isCompleted());
        long taskId = database.insert(DatabaseHelper.TASK_TABLE, null, values);
        return taskId;
    }

    public Cursor getAllLists(){
        String query = "SELECT * FROM " + DatabaseHelper.LIST_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
    public Cursor getAllItems(){
        String query = "SELECT * FROM " + DatabaseHelper.TASK_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
    public Cursor getTaskById(long listId){
        String query = "SELECT * FROM " + DatabaseHelper.TASK_TABLE +" Where listId = " + listId;
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }




}
