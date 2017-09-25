package com.example.thirdframedemo.httpUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by wn123 on 2017/5/19.
 */

public class HttpConnectionUtils {

    private static final int TIME=15*3000;

    public static String execute(MyRequest request){
        switch (request.method){
            case GET:
                get(request);
                break;
            case POST:
                post(request);
                break;
            case DELETE:
                break;
            case PUT:
                break;
            default:
                break;
        }
        return null;
    }

    private static String get(MyRequest request) {
        HttpURLConnection connection=null;
//        BufferedReader bs=null;
//        String response="";
        ByteArrayOutputStream out=null;
        InputStream is=null;
        try {
            connection= (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setReadTimeout(TIME);
            connection.setConnectTimeout(TIME);

            addHeaders(request.headers, connection);
            out=new ByteArrayOutputStream();
            is=connection.getInputStream();
            byte[] buffer=new byte[2048];
            int len;
            while ((len=is.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is!=null){
                    is.close();
                }
                if (out!=null){
                    out.flush();
                    out.close();
                }
                if (connection!=null){
                    connection.disconnect();
                }
                return new String(out.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String post(MyRequest request) {
        HttpURLConnection connection=null;
        ByteArrayOutputStream out=null;
        InputStream is=null;

        try {
            connection= (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setReadTimeout(TIME);
            connection.setConnectTimeout(TIME);
            connection.setDoOutput(true);

            addHeaders(request.headers, connection);

            connection.addRequestProperty("content-type","application/json");

            OutputStream os=connection.getOutputStream();
            os.write(request.content.getBytes());

            out=new ByteArrayOutputStream();
            is=connection.getInputStream();
            byte[] buffer=new byte[2048];
            int len;
            while ((len=is.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is!=null){
                    is.close();
                }
                if (out!=null){
                    out.flush();
                    out.close();
                }
                if (connection!=null){
                    connection.disconnect();
                }
                return new String(out.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void addHeaders(Map<String, String> headers, HttpURLConnection connection) {
        for(Map.Entry<String,String> entry:headers.entrySet()){
            connection.addRequestProperty(entry.getKey(),entry.getValue());  //添加请求头
        }
    }
}
