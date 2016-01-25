package com.codepath.simpletodo.Models;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wguo on 1/13/2016.
 */
public class TaskList implements ParentListItem {
    private long Id;
    private String ListName;
    private List<Task> mChildrenList;

    public TaskList() {

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

    public void setListName(String name) {
         ListName = name;
    }




    @Override
    public List<Task> getChildItemList() {
        return mChildrenList;
    }


    public void setChildItemList(List<Task> list) {
        mChildrenList = list;
    }


    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

}
