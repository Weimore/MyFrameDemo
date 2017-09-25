package com.example.projectmodule.testactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseHolder;
import com.example.projectmodule.base.BaseListActivity;
import com.example.projectmodule.model.User;
import com.example.projectmodule.widget.pull.ILayoutManager;
import com.example.projectmodule.widget.pull.MyLinearLayoutManager;

import java.util.ArrayList;

public class SampleListActivity extends BaseListActivity<User> {


    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sample_list, R.string.sample_title);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.setRefreshing();  //初始化刷新一次
        recycler.showFooterLayout(true);  //显示正在加载的footer
    }

    @Override
    protected ILayoutManager setLayoutMananger() {
        return new MyLinearLayoutManager(getBaseContext());
//        return new MyGridLayoutManager(getBaseContext(), 3, adapter);
//        return new MyStaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void onRefreshData(int action) {

        if (dataLists == null) {
            dataLists = new ArrayList<>();
        }

        handler.sendEmptyMessageDelayed(0, 1000);


    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int size = dataLists.size();
            for (int i = size; i < size + 20; i++) {
                User user = new User("联合市场" + i, R.drawable.splash);
                dataLists.add(user);
            }
            adapter.notifyDataSetChanged();
            recycler.onRefreshComplete();
            if (dataLists.size() < 100) {
                recycler.loadMoreEnable(true);
            } else {
                recycler.loadMoreEnable(false);
            }
        }
    };

    @Override
    protected BaseHolder onCreateNormalHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item_one, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SampleListActivity.this,SampleFragmentActivity.class));
            }
        });
        return new UserHolder(view);
    }

    class UserHolder extends BaseHolder {
        ImageView imageView;
        TextView textView;
        View itemView;

        public UserHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }

        //直接把bind方法移动到holder中，直接在holder中就开始根据位置绑定数据
        @Override
        protected void onBindViewItem(int position) {
            User user = dataLists.get(position);
            imageView.setImageResource(user.getImageRes());
            textView.setText(user.getName());
        }
    }


}
