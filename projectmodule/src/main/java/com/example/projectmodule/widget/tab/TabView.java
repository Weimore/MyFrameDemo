package com.example.projectmodule.widget.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectmodule.R;

/**
 * Created by 吴城林 on 2017/8/28.
 */

public class TabView extends LinearLayout{

    private Context mContext;
    private TextView mTextView;
    private ImageView mImageView;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.tabview_layout,this);
        setOrientation(VERTICAL);
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.tab_imageview);
        mTextView = (TextView) findViewById(R.id.tab_textview);
    }


    public void initData(Tab tab) {
        mImageView.setBackgroundResource(tab.imageRes);
        mTextView.setText(tab.textRes);
    }

    public void setColor(int colorRes){

        mTextView.setTextColor(getResources().getColor(colorRes));
    }

    public void reviewColor(){

        mTextView.setTextColor(getResources().getColor(R.color.colorBlack));
    }
}
