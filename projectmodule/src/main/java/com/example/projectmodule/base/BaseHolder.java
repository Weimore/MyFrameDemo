package com.example.projectmodule.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 吴城林 on 2017/8/5.
 */

public abstract class BaseHolder extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
    }

    //根据位置把数据绑定到对应的holder上
   protected abstract void onBindViewItem(int position);

}
