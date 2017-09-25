package com.example.projectmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectmodule.base.BaseActivity;
import com.example.projectmodule.testactivity.SamplePagerActivity;
import com.example.projectmodule.utils.MyApplication;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {


    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home,R.string.home_title,MODE_NONE,R.menu.toolbar_meun);
    }

    @Override
    protected void setUpData() {
        MyApplication.profile=new ArrayList<>();
        MyApplication.profile.add("hello");
    }

    public void startActivity(View v){
        startActivity(new Intent(this, SamplePagerActivity.class));
    }

}
