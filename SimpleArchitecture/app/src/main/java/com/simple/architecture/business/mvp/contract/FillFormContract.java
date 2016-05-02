package com.simple.architecture.business.mvp.contract;

import com.simple.architecture.business.mvp.bean.FormEvent;

import rx.Observable;

/**
 * Created by sucer on 2016/4/23.
 */
public interface FillFormContract {

     interface Ui {

        Observable<FormEvent> onEditInputChangeCompleted();

         void show(String name, String vale);

    }


}
