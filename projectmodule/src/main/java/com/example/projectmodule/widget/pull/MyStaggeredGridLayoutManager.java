package com.example.projectmodule.widget.pull;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by 吴城林 on 2017/8/13.
 */

public class MyStaggeredGridLayoutManager extends StaggeredGridLayoutManager implements ILayoutManager {

    private int spanCount = 1;

    public MyStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
        this.spanCount = spanCount;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int getFinalVisiblePosition() {
        int []positions = null;
        positions = findLastVisibleItemPositions(positions);
        return positions[0];
    }

    @Override
    public int getSpanCount() {
        return spanCount;
    }


}
