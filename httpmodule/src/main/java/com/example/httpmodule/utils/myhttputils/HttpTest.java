package com.example.httpmodule.utils.myhttputils;

import com.example.httpmodule.model.User;
import com.example.httpmodule.utils.LogUtils;
import com.example.httpmodule.utils.myhttputils.httpException.AppException;
import com.example.httpmodule.utils.myhttputils.httpcallback.FileCallBack;
import com.example.httpmodule.utils.myhttputils.httpcallback.JsonCallBack;


/**
 * Created by 吴城林 on 2017/7/17.
 */

public class HttpTest {



    public static void get(){
        String url="";
        MyRequest request=new MyRequest(url);
//        String response= HttpConnectionUtils.execute(request);
    }

    public static void post(){
        String url="";
        MyRequest request=new MyRequest(url, MyRequest.RequestMethod.POST);
//        String response= HttpConnectionUtils.execute(request);
    }

    public static void execute(){
        String url="http://v.juhe.cn/toutiao/index?type=top&key=19cd561baff8c8c66d700601a2145ecc";
        MyRequest request=new MyRequest(url, MyRequest.RequestMethod.GET);
        request.setCallBack(new JsonCallBack<User> (){
            @Override
            public void onSuccess(User response) {
                LogUtils.d(response.toString());
            }

            @Override
            public void onFailed(AppException e) {
                LogUtils.e(e.toString());
            }
        });
        RequestTask task=new RequestTask(request);
        task.execute();
    }

    public static void download(){
        String url="http://v.juhe.cn/WNXG/city?key=19cd561baff8c8c66d700601a2145ecc";
        MyRequest request=new MyRequest(url, MyRequest.RequestMethod.GET);
        request.setCallBack(new FileCallBack() {
            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onFailed(AppException e) {

            }

            @Override
            public void updateProgress(Integer curLen, Integer totalLen) {

            }
        });
        RequestTask task=new RequestTask(request);
        task.execute();
    }

}
