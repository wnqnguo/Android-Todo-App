package com.codepath.simpletodo.Models;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wguo on 1/17/2016.
 */
public class Helper {
    public static List<ParentListItem> getgeneratedLists(){
        List<ParentListItem> parentObjects = new ArrayList<>();
        List<TaskList> list = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            TaskList temp = new TaskList();
            List<Task> tasks = new ArrayList<>();
            for(int j = 0; j < 5; j++){
                tasks.add(new Task());
            }
            temp.setChildItemList(tasks);
            list.add(temp);
            parentObjects.add(temp);
        }
        return parentObjects;
    }
}
