package com.example.projectmodule.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by 吴城林 on 2017/8/1.
 */

//该类用来判断当前app的状态
public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {

    private static AppStatusTracker tracker;

    private int App_status = ConstantValues.STATUS_FORCE_KILLED;     //刚初始化时默认处于被强杀状态
    private int Activity_number = 0;   //处于前台的activity数目
    private boolean inBackStage;       //程序当前是否处于后台状态

    private AppStatusTracker(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    //该方法只可以在MyApplication中被调用
    public static void init(Application application) {
        if (tracker == null) {
            tracker = new AppStatusTracker(application);
        }
    }

//    private static class AppTrackerHolder{
//        private static AppStatusTracker tracker = new AppStatusTracker();
//    }

    //该方法可返回一个实例
    public static AppStatusTracker getInstance() {
        return tracker;
    }

    public int getApp_status() {
        return this.App_status;
    }

    public void setApp_status(int app_status) {
        this.App_status = app_status;
    }

    public boolean inBackStage(){
        return inBackStage;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        LogUtils.d("onActivityCreated..........................." + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtils.d("onActivityStarted..........................." + activity.getClass().getSimpleName());
        Activity_number++;
        inBackStage = false;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtils.d("onActivityResumed..........................." + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtils.d("onActivityPaused..........................." + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtils.d("onActivityStopped..........................." + activity.getClass().getSimpleName());
        Activity_number--;
        if (Activity_number == 0) {
            inBackStage = true;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        LogUtils.d("onActivitySaveInstanceState..........................." + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.d("onActivityDestroyed..........................." + activity.getClass().getSimpleName());
    }
}
