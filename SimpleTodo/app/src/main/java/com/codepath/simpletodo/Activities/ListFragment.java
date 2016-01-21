package com.codepath.simpletodo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.codepath.simpletodo.Database.TodoDao;
import com.codepath.simpletodo.Models.Helper;
import com.codepath.simpletodo.Models.ListExpandableAdaptor;
import com.codepath.simpletodo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wguo on 1/17/2016.
 */
public class ListFragment extends Fragment {
    private RecyclerView mListRecyclerView;
    private List mLists;
    private Toolbar mToolbar;
    private ImageButton mAdd;
    private ImageButton mSave;
    private TodoDao todoDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        Log.e("here","called ron create");
        mListRecyclerView = (RecyclerView) view.findViewById(R.id.task_list_recycler_view);
        todoDao = new TodoDao(view.getContext());
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLists = todoDao.getAllLists();
        if(mLists.size()>0){
            ListExpandableAdaptor listExpandableAdapter = new ListExpandableAdaptor(getActivity(),mLists);
            listExpandableAdapter.onRestoreInstanceState(savedInstanceState);
            mListRecyclerView.setAdapter(listExpandableAdapter);
        }
        mAdd = (ImageButton) view.findViewById(R.id.add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewList();
            }
        });
        return view;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mLists.size() > 0){
            ((ListExpandableAdaptor) mListRecyclerView.getAdapter()).onSaveInstanceState(outState);
        }

    }
    public void addNewList(){
       Intent intent = new Intent(getActivity(), CreateNewList.class);
        startActivity(intent);
    }
    @Override
    public void onViewStateRestored(Bundle inState) {
        super.onViewStateRestored(inState);
        Log.e("here", "called restored");
        if(inState!=null) {
           Log.e("here","called restored");
        }

    }
    @Override
    public void onResume(){
        super.onResume();
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLists = todoDao.getAllLists();
        if(mLists.size()>0){
            ListExpandableAdaptor listExpandableAdapter = new ListExpandableAdaptor(getActivity(),mLists);
           // listExpandableAdapter.onRestoreInstanceState(savedInstanceState);
            mListRecyclerView.setAdapter(listExpandableAdapter);
        }
        Log.e("here","called onsume");
    }


}
