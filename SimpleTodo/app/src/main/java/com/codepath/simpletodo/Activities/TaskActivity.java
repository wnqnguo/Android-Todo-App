package com.codepath.simpletodo.Activities;

/**
 * Created by wguo on 1/17/2016.
 */

import android.support.v4.app.Fragment;
public class TaskActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TaskFragment();
    }

}