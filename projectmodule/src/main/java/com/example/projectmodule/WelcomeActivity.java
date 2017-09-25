package com.example.projectmodule;

import android.content.Intent;
import android.os.Bundle;

import com.example.projectmodule.base.BaseSplashActivity;

/**
 * Created by 吴城林 on 2017/8/4.
 */

public class WelcomeActivity extends BaseSplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSplash(new Intent(WelcomeActivity.this, HomeActivity.class),R.drawable.splash);
    }

    @Override
    protected void setUpContentView(Bundle savedInstanceState) {

    }
}
