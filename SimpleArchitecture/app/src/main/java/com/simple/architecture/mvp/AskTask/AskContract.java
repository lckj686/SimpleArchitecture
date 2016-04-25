package com.simple.architecture.mvp.AskTask;

/**
 * Created by sucer on 2016/4/19.
 */
public interface AskContract {
    public interface AskPresenter {
        void ask(String a);

    }

    public interface AskUi {
        String getAsk();

        void setAck(String s);

    }
}
