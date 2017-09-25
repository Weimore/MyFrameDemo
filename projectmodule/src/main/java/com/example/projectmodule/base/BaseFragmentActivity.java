package com.example.projectmodule.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projectmodule.R;
//(未使用，搭建一个包含动态fragment的activity模板)
public abstract class BaseFragmentActivity extends BaseActivity {
    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        //如果activity被异常销毁，则在 Activity再次创建时 将异常销毁时保存的 关于Fragment的状态的内容 给删除
        // 防止造成之前的Fragment 与重新创建的Fragment叠加
        if(savedInstanceState!= null) {
            String FRAGMENTS_TAG =  "android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
    }
}
