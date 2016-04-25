package com.simple.architecture.rxjava.utils;

import android.widget.EditText;

import rx.Observable;

/**
 * Created by sucer on 2016/4/23.
 */
public class RxCustView {

    private RxCustView(){

    }

    static public Observable<String> editTextChange(EditText text) {
        return Observable.create(new EditTextChangeOnSubscribe(text));
    }

}
