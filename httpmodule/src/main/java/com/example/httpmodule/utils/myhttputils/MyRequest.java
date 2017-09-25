package com.example.httpmodule.utils.myhttputils;

import android.os.Build;

import com.example.httpmodule.utils.myhttputils.httpException.AppException;
import com.example.httpmodule.utils.myhttputils.httpException.OnGlobalExceptionListener;
import com.example.httpmodule.utils.myhttputils.httpcallback.IRequestCallBack;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by wn123 on 2017/5/19.
 */

public class MyRequest {

    public String url;
    public String content;
    public Map<String, String> headers = new HashMap<>();
    public RequestMethod method;
    public IRequestCallBack iCallBack;
    public OnGlobalExceptionListener onGlobalExceptionListener;
    public int retryCount = 3;  //默认重连次数为3
    public volatile boolean isCancelled;
    private RequestTask task;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String tag;


    public void checkIfCancelled() throws AppException {
        if (isCancelled) {
            throw new AppException(AppException.ErrorType.CANCEL, "the request hans been cancelled");
        }
    }

    public void setOnGlobalExceptionListener(OnGlobalExceptionListener onGlobalExceptionListener) {
        this.onGlobalExceptionListener = onGlobalExceptionListener;
    }

    public void cancel(boolean force) {
        isCancelled = true;
        iCallBack.cancel();
        if (task != null && force)
            task.cancel(force);
    }

    public void execute(Executor executor) {

        task = new RequestTask(this);
        if (Build.VERSION.SDK_INT > 11) {
            task.executeOnExecutor(executor);
        } else {
            task.execute();
        }
    }


    public enum RequestMethod {
        GET, POST, DELETE, PUT
    }

    public MyRequest(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
    }

    public MyRequest(String url) { //默认为get请求
        this.url = url;
        this.method = RequestMethod.GET;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void setCallBack(IRequestCallBack iCallBack) {
        this.iCallBack = iCallBack;
    }
}
