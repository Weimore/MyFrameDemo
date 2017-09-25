package com.example.httpmodule.utils.myhttputils;

import android.webkit.URLUtil;

import com.example.httpmodule.utils.myhttputils.httpException.AppException;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by wn123 on 2017/5/19.
 */

public class HttpConnectionUtils {

    private static final int TIME = 15 * 3000;

    public static HttpURLConnection execute(MyRequest request) throws AppException {
        if (!URLUtil.isNetworkUrl(request.url)) {
            throw new AppException(AppException.ErrorType.URL, "this url:" + request.url + " is not valid");
        }
        switch (request.method) {
            case GET:
            case DELETE:
                return getOrDelete(request);
            case POST:
            case PUT:
                return postOrPut(request);
            default:
                break;
        }
        return null;
    }


    private static HttpURLConnection getOrDelete(MyRequest request) throws AppException {

        HttpURLConnection connection = null;
        try {
            request.checkIfCancelled();

            connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(TIME);
            connection.setReadTimeout(TIME);
            addHeaders(request.headers, connection);

            request.checkIfCancelled();

        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIME_OUT, e.getLocalizedMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getLocalizedMessage());
        }
        return connection;
    }

    private static HttpURLConnection postOrPut(MyRequest request) throws AppException {

        HttpURLConnection connection = null;
        try {
            request.checkIfCancelled();
            connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(TIME);
            connection.setReadTimeout(TIME);
            connection.setDoOutput(true);

            addHeaders(request.headers, connection);
            request.checkIfCancelled();

//            connection.addRequestProperty("content-type", "application/json");

            OutputStream os = connection.getOutputStream();
            os.write(request.content.getBytes());
            request.checkIfCancelled();

        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIME_OUT, e.getLocalizedMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getLocalizedMessage());
        }

        return connection;
    }


    //添加请求头
    private static void addHeaders(Map<String, String> headers, HttpURLConnection connection) {
        if (headers == null || headers.size() == 0)
            return;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }
}
