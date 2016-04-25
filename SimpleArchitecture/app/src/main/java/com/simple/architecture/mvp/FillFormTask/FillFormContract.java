package com.simple.architecture.mvp.FillFormTask;

import com.simple.architecture.mvp.Bean.FormEvent;

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
