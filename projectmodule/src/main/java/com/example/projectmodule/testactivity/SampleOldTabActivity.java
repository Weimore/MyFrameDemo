package com.example.projectmodule.testactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseActivity;
import com.example.projectmodule.testfragment.SampleListFragment;
import com.example.projectmodule.widget.tab.ITabFragment;
import com.example.projectmodule.widget.tab.OnTabClickListener;
import com.example.projectmodule.widget.tab.Tab;
import com.example.projectmodule.widget.tab.TabLayout;

import java.util.ArrayList;

public class SampleOldTabActivity extends BaseActivity implements OnTabClickListener{

    private TabLayout mTabLayout;
    private ArrayList<Tab> tabs;
    private ArrayList<Fragment> fragments;
    private ITabFragment fragment;

    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sample_tab, R.string.sample_title);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        addTabs();
//        addFragment();
        mTabLayout.initData(tabs, this);
        setContentFragment(0);
    }

    private void setContentFragment(int i) {
        mTabLayout.setSelection(i);
        onTabClicked(tabs.get(i));
    }


    protected void addFragment() {
        fragments = new ArrayList<>();
        SampleListFragment f1 = new SampleListFragment();
        fragments.add(f1);
    }

    //子类重写该方法添加tab和fragment
    private void addTabs() {
        tabs = new ArrayList<>();
        if (tabs.size() > 0) {
            tabs.clear();
        }
        Tab tab1 = new Tab(R.mipmap.ic_launcher, "TAB1", R.menu.toolbar_meun, SampleListFragment.class);
        Tab tab2 = new Tab(R.mipmap.ic_launcher, "TAB2", SampleListFragment.class);
        Tab tab3 = new Tab(R.mipmap.ic_launcher, "TAB3", R.menu.toolbar_meun, SampleListFragment.class);
        Tab tab4 = new Tab(R.mipmap.ic_launcher, "TAB4", SampleListFragment.class);

        tabs.add(tab1);
        tabs.add(tab2);
        tabs.add(tab3);
        tabs.add(tab4);
    }

    @Override
    public void onTabClicked(Tab tab) {
        try {
            fragment = tab.tabFragmentClz.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.sample_tab_framelayout, fragment.getFragment()).commitAllowingStateLoss();
            setUpTitle(tab.textRes);
            setUpMenu(tab.menuRes);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        super.onMenuItemClick(item);
//        return fragment.onMenuItemClick(item);
//    }
}
