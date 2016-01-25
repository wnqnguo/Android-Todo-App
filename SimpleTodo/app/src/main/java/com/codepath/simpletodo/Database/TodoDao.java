package com.codepath.simpletodo.Database;

import android.content.Context;
import android.database.Cursor;

import com.codepath.simpletodo.Models.TaskList;
import com.codepath.simpletodo.Models.Task;

import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private DataSource todoDataSource;

    public TodoDao(Context context) {
        todoDataSource = new DataSource(context);
    }

    public ArrayList<TaskList> getAllLists(){
        ArrayList<TaskList> Lists = new ArrayList<TaskList>();
        todoDataSource.open();
        Cursor listCursor = todoDataSource.getAllLists();
        if (listCursor != null) {

            while (listCursor.moveToNext()) {
                TaskList taskList = new TaskList();
                taskList.setId(listCursor.getInt(0));
                taskList.setListName(listCursor.getString(1));
                Cursor taskCursor = todoDataSource.getTaskByListId(listCursor.getLong(0));
                List<Task> tasks = new ArrayList<Task>();
                if (taskCursor != null) {

                    while (taskCursor.moveToNext()) {
                        Task task = new Task();
                        task.setId(taskCursor.getLong(0));
                        task.setListId(taskCursor.getLong(1));
                        task.setTaskName(taskCursor.getString(2));
                        task.setDueDate(taskCursor.getString(3));
                        task.setNotes(taskCursor.getString(4));
                        task.setPriorityLevel(taskCursor.getString(5));
                        task.setCompleted(taskCursor.getLong(6));
                        tasks.add(task);
                    }

                }
                taskList.setChildItemList(tasks);
                Lists.add(taskList);
            }


        }
        todoDataSource.close();
        return Lists;
    }

    public long saveList(TaskList list){
        todoDataSource.open();
        long listId = todoDataSource.saveList(list);
        list.setId(listId);
        todoDataSource.close();
        return listId;

    }
    public void addTaskToList( Task task){
        todoDataSource.open();
        long taskId = todoDataSource.addTaskToList(task);
        task.setId(taskId);
        todoDataSource.close();
    }
    public void removeTaskFromList(long taskId){
        todoDataSource.open();
        todoDataSource.removeTaskFromList(taskId);
        todoDataSource.close();
    }
    public Task getTaskById(long taskId){
        Task task = new Task();
        todoDataSource.open();
        Cursor taskCursor =  todoDataSource.getTaskByTaskId(taskId);
        if(taskCursor!=null){
            while(taskCursor.moveToNext()){
                task.setId(taskCursor.getLong(0));
                task.setListId(taskCursor.getLong(1));
                task.setTaskName(taskCursor.getString(2));
                task.setDueDate(taskCursor.getString(3));
                task.setNotes(taskCursor.getString(4));
                task.setPriorityLevel(taskCursor.getString(5));
                task.setCompleted(taskCursor.getLong(6));
            }

        }
        todoDataSource.close();
        return task;
    }

}
