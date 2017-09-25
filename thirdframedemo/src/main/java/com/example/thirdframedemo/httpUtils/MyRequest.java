package com.example.thirdframedemo.httpUtils;

import java.util.Map;

/**
 * Created by wn123 on 2017/5/19.
 */

public class MyRequest {

    public String url;
    public String content;
    public Map<String,String> headers;

    public RequestMethod method;

    public enum RequestMethod{
        GET,POST,DELETE,PUT
    }

    public MyRequest(String url,RequestMethod method) {
        this.url = url;
        this.method=method;
    }

    public MyRequest(String url) { //默认为get请求
        this.url = url;
        this.method=RequestMethod.GET;
    }

    public void addHeader(String key,String value){
        headers.put(key,value);
    }
}
