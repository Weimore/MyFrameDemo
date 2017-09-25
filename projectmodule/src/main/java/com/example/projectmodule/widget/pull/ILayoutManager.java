package com.example.projectmodule.widget.pull;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 吴城林 on 2017/8/10.
 */

public interface ILayoutManager {
    RecyclerView.LayoutManager getLayoutManager();
    int getFinalVisiblePosition();
    int getSpanCount();
}
