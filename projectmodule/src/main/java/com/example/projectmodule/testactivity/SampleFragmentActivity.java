package com.example.projectmodule.testactivity;

import android.os.Bundle;

import com.example.projectmodule.R;
import com.example.projectmodule.base.BaseActivity;

/**
 * Created by 吴城林 on 2017/8/20.
 */
//包含fragment的activity的示例
public class SampleFragmentActivity extends BaseActivity{

//    private FrameLayout frameLayout;

    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sample_fragment,R.string.sample_fragment_title,MODE_BACK);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
//        frameLayout= (FrameLayout) findViewById(R.id.fragment_framelayout);
    }

}
