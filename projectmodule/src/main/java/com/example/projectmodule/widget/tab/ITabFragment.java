package com.example.projectmodule.widget.tab;

import android.view.MenuItem;

import com.example.projectmodule.base.BaseFragment;

/**
 * Created by 吴城林 on 2017/8/28.
 */

public interface ITabFragment {
    boolean onMenuItemClick(MenuItem item);
    BaseFragment getFragment();
//    String getFragmentTag();
}
