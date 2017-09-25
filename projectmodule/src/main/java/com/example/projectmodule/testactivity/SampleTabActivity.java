package com.example.projectmodule.testactivity;

import android.os.Bundle;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseTabActivity;
import com.example.projectmodule.testfragment.SampleListFragment;
import com.example.projectmodule.testfragment.SamplePagerFragment;
import com.example.projectmodule.widget.tab.Tab;

import java.util.ArrayList;

public class SampleTabActivity extends BaseTabActivity{

    private ArrayList<Tab> tabs;

    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        if(savedInstanceState!= null)
        {
            String FRAGMENTS_TAG =  "android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        setContentView(R.layout.activity_sample_tab, R.string.sample_title);
    }

    //设置初始加载的是哪一个fragment
    @Override
    protected void setUpData() {
        super.setUpData();
        setContentFragment(1);
    }

    //设置容器id
    @Override
    public int setContainerId() {
        return R.id.sample_tab_framelayout;
    }

    //添加tabs
    @Override
    protected ArrayList<Tab> addTabs() {
        tabs = new ArrayList<>();
        if (tabs.size() > 0) {
            tabs.clear();
        }
        Tab tab1 = new Tab(R.mipmap.ic_launcher, "TAB1", R.menu.toolbar_meun, SampleListFragment.class);
        Tab tab2 = new Tab(R.mipmap.ic_launcher, "TAB2", SamplePagerFragment.class);
//        Tab tab3 = new Tab(R.mipmap.ic_launcher, "TAB3", R.menu.toolbar_meun, SampleListFragment.class);
//        Tab tab4 = new Tab(R.mipmap.ic_launcher, "TAB4", SampleListFragment.class);

        tabs.add(tab1);
        tabs.add(tab2);
//        tabs.add(tab3);
//        tabs.add(tab4);
        return tabs;
    }

}
