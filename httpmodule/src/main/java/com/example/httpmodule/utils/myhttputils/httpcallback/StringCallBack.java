package com.example.httpmodule.utils.myhttputils.httpcallback;

/**
 * Created by 吴城林 on 2017/7/20.
 */

public abstract class StringCallBack extends RequestCallBack<String>{
    @Override
    public String parseData(String result) {
        return result;
    }
}
