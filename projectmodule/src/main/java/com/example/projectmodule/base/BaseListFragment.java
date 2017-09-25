package com.example.projectmodule.base;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectmodule.R;
import com.example.projectmodule.utils.LogUtils;
import com.example.projectmodule.widget.pull.ILayoutManager;
import com.example.projectmodule.widget.pull.MyLinearLayoutManager;
import com.example.projectmodule.widget.pull.PullToRefreshRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/8/19.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements PullToRefreshRecycler.OnPullToRefreshListener{

    protected PullToRefreshRecycler recycler;
    protected BaseListAdapter adapter;
    protected ArrayList<T> dataLists;

    @Override
    protected void setUpView(View view, Bundle saveInstanceState) {
        super.setUpView(view, saveInstanceState);
        recycler = (PullToRefreshRecycler) view.findViewById(R.id.pulltorefresh_recycler);
    }

    @Override
    protected void setUpData(Bundle saveInstance) {
        super.setUpData(saveInstance);
        if(dataLists == null){
            dataLists=new ArrayList<T>();
            LogUtils.e("dataList is null............................"+getArguments());
        }
        recycler.setOnRefreshListener(this);
        setUpAdapter();
        recycler.setLayoutManager(setLayouManager());
        recycler.addItemDecoration(setItemDecration());
        recycler.setAdapter(adapter);
    }

    protected void setUpAdapter() {
        adapter = new ListAdapter();
    }

    protected ILayoutManager setLayouManager() {
        return new MyLinearLayoutManager(context);
    }

    protected RecyclerView.ItemDecoration setItemDecration() {
        return null;
    }

    @Override
    public void onRefresh(int action) {
        if (action == PullToRefreshRecycler.ACTION_PULLTO_REFRESH) {
            dataLists.clear();
        }
        onRefreshData(action);
    }

    protected abstract void onRefreshData(int action);

    class ListAdapter extends BaseListAdapter{
        @Override
        protected int getDataCount() {
            return dataLists != null ? dataLists.size() : 0;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        protected BaseHolder onCreateNormalHolder(ViewGroup parent, int viewType) {
            return onCreateHolder(parent,viewType);
        }
    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseHolder onCreateHolder(ViewGroup parent, int viewType);

}
