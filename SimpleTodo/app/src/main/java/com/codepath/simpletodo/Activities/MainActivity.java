package com.codepath.simpletodo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import android.support.v4.app.Fragment;
import com.codepath.simpletodo.Models.Task;
import com.codepath.simpletodo.Models.TaskList;
import com.codepath.simpletodo.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends SingleFragmentActivity  {

    protected Fragment createFragment() {
        return new ListFragment();
    }




}
