package com.codepath.simpletodo.Models;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.codepath.simpletodo.Activities.CreateNewList;
import com.codepath.simpletodo.Activities.EditItemActivity;
import com.codepath.simpletodo.Activities.ListFragment;
import com.codepath.simpletodo.Activities.MainActivity;
import com.codepath.simpletodo.Database.TodoDao;
import com.codepath.simpletodo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wguo on 1/16/2016.
 */
public class ListChildViewHolder extends ChildViewHolder{
    public TextView mTaskName;
    public CheckBox mTaskComplete;
    public TextView mTaskPriority;
    long mTaskId;
    long mTaskListId;
    TodoDao mDao;
    public ListChildViewHolder(View itemView){
        super(itemView);
        mTaskId = 0;
        mTaskListId = 0;
        mDao = new TodoDao(itemView.getContext());
       List<TaskList> mList = mDao.getAllLists();
        //ListExpandableAdaptor listExpandableAdapter = new ListExpandableAdaptor(itemView.getContext(),mList);

        //RecyclerView mListRecyclerView = (RecyclerView) itemView.findViewById(R.id.task_list_recycler_view);
        mTaskName = (TextView)itemView.findViewById(R.id.child_list_item_name);
        mTaskComplete = (CheckBox)itemView.findViewById(R.id.child_list_item_check_box);
        mTaskPriority = (TextView)itemView.findViewById(R.id.child_list_item_priority);
       // mRemove = (ImageButton)itemView.findViewById(R.id.child_list_item_remove);
        String priority = mTaskPriority.getText().toString();
        if(priority!=null){
            if(priority.equals("LOW")){
                mTaskPriority.setBackgroundResource(R.color.green);
            }else if(priority.equals("MEDIUM")){
                mTaskPriority.setBackgroundResource(R.color.yellow);
            }else if(priority.equals("HIGH")){
                mTaskPriority.setBackgroundResource(R.color.red);
            }else{

            }
        }
        mTaskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditItemActivity.class);

                intent.putExtra("taskListid", mTaskListId);
                intent.putExtra("taskId", mTaskId);
                v.getContext().startActivity(intent);
                //  v.getContext().startActivity(intent);


            }
        });
//        mRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               mDao.removeTaskFromList(mTaskId);
//               Intent intent = new Intent(v.getContext(), MainActivity.class);
//                intent.putExtra("taskListid", mTaskListId);
//              //  v.getContext().startActivity(intent);
//
//                new ListFragment();
//
//              //  listExpandableAdapter.notifyChildItemRemoved(parentPosition, childPosition);
//
//
//            }
//        });
    }

}
