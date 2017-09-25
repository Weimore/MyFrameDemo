package com.example.projectmodule.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectmodule.R;
import com.example.projectmodule.model.SectionData;

/**
 * Created by 吴城林 on 2017/8/20.
 */

public abstract class BaseSectionListActivity<T> extends BaseListActivity<SectionData<T>> {

    private static final int VIEW_TYPE_HEADER = 101;
    private static final int VIEW_TYPE_NORMAL = 2;

    @Override
    protected int getItemType(int position) {
        return dataLists.get(position).isHeader ? VIEW_TYPE_HEADER : VIEW_TYPE_NORMAL;
    }

    @Override
    protected BaseHolder onCreateNormalHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return onCreateHeaderHolder(parent);
        }
        return onCreateHolder(parent, viewType);
    }

    protected abstract BaseHolder onCreateHolder(ViewGroup parent, int viewType);

    protected BaseHolder onCreateHeaderHolder(ViewGroup parent) {
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.sample_item_header, parent, false);
        return new HeaderHolder(view);
    }

    class HeaderHolder extends BaseHolder {

        View view;
        TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            this.view =itemView;
            this.header = (TextView) view.findViewById(R.id.sample_item_header);
        }

        @Override
        protected void onBindViewItem(int position) {
            header.setText(dataLists.get(position).headerName);
        }
    }

}
