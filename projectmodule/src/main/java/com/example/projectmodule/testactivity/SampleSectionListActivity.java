package com.example.projectmodule.testactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseHolder;
import com.example.projectmodule.base.BaseSectionListActivity;
import com.example.projectmodule.model.User;
import com.example.projectmodule.utils.DataServer;
import com.example.projectmodule.widget.pull.ILayoutManager;
import com.example.projectmodule.widget.pull.MyLinearLayoutManager;

public class SampleSectionListActivity extends BaseSectionListActivity<User> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sample_section_list,R.string.sample_title,MODE_BACK);
    }



    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.showFooterLayout(true);
        recycler.setRefreshing();
    }

    @Override
    protected ILayoutManager setLayoutMananger() {
        return new MyLinearLayoutManager(this);
    }

    @Override
    public void onRefreshData(int action) {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dataLists.addAll(DataServer.getSectionData());
                adapter.notifyDataSetChanged();
                recycler.onRefreshComplete();
                if (dataLists.size() < 100) {
                    recycler.loadMoreEnable(true);
                }else {
                    recycler.loadMoreEnable(false);
                }
            }
        }, 1000);
    }

    @Override
    protected BaseHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item_one, parent, false);
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
            User user = dataLists.get(position).t;
            imageView.setImageResource(user.getImageRes());
            textView.setText(user.getName());
        }
    }
}
