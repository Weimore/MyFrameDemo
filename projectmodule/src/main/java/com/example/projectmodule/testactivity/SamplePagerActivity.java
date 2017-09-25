package com.example.projectmodule.testactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseActivity;
import com.example.projectmodule.testfragment.BlankFragment;
import com.example.projectmodule.testfragment.SampleListFragment;
import com.example.projectmodule.testfragment.SamplePagerFragment;

import java.util.ArrayList;
import java.util.List;

public class SamplePagerActivity extends BaseActivity {

    private ViewPager viewPager;
    private List<Class> fragmentNames;

    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        if(savedInstanceState!= null)
        {
            String FRAGMENTS_TAG =  "android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        setContentView(R.layout.activity_sample_pager, R.string.sample_title, MODE_BACK);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        viewPager = (ViewPager) findViewById(R.id.sample_viewpager);
        viewPager.setOffscreenPageLimit(1);
    }


    @Override
    protected void setUpData() {
        super.setUpData();
//        fragmentList = new ArrayList<>();
        setUpFragments();
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                try {
                    return (Fragment) fragmentNames.get(position).newInstance();      //使用这种方法加载Fragmnet，就不会出现内存无法释放的问题
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public int getCount() {
                return fragmentNames.size();
            }
        });
    }

    private void setUpFragments() {

        if(fragmentNames  == null){
            fragmentNames = new ArrayList<>();
        }
        fragmentNames.clear();
        fragmentNames.add(SampleListFragment.class);
        fragmentNames.add(SampleListFragment.class);
        fragmentNames.add(SampleListFragment.class);
        fragmentNames.add(SampleListFragment.class);
    }

    }
