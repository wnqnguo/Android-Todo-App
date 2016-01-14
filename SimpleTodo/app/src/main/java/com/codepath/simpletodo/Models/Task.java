package com.codepath.simpletodo.Models;

/**
 * Created by wguo on 1/13/2016.
 */
public class Task {
    private int Id;
    private String Name;
    private String DueDate;
    private String PriorityLevel;
    private String Notes;
    private String List;
    private boolean Completed;
    public Task(int id, String Name) {
        this.Id = id;
        this.Name = Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getPriorityLevel() {
        return PriorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        PriorityLevel = priorityLevel;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getList() {
        return List;
    }

    public void setList(String list) {
        List = list;
    }

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean completed) {
        Completed = completed;
    }


}
