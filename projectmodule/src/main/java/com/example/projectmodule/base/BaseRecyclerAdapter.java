package com.example.projectmodule.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 吴城林 on 2017/8/12.
 */

//通用adapter(未完成，未使用)
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseHolder> {

    private Context context;
    private List datas;

    public BaseRecyclerAdapter(Context context, List datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.onBindViewItem(position);
    }
}
