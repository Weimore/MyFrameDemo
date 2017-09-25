package com.example.projectmodule.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmodule.R;
import com.example.projectmodule.WelcomeActivity;
import com.example.projectmodule.utils.AppStatusTracker;
import com.example.projectmodule.utils.ConstantValues;
import com.example.projectmodule.utils.LogUtils;

/**
 * Created by 吴城林 on 2017/8/1.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolBar;
    private TextView toolBarTitle;

    protected static final int MODE_NONE = 0;
    protected static final int MODE_BACK = 1;
    protected static final int MODE_DRAWER = 2;
    protected static final int MENU_NULL = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LogUtils.d("onCreate................." + getClass().getName());
        switch (AppStatusTracker.getInstance().getApp_status()) {
            case ConstantValues.STATUS_FORCE_KILLED:  //如果应用被强杀
                protectApp();
                break;
            case ConstantValues.STATUS_KICK_OUT:  //如果token超时或被挤下线
                kickOut();
                break;
            case ConstantValues.STATUS_ON_LINE:
            case ConstantValues.STATUS_OFF_LINE:
                setUpContentView(savedInstanceState);
                setUpView();
                setUpData();
                break;
        }
    }

    /**
     * 该方法运行在onCreate（）方法中，用于设置布局
     * @param savedInstanceState
     */
    protected abstract void setUpContentView(Bundle savedInstanceState);

    /**
     * 该方法运行在onCreate（）方法中，用于正常加载View
     */
    protected void setUpView() {
    }

    /**
     * 该方法运行在onCreate（）方法中，用于正常加载数据
     */
    protected void setUpData() {
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    //如果token超时或被挤下线
    protected void kickOut() {

    }

    //如果应用被强杀
    protected void protectApp() {
        LogUtils.d("protectApp..................");
        Intent intent = new Intent(this, WelcomeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }


    protected void sendNotify() {    //当要发送通知时，调用该方法，先判断应用处于前台还是后台，然后再决定通知发送的样式
        if (AppStatusTracker.getInstance().inBackStage()) {   //如果应用处于后台
            LogUtils.d("system in backStage............");
        } else {

        }
    }

    //设置toolbar的menu
    public void setUpMenu(int menuResID) {
        toolBar.getMenu().clear();
        if (menuResID != MENU_NULL) {   //如果传入了menu的资源id,就设置menu
            toolBar.inflateMenu(menuResID);
            toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return BaseActivity.this.onMenuItemClick(item);
                }
            });
        }
    }

    //设置toobar标题
    public void setUpTitle(String title) {
        if (title != null) {
            toolBarTitle.setText(title);
        }

    }

    public void setUpTitle(int titleContent) {
        if (titleContent != 0) {
            toolBarTitle.setText(titleContent);
        }
    }

    public boolean onMenuItemClick(MenuItem item){
        return false;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        this.setContentView(layoutResID, R.string.none_title);
    }

    protected void setContentView(@LayoutRes int layoutResID, int titleContent) {
        this.setContentView(layoutResID, titleContent, MODE_NONE);
    }

    protected void setContentView(@LayoutRes int layoutResID, int titleContent, int mode) {
        this.setContentView(layoutResID, titleContent, mode, MENU_NULL);
    }

    protected void setContentView(@LayoutRes int layoutResID, int titleContent, int mode, int menuResID) {
        this.setContentView(layoutResID, titleContent, mode, menuResID, null);
    }


    /**
     * @param layoutResID
     * @param titleContent
     * @param menuResID    the overflow menu
     * @param mode         If your mode is ConstantValues.DRAWER ,you need to input a DrawerLayout for the fifth parameter
     * @param drawerLayout
     */
    protected void setContentView(@LayoutRes int layoutResID, int titleContent, int mode, int menuResID, DrawerLayout drawerLayout) {
        super.setContentView(layoutResID);

        toolBar = (Toolbar) findViewById(R.id.toobar);
        toolBarTitle = (TextView) findViewById(R.id.toobar_title);
        if (toolBar == null && toolBarTitle != null) {  //如果没有添加toobar布局，就不进行设置
            return;
        }

        setUpMenu(menuResID);
        setUpTitle(titleContent);

        switch (mode) {
            case MODE_NONE:
                break;
            case MODE_BACK:
//                toolBar.setBackMode(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        finish();
//                    }
//                });
                toolBar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
                toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                break;
            case MODE_DRAWER:
                if (drawerLayout == null) {
                    Toast.makeText(this, "未设置DrawerLayout", Toast.LENGTH_SHORT).show();
                    return;
                }
//                toolBar.setDrawerMode(this, drawerLayout);
                setSupportActionBar(toolBar);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayShowTitleEnabled(false);  //不显示label
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(        //设定drawLayout的开关及动画
                        this, drawerLayout, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                mToogle.syncState();
                drawerLayout.setDrawerListener(mToogle);           //让ActionBarDrawerToggle监听drawerLayout的开关状态
                break;
            default:
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
//        LogUtils.d("onStart..........................." + getClass().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LogUtils.d("onResume............................" + getClass().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
//        LogUtils.d("onPause............................." + getClass().getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
//        LogUtils.d("onStop.............................." + getClass().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        LogUtils.d("onDestory..............................." + getClass().getName());
    }


}
