package com.codepath.simpletodo.Models;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.codepath.simpletodo.Activities.CreateNewList;
import com.codepath.simpletodo.Database.TodoDao;
import com.codepath.simpletodo.R;

/**
 * Created by wguo on 1/16/2016.
 */
public class ListParentViewHolder extends ParentViewHolder {
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private static final boolean HONEYCOMB_AND_ABOVE = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    public ImageButton mListParentRemove;
    public TextView mListName;
    public ImageButton mParentDropDownArrow ;
    private TodoDao todoDao;
    private TaskList mList;
    public long taskListId;
    public ListParentViewHolder(View itemView){
        super(itemView);
        mListParentRemove = (ImageButton) itemView.findViewById(R.id.parent_list_item_remove);
        mListName = (TextView) itemView.findViewById(R.id.parent_list_item_name);
        mParentDropDownArrow  = (ImageButton) itemView.findViewById(R.id.parent_list_item_expand);
        todoDao = new TodoDao(itemView.getContext());
        mParentDropDownArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mParentDropDownArrow.getRotation()==INITIAL_POSITION){
                    expandView();
                }else{
                    collapseView();
                }
            }
        });

        mListName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(v.getContext(), CreateNewList.class);
                intent.putExtra("taskListId",(long)taskListId);
                v.getContext().startActivity(intent);

            }
        });




    }
    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (!HONEYCOMB_AND_ABOVE) {
            return;
        }

        if (expanded) {
            mParentDropDownArrow.setRotation(ROTATED_POSITION);
        } else {
            mParentDropDownArrow.setRotation(INITIAL_POSITION);
        }
    }
    @Override
    public boolean shouldItemViewClickToggleExpansion(){
        return false;
    }

    public void setTaskListId(long id){
        taskListId = id;
    }


}

