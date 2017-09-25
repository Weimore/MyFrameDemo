package com.example.httpmodule.utils.myhttputils;

import android.text.TextUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by 吴城林 on 2017/7/24.
 */

public class RequestManager {

    public static RequestManager requestManager = null;
    public HashMap<String, ArrayList<MyRequest>> mCacheRequest;
    private Executor mExecutor;


    public static RequestManager getInstance() {
        if (requestManager == null) {
            requestManager = new RequestManager();
        }
        return requestManager;
    }

    public RequestManager() {
        mExecutor= Executors.newFixedThreadPool(5);
        mCacheRequest = new HashMap<String, ArrayList<MyRequest>>();
    }

    public void performRequest(MyRequest request) {
       request.execute(mExecutor);
        if (!mCacheRequest.containsKey(request.tag)) {
            ArrayList<MyRequest> requests = new ArrayList<MyRequest>();
            mCacheRequest.put(request.tag, requests);
        }
        mCacheRequest.get(request.tag).add(request);
    }


    public void cancelRequest(String tag){
        this.cancelRequest(tag,false);
    }

    public void cancelRequest(String tag,boolean force) {
        if (TextUtils.isEmpty(tag.trim())) {
            return;
        }
        if (mCacheRequest.containsKey(tag)) {
            ArrayList<MyRequest> requests = mCacheRequest.remove(tag);
            for (MyRequest request : requests) {
                if (!request.isCancelled && tag.equals(request.tag)) {
                    request.cancel(force);
                }
            }
        }

    }


    public void cancelAll() {
        for (Map.Entry<String, ArrayList<MyRequest>> entry : mCacheRequest.entrySet()) {
            ArrayList<MyRequest> requests = entry.getValue();
            for (MyRequest request : requests) {
                if (!request.isCancelled) {
                    request.cancel(true);
                }
            }
        }
    }

}
