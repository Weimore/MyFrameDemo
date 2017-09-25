package com.example.httpmodule.utils.myhttputils.httpcallback;


import com.example.httpmodule.utils.myhttputils.HttpStatus;
import com.example.httpmodule.utils.myhttputils.httpException.AppException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by 吴城林 on 2017/7/19.
 */

//该类是一个抽象类，子类继承该类后，必须实现parse抽象方法，对不同属性的数据进行解析，并返回 T 对象
public abstract class RequestCallBack<T> extends IRequestCallBack<T> {

//    private volatile boolean isCancelled;
//
//    @Override
//    public void cancel() {
//        isCancelled=true;
//    }
//
//    public void checkIfCancelled() throws AppException{
//        if(isCancelled){
//            throw new AppException(AppException.ErrorType.CANCEL,"the request hans been cancelled");
//        }
//    }

//    @Override
//    public T parse(HttpURLConnection connection, OnProgressUpdataListener listener) throws AppException {
//        return parse(connection);
//    }

    @Override
    public T parse(HttpURLConnection connection) throws AppException {
        try {
            checkIfCancelled();
            int statusCode=connection.getResponseCode();
            if (statusCode == HttpStatus.STATUS_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    InputStream is = connection.getInputStream();
                    byte[] buffer = new byte[2048];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        checkIfCancelled();
                        out.write(buffer, 0, len);
                    }
                    is.close();
                    out.flush();
                    out.close();
                    connection.disconnect();
                    String result = new String(out.toByteArray());
                    T t= parseData(result);
                return postRequest(t);
            }else {//如果STATUS_CODE不是200，则直接返回错误
//                connection.getErrorStream();
//                connection.getResponseMessage();
                throw new AppException(statusCode,connection.getResponseMessage());
            }
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.TIME_OUT,e.getLocalizedMessage());
        }
    }

    public abstract T parseData(String result) throws AppException;


//    //该方法运行在子线程中
//    //该方法可以在执行http请求之前，先在数据库中查询是否已缓存，如果已缓存，则从数据库中取出，不再执行http查询
//    @Override
//    public T preRequest() {
//        return null;
//    }
//
//    //该方法运行在子线程中，子类可实现该方法，用于修改数据，或者进行一些db操作
//    @Override
//    public T postRequest(T t) {
//        return null;
//    }
//
//    @Override
//    public void updateProgress(Integer curLen, Integer totalLen) {
//
//    }
}
