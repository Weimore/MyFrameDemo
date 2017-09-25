package com.example.httpmodule.utils.myhttputils.httpException;

/**
 * Created by 吴城林 on 2017/7/23.
 */

//监听全局Exception的接口
public interface OnGlobalExceptionListener {
    boolean handleGlobalException(AppException e);
}
