package com.simple.architecture.mvp.moudle;

import rx.Observable;

/**
 * Created by sucer on 2016/4/19.
 */
public class RxRequest {

    public static Observable request(String s){

        return Observable.just(s);
    }


}
