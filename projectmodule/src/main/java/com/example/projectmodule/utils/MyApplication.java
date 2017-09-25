package com.example.projectmodule.utils;

import android.app.Application;

import java.util.List;

/**
 * Created by 吴城林 on 2017/8/2.
 */

public class MyApplication extends Application {
    public static MyApplication myApplication;
    public static List<String> profile;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("application create............................");
        myApplication=this;
        AppStatusTracker.init(this);
    }
}
