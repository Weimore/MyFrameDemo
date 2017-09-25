package com.example.projectmodule.widget;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectmodule.R;


/**
 * Created by 吴城林 on 2017/8/1.
 * （弃用）
 */

public class MyToolBar extends LinearLayout {

    private Toolbar contentToolbar;
    private TextView toolBarTitle;

    public MyToolBar(Context context) {
        this(context, null);
    }

    public MyToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.my_toolbar,this);
        contentToolbar= (Toolbar) findViewById(R.id.content_toolbar);
        toolBarTitle = (TextView) findViewById(R.id.toobar_title);
    }

    public void setTitle(int titleRes){
        toolBarTitle.setText(titleRes);
    }

    public void setBackMode(OnClickListener backListener){
        contentToolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        contentToolbar.setNavigationOnClickListener(backListener);
    }


    public void setDrawerMode(AppCompatActivity activity, DrawerLayout drawerLayout){
        activity.setSupportActionBar(contentToolbar);
        ActionBar actionBar=activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayShowTitleEnabled(false);  //不显示label
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(        //设定drawLayout的开关及动画
                activity, drawerLayout, contentToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mToogle.syncState();
        drawerLayout.setDrawerListener(mToogle);           //让ActionBarDrawerToggle监听drawerLayout的开关状态
    }


    public void setMenu(int menuResID) {
        contentToolbar.getMenu().clear();
        contentToolbar.inflateMenu(menuResID);
    }
}
