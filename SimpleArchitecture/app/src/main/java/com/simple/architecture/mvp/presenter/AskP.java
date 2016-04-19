package com.simple.architecture.mvp.presenter;

import com.simple.architecture.mvp.contract.AskContract;
import com.simple.architecture.mvp.moudle.Request;

/**
 * Created by sucer on 2016/4/19.
 */
public class AskP implements AskContract.AskPresenter{
    AskContract.AskUi ui;

    public AskP(AskContract.AskUi ui){
        this.ui = ui;
    }


    @Override
    public void ask(String a) {

        String src = Request.request("ask " + a);
        ui.setAck(src);
    }
}
