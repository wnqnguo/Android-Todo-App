package com.codepath.simpletodo.Models;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.codepath.simpletodo.Activities.CreateNewList;
import com.codepath.simpletodo.Activities.EditItemActivity;
import com.codepath.simpletodo.R;

/**
 * Created by wguo on 1/16/2016.
 */
public class ListChildViewHolder extends ChildViewHolder{
    public TextView mTaskName;
    public CheckBox mTaskComplete;
    public ListChildViewHolder(View itemView){
        super(itemView);
        mTaskName = (TextView)itemView.findViewById(R.id.child_list_item_name);
        mTaskComplete = (CheckBox)itemView.findViewById(R.id.child_list_item_check_box);
        mTaskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditItemActivity.class);

            }
        });
    }
}
