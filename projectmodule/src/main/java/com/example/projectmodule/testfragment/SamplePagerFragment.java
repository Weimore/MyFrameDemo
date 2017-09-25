package com.example.projectmodule.testfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseFragment;
import com.example.projectmodule.utils.LogUtils;
import com.example.projectmodule.widget.tab.ITabFragment;

import java.util.Random;

/**
 * Created by 吴城林 on 2017/8/30.
 */

public class SamplePagerFragment extends BaseFragment implements ITabFragment{

    private TextView textView;
    protected String params;


    public static SamplePagerFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        SamplePagerFragment fragment = new SamplePagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View setUpContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLazyLoadEnable();
        return inflater.inflate(R.layout.fragment_sample_pager, container, false);
    }


    @Override
    protected void setUpView(View view, Bundle saveInstanceState) {
        super.setUpView(view, saveInstanceState);
        textView = (TextView) view.findViewById(R.id.sample_fragment_text);
    }

    @Override
    protected void setUpData(Bundle saveInstance) {
        super.setUpData(saveInstance);
        if(params == null){
            params = String.valueOf(new Random().nextInt(10));
        }
        textView.setText("page:" +getArguments().getString(ARGUMENT)+"...."+params);
        LogUtils.e("setUpData.............................."+getClass().getSimpleName()+getTag());
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
