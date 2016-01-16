package com.codepath.simpletodo.Models;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by wguo on 1/13/2016.
 */
public class List {
    private long Id;
    private String ListName;

    public List(int id, String ListName) {
        this.Id = id;
        this.ListName = ListName;
    }

    public ArrayList<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        Tasks = tasks;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getListName() {
        return ListName;
    }

    public void ListName(String name) {
        ListName = name;
    }

    private ArrayList<Task> Tasks;


}
