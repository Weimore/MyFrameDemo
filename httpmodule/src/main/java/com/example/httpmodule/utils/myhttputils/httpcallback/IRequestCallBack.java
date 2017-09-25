package com.example.httpmodule.utils.myhttputils.httpcallback;

import com.example.httpmodule.utils.myhttputils.httpException.AppException;

import java.net.HttpURLConnection;

/**
 * Created by 吴城林 on 2017/7/17.
 */

//public interface IRequestCallBack<T> {
//    void onSuccess(T response);
//    void onFailed(AppException e);
//    T parse(HttpURLConnection connection) throws AppException;
//    T parse(HttpURLConnection connection,OnProgressUpdataListener listener) throws AppException;
//
//    T preRequest();  //在执行http请求之前，先在数据库中查询是否已缓存，如果已缓存，则从数据库中取出，不再执行http查询
//
//    T postRequest(T t); //用于对象返回之前在子线程处理对象（如存入数据库，或修改对象信息等） (如果是FileCallBack使用，则该方法为空方法，无意义)
//
//    void updateProgress(Integer curLen, Integer totalLen);  //用于更新下载进度（如果是RequestCallBack使用，则该方法为空方法，无意义）
//
//    void cancel();  //用于取消请求的执行
//}

public abstract class IRequestCallBack<T> {
    protected volatile boolean isCancelled;

    public abstract void onSuccess(T response);

    public abstract void onFailed(AppException e);

    public T parse(HttpURLConnection connection) throws AppException{
        return this.parse(connection,null);
    }

    public T parse(HttpURLConnection connection, OnProgressUpdataListener listener) throws AppException{
        return null;
    }

    //在执行http请求之前，先在数据库中查询是否已缓存，如果已缓存，则从数据库中取出，不再执行http查询
    public T preRequest(){
        return null;
    };

    //用于对象返回之前在子线程处理对象（如存入数据库，或修改对象信息等） (如果是FileCallBack使用，则该方法为空方法，无意义)
    protected T postRequest(T t){
        return null;
    }

    //用于更新下载进度（如果是RequestCallBack使用，则该方法为空方法，无意义）
    public void updateProgress(Integer curLen, Integer totalLen){

    };

    //用于取消请求的执行
    public void cancel(){
        isCancelled=true;
    };

    //用于检查请求是否已取消，如果取消就直接抛出异常，停止执行
    protected void checkIfCancelled() throws AppException{
        if(isCancelled){
            throw new AppException(AppException.ErrorType.CANCEL,"the request hans been cancelled");
        }
    }
}
