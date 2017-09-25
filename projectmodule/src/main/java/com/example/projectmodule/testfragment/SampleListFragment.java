package com.example.projectmodule.testfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseFragment;
import com.example.projectmodule.base.BaseHolder;
import com.example.projectmodule.base.BaseListFragment;
import com.example.projectmodule.model.User;
import com.example.projectmodule.utils.LogUtils;
import com.example.projectmodule.widget.pull.ILayoutManager;
import com.example.projectmodule.widget.pull.MyGridLayoutManager;
import com.example.projectmodule.widget.tab.ITabFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 吴城林 on 2017/8/20.
 */

public class SampleListFragment extends BaseListFragment<User> implements ITabFragment {


    public static SampleListFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        SampleListFragment fragment = new SampleListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View setUpContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLazyLoadEnable();
//        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_sample_list, container, false);
    }


    @Override
    protected void setUpData(Bundle saveInstance) {
        super.setUpData(saveInstance);
        LogUtils.e("setUpData..........................." + getClass().getSimpleName() + getArguments());
        recycler.showFooterLayout(true);
        if (dataLists.size() == 0) {
            recycler.setRefreshing();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list",dataLists);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dataLists = (ArrayList<User>) savedInstanceState.getSerializable("list");
    }

    @Override
    protected ILayoutManager setLayouManager() {
        return new MyGridLayoutManager(context, 2, adapter);
    }

    @Override
    protected BaseHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_item_one, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onRefreshData(int action) {
//        if (dataLists == null) {
//            dataLists = new ArrayList<>();
//        }
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int size = dataLists.size();
                for (int i = size; i < size + 20; i++) {
                    User user = new User("联合市场" + i, R.drawable.splash);
                    dataLists.add(user);
                }
                LogUtils.e("refreshData........................." + getClass().getSimpleName() + getArguments());
                adapter.notifyDataSetChanged();
                recycler.onRefreshComplete();
                if (dataLists.size() < 100) {
                    recycler.loadMoreEnable(true);
                } else {
                    recycler.loadMoreEnable(false);
                }
            }
        }, 1000);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        LogUtils.d("itemClicked" + item + "........................");
        return true;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }

    class UserHolder extends BaseHolder {

        TextView textView;
        ImageView imageView;
        View itemView;

        public UserHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView  = (ImageView) itemView.findViewById(R.id.item_image);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }

        @Override
        protected void onBindViewItem(int position) {
            imageView.setImageResource(dataLists.get(position).getImageRes());
            textView.setText(dataLists.get(position).getName());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
