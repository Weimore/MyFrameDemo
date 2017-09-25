package com.example.httpmodule.utils.myhttputils.httpException;

/**
 * Created by 吴城林 on 2017/7/22.
 */

public class AppException extends Exception {

    public int statusCode;
    public String responseMessage;
    public static enum ErrorType{
        SERVER,JSON, URL, CANCEL, TIME_OUT
    }

    public ErrorType errorType;

    public AppException() {
    }

    public AppException(ErrorType errorType,String message) {
        super(message);
        this.errorType=errorType;
    }

    public AppException(int statusCode, String responseMessage) {
        super(responseMessage);
        errorType= ErrorType.SERVER;
        this.statusCode=statusCode;
        this.responseMessage=responseMessage;
    }
}
