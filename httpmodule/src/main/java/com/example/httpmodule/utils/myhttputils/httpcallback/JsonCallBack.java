package com.example.httpmodule.utils.myhttputils.httpcallback;

import com.example.httpmodule.utils.myhttputils.httpException.AppException;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 吴城林 on 2017/7/20.
 */

public abstract class JsonCallBack<T> extends RequestCallBack<T> {

    @Override
    public T parseData(String result) throws AppException {
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(result);
            Object json=jsonObject.opt("result");
            Gson gson=new Gson();
            Type type=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return gson.fromJson(json.toString(),type);
        } catch (JSONException e) {
            throw new AppException(AppException.ErrorType.JSON,e.getLocalizedMessage());
        }
    }
}
