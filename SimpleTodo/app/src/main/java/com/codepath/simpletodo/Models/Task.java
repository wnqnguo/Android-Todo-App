package com.codepath.simpletodo.Models;

/**
 * Created by wguo on 1/13/2016.
 */
public class Task {
    private long Id;
    private String TaskName;
    private String DueDate;
    private String PriorityLevel;
    private String Notes;
    private long ListId;
    private boolean Completed;
    public Task(int id, String TaskName) {
        this.Id = id;
        this.TaskName = TaskName;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String TaskName) {
        TaskName = TaskName;
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

    public long getListId() {
        return ListId;
    }

    public void setListId(int listId) {
        ListId = listId;
    }

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean completed) {
        Completed = completed;
    }

    public String ToString() {
        String item = "";
        item += "Id: " + this.Id +
                ", TaskName: " + this.TaskName +
                ", DueDate: " + this.DueDate +
                ", PriorityLevel: " + this.PriorityLevel +
                ", Notes: " + this.Notes +
                ", ListId:  " + this.ListId +
                ", Completed" + this.Completed+
                " "
        ;
        return item;
    }


}
