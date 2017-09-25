package com.example.httpmodule.utils.myhttputils.httpcallback;

/**
 * Created by 吴城林 on 2017/7/20.
 */

public abstract class XmlCallBack<T> extends RequestCallBack<T> {
    @Override
    public T parseData(String result) {
        //这里吧xml解析成想要的对象并返回
        return null;
    }

}
