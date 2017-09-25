package com.example.httpmodule.utils.myhttputils.httpcallback;

import com.example.httpmodule.utils.myhttputils.HttpStatus;
import com.example.httpmodule.utils.myhttputils.httpException.AppException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;


/**
 * Created by 吴城林 on 2017/7/20.
 */

public abstract class FileCallBack extends IRequestCallBack<String> {

    private String path;
    private OnProgressUpdataListener listener;

//    private volatile boolean isCancelled;

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
//    public String parse(HttpURLConnection connection) throws AppException{
//        return this.parse(connection,null);
//    }

    @Override
    public String parse(HttpURLConnection connection, OnProgressUpdataListener listener) throws AppException{
        this.listener=listener;
        try {
            checkIfCancelled();
            int statusCode=connection.getResponseCode();
            if (statusCode == HttpStatus.STATUS_OK) {
                File f = new File(path);
                if (f.exists()) {
                    f.delete();
                } else {
                    f.mkdirs();
                }

                FileOutputStream out = new FileOutputStream(f);
                InputStream is = connection.getInputStream();

                int totalLen = connection.getContentLength();
                int curLen = 0;
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    checkIfCancelled();
                    out.write(buffer, 0, len);
                    curLen += len;
                    onPrgressUpdata(curLen,totalLen);
                }
                is.close();
                out.flush();
                out.close();
    //                connection.disconnect();
                return path;
            }else {
                throw new AppException(statusCode,connection.getResponseMessage());
            }
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.TIME_OUT,e.getLocalizedMessage());
        }
    }


//    @Override
//    public String preRequest() {
//        return null;
//    }
//
//    @Override
//    public String postRequest(String s) {
//        return null;
//    }

    private void onPrgressUpdata(int curLen, int totalLen) {
        listener.update(curLen,totalLen);
    }


    public IRequestCallBack setCachePath(String path) {
        this.path = path;
        return this;
    }

}
