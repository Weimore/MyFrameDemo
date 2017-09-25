package com.example.projectmodule.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.example.projectmodule.R;
import com.example.projectmodule.utils.LogUtils;
import com.example.projectmodule.widget.tab.ITabFragment;
import com.example.projectmodule.widget.tab.OnTabClickListener;
import com.example.projectmodule.widget.tab.Tab;
import com.example.projectmodule.widget.tab.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 目前该BaseTabActivity采用的是将初始化过的fragment缓存起来，而不是销毁再创建，故适用于fragment较少的环境下
 * 当然以后考虑使用策略模式使该功能可切换，添加上销毁创建模式
 * 它目前并不适合ViewPager的滑动切换按钮高亮效果，以后会优化
 */
public abstract class BaseTabActivity extends BaseActivity implements OnTabClickListener {

    private TabLayout mTabLayout;
    private List<Tab> tabs;
    //    private WeakHashMap<String, Fragment> frags;
    private ITabFragment iContentFragment;
    private int containerId;


    @Override
    protected void setUpView() {
        super.setUpView();
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        tabs = new ArrayList<>();
//        frags = new WeakHashMap<>();

        containerId = setContainerId();  // 设置做为fragment容器的framelayout的id值
        tabs.addAll(addTabs());           //添加tab
        mTabLayout.initData(tabs, this);
//        setContentFragment(0);
    }

    //设置当前显示哪一个fragment，以及哪一个tab高亮显示
    protected void setContentFragment(int i) {
        mTabLayout.setSelection(i);  //设置按钮高亮
        onTabClicked(tabs.get(i));    //设置fragment切换等一系列操作
    }

    //输入做为fragment容器的framelayout的id值
    public abstract int setContainerId();

    //子类重写该方法添加tab和fragment
    protected abstract ArrayList<Tab> addTabs();

    @Override
    public void onTabClicked(Tab tab) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);   //设置fragment切换动画
        setUpFragment(tab, transaction);
        setUpTitle(tab.textRes);
        setUpMenu(tab.menuRes);

    }

    //设置fragment的初始化与显示
    private void setUpFragment(Tab tab, FragmentTransaction transaction) {
        try {
            ITabFragment tmpFragment = (ITabFragment) getSupportFragmentManager().findFragmentByTag(tab.tabFragmentClz.getSimpleName());    //先在FragmnetManager中通过TAG找一下该Fragment是否已存在
            if (tmpFragment == null) {          //如果当期fragment不在FragmnetManager中,则将其实例化，并加到FragmentManager中
                LogUtils.e("temp isNULL........................................");
                tmpFragment = tab.tabFragmentClz.newInstance();
                transaction.add(containerId, tmpFragment.getFragment(),tab.tabFragmentClz.getSimpleName());  //将其添加到FragmentManager中，并且设置一个TAG
                if (iContentFragment != null) {             //如果当前顶部不显示任何Fragment,则将这个Fragment添加到顶部
                    transaction.hide(iContentFragment.getFragment()).commit();
                }
                else {       //如果已有Fragment显示在顶部，则先隐藏顶部的Fragment，再显示该Fragment
                    transaction.commit();
                }
            }
            else {
                transaction.show(tmpFragment.getFragment());      //如果FragmentManager中已有该Fragment，则直接显示该Fragment到顶部
                if(iContentFragment != null){     //如果顶部已有Fragment，则隐藏顶部的Fragment
                    transaction.hide(iContentFragment.getFragment()).commit();
                }else {                       //如果顶部无Fragment，则直接提交事务即可
                    transaction.commit();
                }
            }
            iContentFragment = tmpFragment;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

//    //设置fragment的初始化与显示
//    private void setUpFragment(Tab tab, FragmentTransaction transaction) {
//        hideFragment(transaction);      //隐藏fragment
//        try {
//
//            if (frags.get(tab.toString()) == null) {        //如果该fragment未被初始化过
//                iContentFragment = tab.tabFragmentClz.newInstance();  //初始化fragment
//                frags.put(tab.toString(), iContentFragment.getFragment());          //将其添加到fragment队列
//                transaction.add(containerId, iContentFragment.getFragment());   //将其添加到容器顶部
//            } else {
//                transaction.show(frags.get(tab.toString()));   //将其移到容器顶部
//            }
//            transaction.commit();
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

//        //隐藏所有已被初始化的fragment
//        private void hideFragment (FragmentTransaction transaction){
//            for (WeakHashMap.Entry<String, Fragment> entry : frags.entrySet()) {
//                transaction.hide(entry.getValue());
//            }
//        }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        super.onMenuItemClick(item);
        return iContentFragment.onMenuItemClick(item);
    }
}
