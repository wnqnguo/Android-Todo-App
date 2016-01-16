package com.codepath.simpletodo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wguo on 1/14/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //String to log
    private static final String LOGTAG = "TODO_DB";
    private static final int DATABASE_VERSION = 1;

    //name of database being created
    private static final String DATABASE_NAME = "todo.db";
    public static final String TASK_TABLE = "Task";
    public static final String TASK_ID = "TaskId";
    public static final String TASK_NAME = "TaskName";
    public static final String TASK_DUE_DATE = "DueDate";
    public static final String TASK_NOTES = "Notes";
    public static final String TASK_PRIORITY = "PriorityLevel";
    public static final String TASK_COMPLETED = "isCompleted";
    public static final String TASK_LIST_ID = "listId";
    public static final String LIST_TABLE = "List";
    public static final String LIST_ID = "ListId";
    public static final String LIST_NAME = "ListName";
    public static final String CREATE_TABLE_TASK = " create table "
            + TASK_TABLE + " ("
            + TASK_ID + " integer primary key autoincrement not null, "
            + TASK_LIST_ID + "integer," + " FOREIGN KEY (" +TASK_LIST_ID+ ") REFERENCES "+ LIST_TABLE +"(" +LIST_ID +")"
            + TASK_NAME + " text not null, "
            + TASK_DUE_DATE + " text not null, "
            + TASK_NOTES + " text not null, "
            + TASK_PRIORITY + " text not null, "
            + TASK_COMPLETED + " integer not null, " + ");";
    //database version
    public static final String CREATE_TABLE_LIST = " create table "
            + LIST_TABLE +" ("
            + LIST_ID + " integer primary key autoincrement not null, "
            + LIST_NAME + " text not null " + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL for creating the tables
        db.execSQL(CREATE_TABLE_TASK);
        Log.i(LOGTAG, "Table " + CREATE_TABLE_TASK + " has been created");

        db.execSQL(CREATE_TABLE_LIST);
        Log.i(LOGTAG, "Table " + CREATE_TABLE_LIST + " has been created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // SQL for upgrading the tables
        Log.i(LOGTAG, "Database was v" + oldVersion + ", upgrading to v" + newVersion);
        Log.i(LOGTAG, "Dropping tables...");
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE);
    }
}
