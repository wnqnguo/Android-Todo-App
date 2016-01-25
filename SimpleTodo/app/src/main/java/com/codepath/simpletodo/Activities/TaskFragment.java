package com.codepath.simpletodo.Activities;

/**
 * Created by wguo on 1/17/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.codepath.simpletodo.Models.Task;
import com.codepath.simpletodo.R;

import java.util.List;

public class TaskFragment extends Fragment {

    private Task mTask;
    private EditText mTaskName;
    private CheckBox mCompletedCheckBox;
    private RecyclerView mListRecyclerView;
    private ImageButton remove;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mTask = new Task(1,"test");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);

       mTaskName = (EditText) v.findViewById(R.id.task_name);
        mListRecyclerView = (RecyclerView) v.findViewById(R.id.task_list_recycler_view);
       // remove = (ImageButton) v.findViewById(R.id.child_list_item_remove);
        mTaskName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setTaskName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mCompletedCheckBox = (CheckBox) v.findViewById(R.id.task_completed);
        /*
        mCompletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTask.setCompleted(1);
            }
        });*/

        return v;
    }
}