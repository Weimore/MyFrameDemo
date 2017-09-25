package com.example.projectmodule.widget.pull;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.projectmodule.base.BaseListAdapter;

/**
 * Created by 吴城林 on 2017/8/13.
 */

public class MyGridLayoutManager extends GridLayoutManager implements ILayoutManager{

    private BaseListAdapter adapter;
//    private int spanCount = 1;

    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyGridLayoutManager(Context context, int spanCount,BaseListAdapter adapter) {
        super(context, spanCount);
//        this.spanCount = spanCount;
        this.adapter = adapter;
        setSpanSizeLookup(new GridSizeLookUp());
    }

//    @Override
//    public int getSpanCount() {
//        return spanCount;
//    }

    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int getFinalVisiblePosition() {
        return findLastVisibleItemPosition();
    }

    //用于确保每次footer都是自占一行
    class GridSizeLookUp extends GridLayoutManager.SpanSizeLookup {
        @Override
        public int getSpanSize(int position) {
            if (adapter.isShowFooter(position)) {
                return getSpanCount();
            }
            return 1;
        }
    }
}
