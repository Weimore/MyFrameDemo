package com.example.httpmodule.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.httpmodule.utils.myhttputils.RequestManager;
import com.example.httpmodule.utils.myhttputils.httpException.AppException;
import com.example.httpmodule.utils.myhttputils.httpException.OnGlobalExceptionListener;

/**
 * Created by 吴城林 on 2017/7/27.
 */

public class BaseActivity extends AppCompatActivity implements OnGlobalExceptionListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleGlobalException(AppException e) {
        if(e.statusCode==403){
            if("token invalid".equals(e.responseMessage)){
//                TODO  relogin（登录失效，重新登录）
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onStop() {
        super.onStop();
        RequestManager.getInstance().cancelRequest(toString(),true);
    }
}
