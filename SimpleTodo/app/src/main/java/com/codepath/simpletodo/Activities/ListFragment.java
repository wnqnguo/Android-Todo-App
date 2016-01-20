package com.codepath.simpletodo.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.codepath.simpletodo.Models.Helper;
import com.codepath.simpletodo.Models.ListExpandableAdaptor;
import com.codepath.simpletodo.R;

import java.util.List;

/**
 * Created by wguo on 1/17/2016.
 */
public class ListFragment extends Fragment {
    private RecyclerView mListRecyclerView;
    private List mLists;
    private Toolbar mToolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        mListRecyclerView = (RecyclerView) view.findViewById(R.id.task_list_recycler_view);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
       // ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       mLists = Helper.getgeneratedLists();
        ListExpandableAdaptor listExpandableAdapter = new ListExpandableAdaptor(getActivity(),mLists);





        listExpandableAdapter.onRestoreInstanceState(savedInstanceState);
        mListRecyclerView.setAdapter(listExpandableAdapter);
        return view;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((ListExpandableAdaptor) mListRecyclerView.getAdapter()).onSaveInstanceState(outState);
    }

}
