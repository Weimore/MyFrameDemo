package com.example.thirdframedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.thirdframedemo.httpUtils.HttpConnectionUtils;
import com.example.thirdframedemo.httpUtils.MyRequest;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void get() {
        String url = "https://imgsa.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=10f644803b01213fdb3e468e358e5db4/d50735fae6cd7b8903b74ada0d2442a7d8330e8b.jpg";
        MyRequest request = new MyRequest(url);
        request.url = "https://imgsa.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=10f644803b01213fdb3e468e358e5db4/d50735fae6cd7b8903b74ada0d2442a7d8330e8b.jpg";
        String response = HttpConnectionUtils.execute(request);
        Log.e("Rella", "response" + response);
    }

    private void post() {
        String url = "";
        MyRequest request = new MyRequest(url, MyRequest.RequestMethod.POST);
//        request.url="";
        String response = HttpConnectionUtils.execute(request);
        Log.e("Rella", "response" + response);
    }
}
