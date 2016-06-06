package com.blue.moudle.http;

/**
 * Created by sucer on 2016/6/4.
 */
public interface HttpUser<T> {
    void onLogicResponse(T t, String src);

    void onLogicFailure(ErrorHttpLoic code, String msg, Throwable t);

    void onHttpFailure(Throwable t);
}
