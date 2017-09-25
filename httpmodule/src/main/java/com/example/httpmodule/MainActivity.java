package com.example.httpmodule;

import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.httpmodule.base.BaseActivity;
import com.example.httpmodule.model.User;
import com.example.httpmodule.utils.myhttputils.MyRequest;
import com.example.httpmodule.utils.myhttputils.RequestManager;
import com.example.httpmodule.utils.myhttputils.httpException.AppException;
import com.example.httpmodule.utils.myhttputils.httpcallback.JsonCallBack;

import java.io.File;


public class MainActivity extends BaseActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void initHttp() {
        String url = "http://v.juhe.cn/WNXG/city?key=19cd561baff8c8c66d700601a2145ecc";
        final MyRequest request = new MyRequest(url, MyRequest.RequestMethod.GET);
//        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"demo.jpg";
        request.setCallBack(new JsonCallBack<User>() {
            @Override
            public User postRequest(User user) {//该方法是运行在子线程中
                user.set上海("shanghai");
                return user;
            }

            @Override
            public void onSuccess(User response) {
                textView.setText(response.toString());
            }

            @Override
            public void onFailed(AppException e) {

            }
        });
        request.setOnGlobalExceptionListener(this);  //设置监听全局Exception，如token过期等（登录失效）
        request.setTag(toString());
        RequestManager.getInstance().performRequest(request);
//        task.execute();
    }

    private void init() {
        textView= (TextView) findViewById(R.id.id_textView);
    }


    public void onButtonClicked(View view){
        initHttp();
    }
}
