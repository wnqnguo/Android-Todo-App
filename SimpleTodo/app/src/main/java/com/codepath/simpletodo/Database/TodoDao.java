package com.codepath.simpletodo.Database;

import android.content.Context;
import android.database.Cursor;

import com.codepath.simpletodo.Models.List;
import com.codepath.simpletodo.Models.Task;

import java.util.ArrayList;

/**
 * Created by wguo on 1/14/2016.
 */
public class TodoDao {
    private DataSource todoDataSource;
    public  TodoDao(Context context) {
        todoDataSource = new DataSource(context);
    }
    /*public ArrayList<List> getAllLists(){
        ArrayList<List> Lists = new ArrayList<List>();
        Cursor listCursor = todoDataSource.getAllLists();
        if(listCursor != null){
            while(listCursor.moveToNext()){
                List temp = new List();
                temp.setId(Integer.parseInt());
            }
        }
    }*/
    public void saveList(List list){
        todoDataSource.open();
        long listId = todoDataSource.saveList(list);
        list.setId(listId);
        todoDataSource.close();

    }
    public void addTaskToList( Task task){
        todoDataSource.open();
        long taskId = todoDataSource.addTaskToList(task);
        task.setId(taskId);
        todoDataSource.close();
    }

}
