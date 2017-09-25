package com.example.projectmodule.widget.pull;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseListAdapter;

/**
 * Created by 吴城林 on 2017/8/6.
 */

public class PullToRefreshRecycler extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    private OnPullToRefreshListener listener;
    public static final int ACTION_IDLE = 0;
    public static final int ACTION_PULLTO_REFRESH = 1;
    public static final int ACTION_LOADMORE_REFRESH = 2;

    private int currentAction = ACTION_IDLE;
    private boolean loadMoreEnable = false;
    private boolean pullToRefreshEnable = true;
    private ILayoutManager layoutManager;
    private BaseListAdapter adapter;
    private boolean showFooter = false;

    public PullToRefreshRecycler(Context context) {
        this(context, null);
    }

    public PullToRefreshRecycler(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshRecycler(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View v = LayoutInflater.from(context).inflate(R.layout.widget_pulltorefresh_recycler, this);
//        View v=LayoutInflater.from(context).inflate(R.layout.widget_pulltorefresh_recycler,(ViewGroup) getRootView(),true);
        initView(v);
    }

    private void initView(View v) {
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                LogUtils.d("loadMoreEnable:" + loadMoreEnable + " currentAction:" + currentAction + " checkIfLoadMore():" + checkIfLoadMore());
                if (loadMoreEnable && currentAction == ACTION_IDLE && checkIfLoadMore()) {
//                    LogUtils.d("可加载更多。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
                    currentAction = ACTION_LOADMORE_REFRESH;
                    if (showFooter) {
                        adapter.showFooter(true);
                    }
                    swipeRefreshLayout.setEnabled(false);
                    listener.onRefresh(ACTION_LOADMORE_REFRESH);
                }
            }
        });
    }

    //供外界调用，决定是否加载footer，默认加载
    public void showFooterLayout(boolean showFooter) {
        this.showFooter = showFooter;
    }

    //供外界调用，可决定是否启用加载更多功能
    public void loadMoreEnable(boolean enable) {
        loadMoreEnable = enable;
    }

    //供外界调用，可决定是否启用下拉刷新功能
    public void pullToRefreshEnable(boolean enable) {
        pullToRefreshEnable = enable;
    }

    //该方法根据 目前可见的最后一个item的位置和总的item的数目来判断是否加载更多
    //不同的布局对该方法的处理也不同
    private boolean checkIfLoadMore() {
        int positon = layoutManager.getFinalVisiblePosition();
        int totalCount = layoutManager.getLayoutManager().getItemCount();  //总的item数目（不是显示的item数目）
        if (totalCount - positon < layoutManager.getSpanCount() + 1) {
            return true;
        }
        return false;
    }

    //添加分割线等
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        if (itemDecoration != null) {
            recyclerView.addItemDecoration(itemDecoration);
        }
    }

    public void setLayoutManager(ILayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        recyclerView.setLayoutManager(layoutManager.getLayoutManager());
    }

    public void setAdapter(BaseListAdapter adapter) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }

    //swipeRefreshLayout的刷新方法（下拉刷新）
    @Override
    public void onRefresh() {
        currentAction = ACTION_PULLTO_REFRESH;
        listener.onRefresh(ACTION_PULLTO_REFRESH);
    }

    //如果在setUpData（）中添加该方法，可以在一开始初始化时就刷新一次数据
    public void setRefreshing() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public void onRefreshComplete() {
        switch (currentAction) {
            case ACTION_PULLTO_REFRESH:
                swipeRefreshLayout.setRefreshing(false);
                break;
            case ACTION_LOADMORE_REFRESH:
                if (pullToRefreshEnable) {
                    swipeRefreshLayout.setEnabled(true);
                    adapter.showFooter(false);
                }
                break;
        }
        currentAction = ACTION_IDLE;
    }

    public void setOnRefreshListener(OnPullToRefreshListener listener) {
        this.listener = listener;
    }

    public interface OnPullToRefreshListener {
        void onRefresh(int action);
    }


}