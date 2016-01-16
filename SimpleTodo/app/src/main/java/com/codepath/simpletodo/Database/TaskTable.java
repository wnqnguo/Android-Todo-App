package com.codepath.simpletodo.Database;

import android.provider.BaseColumns;

/**
 * Created by wguo on 1/15/2016.
 */
public class TaskTable {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "todo.db";
    public static final String TABLE = "tasks";

    public class TaskColumns {
        public static final String _ID = BaseColumns._ID;
        public static final String TaskName = "TaskName";
        public static final String Notes = "Notes";
        public static final String DueDate = "DueDate";

    }
}
