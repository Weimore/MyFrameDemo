package com.example.projectmodule.widget.pull;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by 吴城林 on 2017/8/13.
 */

public class MyLinearLayoutManager extends LinearLayoutManager implements ILayoutManager{
    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int getFinalVisiblePosition() {
        return findLastVisibleItemPosition();
    }

    @Override
    public int getSpanCount() {
        return 1;
    }

}
