package com.simple.architecture.business.mvp.contract;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * Created by sucer on 2016/4/19.
 */
public class RxAskContract {
    public interface AskPresenter {
//        void ask(String a);
        void run();

    }

    public interface AskUi {
//        String getAsk();
//        void setAck(String s);

        Observable<String> getAskObservable();
        Observer<String> ackObserver();
        Action1 askAction();

    }
}
