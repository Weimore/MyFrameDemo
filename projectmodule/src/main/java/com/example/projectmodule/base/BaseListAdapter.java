package com.example.projectmodule.base;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectmodule.R;

/**
 * Created by 吴城林 on 2017/8/19.
 */

public abstract class BaseListAdapter extends RecyclerView.Adapter<BaseHolder> {

    private boolean showFooter;
    private final static int SHOW_LOADMORE_FOOTER = 100;

    @Override
    public int getItemCount() {
        return getDataCount() + (showFooter ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowFooter(position)) {
            return SHOW_LOADMORE_FOOTER;
        }
        return getDataViewType(position);
    }


    //是否滑动到了最后一个item,可以显示footer
    public boolean isShowFooter(int position) {
        if(showFooter && position == getItemCount() - 1){
            return true;
        }
        return false;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_LOADMORE_FOOTER) {
            return getLoadMoreFooter(parent);
        }
        return onCreateNormalHolder(parent, viewType);
    }

    //获得footer的holder
    private BaseHolder getLoadMoreFooter(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.samplelist_footer_layout, parent, false);
        return new FooterHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (isShowFooter(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                p.setFullSpan(true);
            }
        }
        holder.onBindViewItem(position);
    }

    //供PullToRefreshRecycler调用决定是否刷新footer
    public void showFooter(boolean showFooter) {
        this.showFooter = showFooter;
        if (showFooter) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount());
        }
    }

    private class FooterHolder extends BaseHolder {
        public FooterHolder(View view) {
            super(view);
        }

        @Override
        protected void onBindViewItem(int position) {

        }
    }

    protected abstract int getDataCount();
    protected abstract BaseHolder onCreateNormalHolder(ViewGroup parent, int viewType);
    protected abstract int getDataViewType(int position);
}
