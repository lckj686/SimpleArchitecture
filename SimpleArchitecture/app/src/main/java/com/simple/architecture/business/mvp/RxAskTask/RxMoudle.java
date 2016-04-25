package com.simple.architecture.business.mvp.RxAskTask;

import rx.Observable;

/**
 * Created by sucer on 2016/4/19.
 */
public class RxMoudle {

    public static Observable request(String s){

        return Observable.just(s);
    }


}
