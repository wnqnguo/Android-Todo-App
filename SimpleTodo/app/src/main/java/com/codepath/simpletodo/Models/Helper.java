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
            TaskList temp = new TaskList(1,"movies");
            List<Task> tasks = new ArrayList<>();
            for(int j = 0; j < 5; j++){
                tasks.add(new Task(1,"star wars"));
            }
            temp.setChildItemList(tasks);
            list.add(temp);
            parentObjects.add(temp);
        }
        return parentObjects;
    }
}
