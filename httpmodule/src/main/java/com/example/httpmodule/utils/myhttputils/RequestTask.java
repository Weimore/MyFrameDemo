package com.example.httpmodule.utils.myhttputils;

import android.os.AsyncTask;

import com.example.httpmodule.utils.myhttputils.httpException.AppException;
import com.example.httpmodule.utils.myhttputils.httpcallback.FileCallBack;
import com.example.httpmodule.utils.myhttputils.httpcallback.OnProgressUpdataListener;

import java.net.HttpURLConnection;

/**
 * Created by 吴城林 on 2017/7/17.
 */

public class RequestTask extends AsyncTask<Void,Integer,Object>{

    private MyRequest request;

    public RequestTask(MyRequest request) {
        this.request=request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... params) {
        //在执行http请求之前，先在数据库中查询是否已缓存，如果已缓存，则从数据库中取出，不再执行http查询
        if(request.iCallBack!=null){
           Object o= request.iCallBack.preRequest();
            if(o!=null){
                return o;
            }
        }
        //在子线程中访问数据
        return request(0);
    }

    //访问数据，解析处理数据（包含重连机制）
    private Object request(int retryCount) {
        try {
            if(isCancelled()){
                return new AppException(AppException.ErrorType.CANCEL,"the request has been cancelled");
            }
            HttpURLConnection connection= HttpConnectionUtils.execute(request);
            if(request.iCallBack instanceof FileCallBack){
                return request.iCallBack.parse(connection, new OnProgressUpdataListener() {
                    @Override
                    public void update(int curLen, int totalLen) {
                        publishProgress(curLen,totalLen);
                    }
                });
            }else {
                return request.iCallBack.parse(connection);
            }
        } catch (AppException e) {
            if(e.errorType== AppException.ErrorType.TIME_OUT){
                if(retryCount<request.retryCount){
                    retryCount++;
                    return request(retryCount);
                }
            }
            return e;
        }
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
        request.iCallBack.onFailed((AppException) o);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        request.iCallBack.updateProgress(values[0],values[1]);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(o instanceof AppException){
            if(request.onGlobalExceptionListener!=null){
                if(!request.onGlobalExceptionListener.handleGlobalException((AppException) o)){
                    request.iCallBack.onFailed((AppException) o);
                }
            }else {
                request.iCallBack.onFailed((AppException) o);
            }
        }else {
            request.iCallBack.onSuccess(o);
        }
    }


}
