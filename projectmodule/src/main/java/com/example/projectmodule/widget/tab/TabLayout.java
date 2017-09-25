package com.example.projectmodule.widget.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.projectmodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/8/28.
 */

public class TabLayout extends LinearLayout implements View.OnClickListener {
    private final Context mContext;
    private OnTabClickListener mListener;
    private List<Tab> mTabs;
    private List<TabView> mTabViews;
    private int mColorRes = R.color.colorGreen;

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        setOrientation(HORIZONTAL);
    }

    public void initData(List<Tab> tabs, OnTabClickListener listener) {
        mTabs = tabs;
        mListener = listener;

        if (tabs == null || tabs.size() <= 0) {
            throw new IllegalArgumentException("tab's can not be empty");
        }

        mTabViews = new ArrayList<>();
        LinearLayout.LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = new TabView(mContext);
            tabView.setTag(mTabs.get(i));
            tabView.initData(mTabs.get(i));
            tabView.setOnClickListener(this);
            addView(tabView, params);
            mTabViews.add(tabView);
        }
    }



    @Override
    public void onClick(View view) {
        reviewColor(view);
        mListener.onTabClicked((Tab) view.getTag());
    }

    //重置，并设定颜色
    private void reviewColor(View view) {
        for (TabView tabView:mTabViews){
            tabView.reviewColor();
        }
        TabView tv = (TabView) view;
        tv.setColor(mColorRes);
    }

    //供外部调用，设置点击时item颜色
    public void setClickedColor(int colorRes){
        mColorRes =colorRes;
    }

    //设置点击者
    public void setSelection(int i) {
        reviewColor(mTabViews.get(i));
    }
}
