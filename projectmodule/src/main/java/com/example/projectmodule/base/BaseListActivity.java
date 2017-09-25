package com.example.projectmodule.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.projectmodule.R;
import com.example.projectmodule.widget.pull.ILayoutManager;
import com.example.projectmodule.widget.pull.MyLinearLayoutManager;
import com.example.projectmodule.widget.pull.PullToRefreshRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/8/5.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements PullToRefreshRecycler.OnPullToRefreshListener {

    protected PullToRefreshRecycler recycler;
    protected BaseListAdapter adapter;
    protected List<T> dataLists;

    @Override
    protected void setUpView() {
        recycler = (PullToRefreshRecycler) findViewById(R.id.pulltorefresh_recycler);
    }

    @Override
    protected void setUpData() {
        dataLists = new ArrayList<>();
        setUpAdapter();
        recycler.setOnRefreshListener(this);
        recycler.setLayoutManager(setLayoutMananger());
        recycler.addItemDecoration(setItemDecration());
        recycler.setAdapter(adapter);
    }

    @Override
    public void onRefresh(int action) {
        if (action == PullToRefreshRecycler.ACTION_PULLTO_REFRESH && dataLists.size() > 0) {
            dataLists.clear();
        }
        onRefreshData(action);
    }

    //刷新数据
    protected abstract void onRefreshData(int action);

    //设置adapter，以后如果要自定义adapter，只需要重写该方法就行了
    protected void setUpAdapter() {
        adapter = new ListAdapter();
    }

    //设置分割线等
    protected RecyclerView.ItemDecoration setItemDecration() {
        return null;
    }

    //设置布局风格
    protected ILayoutManager setLayoutMananger() {
        return new MyLinearLayoutManager(this);
    }


    private class ListAdapter extends BaseListAdapter {

        @Override
        protected int getDataCount() {
            return dataLists != null ? dataLists.size() : 0;
        }

        @Override
        protected BaseHolder onCreateNormalHolder(ViewGroup parent, int viewType) {
            return BaseListActivity.this.onCreateNormalHolder(parent, viewType);
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }

    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseHolder onCreateNormalHolder(ViewGroup parent, int viewType);


}
