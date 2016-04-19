package com.simple.architecture.mvp.presenter;

import com.simple.architecture.mvp.contract.RxAskContract;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by sucer on 2016/4/19.
 */
public class RxAskP implements RxAskContract.AskPresenter{
    RxAskContract.AskUi ui;
    Subject bus = new SerializedSubject<>(PublishSubject.create());

    public RxAskP(RxAskContract.AskUi ui){
        this.ui = ui;


    }


    @Override
    public void run() {
        ui.getAskObservable().subscribe(bus);
        bus.subscribe(ui.ackObserver());

    }
}
