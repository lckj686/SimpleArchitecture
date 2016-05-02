package com.simple.architecture.business.dagger;

import android.util.Log;

import com.blue.frame.log.LogDebugUtil;
import com.simple.architecture.business.mvp.bean.FormEvent;

import javax.inject.Inject;

/**
 * Created by sucer on 2016/5/1.
 */
public class DemoP {

    @Inject
    FormEvent event;


    public FormEvent getEvent() {
        return event;
    }

    @Inject
    void set(){
        LogDebugUtil.d("DemoP", "------set()---");
    }

    @Inject
    public DemoP(){
//        DaggerDemoComponent.builder().demoModule(new DemoModule()).build().inject(this);
        Log.d("demop", "event = " + event);
    }

}
