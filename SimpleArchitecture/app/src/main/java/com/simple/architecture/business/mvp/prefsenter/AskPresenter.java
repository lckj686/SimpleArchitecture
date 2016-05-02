package com.simple.architecture.business.mvp.prefsenter;

import com.simple.architecture.business.mvp.model.AskMoudle;
import com.simple.architecture.business.mvp.contract.AskContract;

/**
 * Created by sucer on 2016/4/19.
 */
public class AskPresenter implements AskContract.AskPresenter{
    AskContract.AskUi ui;

    public AskPresenter(AskContract.AskUi ui){
        this.ui = ui;
    }


    @Override
    public void ask(String a) {

        String src = AskMoudle.request("ask " + a);
        ui.setAck(src);
    }
}
