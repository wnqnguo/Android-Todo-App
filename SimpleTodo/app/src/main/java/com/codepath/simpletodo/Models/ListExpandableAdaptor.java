package com.codepath.simpletodo.Models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.codepath.simpletodo.Activities.CreateNewList;
import com.codepath.simpletodo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wguo on 1/17/2016.
 */
public class ListExpandableAdaptor extends ExpandableRecyclerAdapter<ListParentViewHolder, ListChildViewHolder>{
    private LayoutInflater mInflater;
    public ListExpandableAdaptor(Context context, List<ParentListItem> list){
        super(list);
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public ListParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_parent, viewGroup, false);
        return new ListParentViewHolder(view);
    }
    @Override
    public ListChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_child, viewGroup, false);
        return new ListChildViewHolder(view);
    }
    @Override
    public void onBindParentViewHolder(ListParentViewHolder listParentViewHolder, int i, ParentListItem  parentListItem) {
        TaskList list = (TaskList) parentListItem;
        listParentViewHolder.mListName.setText(list.getListName());
        listParentViewHolder.setTaskListId(list.getId());
    }
    @Override
    public void onBindChildViewHolder(ListChildViewHolder listhildViewHolder, int i, Object childObject) {
        Task task = (Task) childObject;
        if(task!=null){
            if(task.getTaskName()!=null){
                listhildViewHolder.mTaskName.setText(task.getTaskName().toString());
            }
            if(task.isCompleted()==0){
                listhildViewHolder.mTaskComplete.setChecked(false);
            }
            if(task.isCompleted()==1){
                listhildViewHolder.mTaskComplete.setChecked(true);
            }
        }else{
            // listhildViewHolder.mTaskName.setText("");
        }

    }
}
