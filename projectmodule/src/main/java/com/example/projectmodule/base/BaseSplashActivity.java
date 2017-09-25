package com.example.projectmodule.base;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.projectmodule.utils.AppStatusTracker;
import com.example.projectmodule.utils.ConstantValues;
import com.example.projectmodule.utils.LogUtils;

public abstract class BaseSplashActivity extends BaseActivity {

    private volatile boolean threadfinish = false;  //如果为true,则使线程直接return，不再启动HomeActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusTracker.getInstance().setApp_status(ConstantValues.STATUS_OFF_LINE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 子类通过在setData()或者setView（）中调用该方法，可以实现广告页效果
     * @param intent  用于决定跳转到哪一个activity
     * @param imageResId  启动页的图片资源
     */
    protected void initSplash(Intent intent, int imageResId) {
        this.initSplash(intent, imageResId, 0l);
    }

    /**
     * 上面方法会调用该方法，不过该方法多了一个参数，可以设置启动页等待的时间
     */
    protected void initSplash(final Intent intent, int imageResId ,long delayMillis) {
        LogUtils.d("initSplash....................");
        if (intent == null) {
            return;
        }
        if (imageResId != 0) {
            getWindow().setBackgroundDrawableResource(imageResId);
        }
        if (delayMillis == 0L) {
            delayMillis = 3000;
        }
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (threadfinish) {
                    return;
                }
                startActivity(intent);
                overridePendingTransition(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
                finish();
            }
        }, delayMillis);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d("onMewIntnet...........................");
    }

    @Override
    public void onBackPressed() {
        /**
         * 如果欢迎界面是不可back的，则直接在空指针的时候返回到WelcomeActivity界面就好了，
         * 因为WelcomeActivity会自启动HomeActivity，然后清空任务栈（HomeActivity为SingleTask模式）
         * 当然WelcomeActivity使用singelTask最好
         *
         * 如果欢迎界面是可back的，就要把WelcomeActivity也设置为SingleTask,并且最好重写onNewIntent（）方法去启动HomeActivity
         * 这种模式下最好设置点击back就不自启动HomeActivity
         *
         */

//        super.onBackPressed();
//        threadfinish = true;
    }

}
