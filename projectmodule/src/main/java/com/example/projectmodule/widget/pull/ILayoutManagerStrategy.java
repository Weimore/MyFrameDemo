package com.example.projectmodule.widget.pull;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by 吴城林 on 2017/9/2.
 */

public enum  ILayoutManagerStrategy {

    GRIDLAYOUT{
        @Override
        public RecyclerView.LayoutManager getLayoutManager() {
            return null;
        }

        @Override
        public int getFinalVisiblePosition() {
            return 0;
        }

        @Override
        public int getSpanCount() {
            return 0;
        }
    },
    LINEARLAYOUT{
        @Override
        public RecyclerView.LayoutManager getLayoutManager() {
            return null;
        }

        @Override
        public int getFinalVisiblePosition() {
            return 0;
        }

        @Override
        public int getSpanCount() {
            return 0;
        }
    },
    STAGGEREDGRIDLAYOUT{
        @Override
        public RecyclerView.LayoutManager getLayoutManager() {
            return null;
        }

        @Override
        public int getFinalVisiblePosition() {
            return 0;
        }

        @Override
        public int getSpanCount() {
            return 0;
        }
    };

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract int getFinalVisiblePosition();

    public abstract int getSpanCount();
}
