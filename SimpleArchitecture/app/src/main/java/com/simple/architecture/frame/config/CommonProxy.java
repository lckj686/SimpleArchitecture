package com.simple.architecture.frame.config;

import android.app.Application;

import com.simple.architecture.business.demo.CustApplication;

/**
 * Description:
 * Created by liw on 2016/4/27.
 */
public class CommonProxy {
    static CommonProxy this_;
    static Application application = CustApplication.instance;

    static public CommonProxy getInstance() {
        if (this_ == null) {
            this_ = new CommonProxy();
            return this_;
        }
        return this_;
    }

    static public Application getApplicationContext(){
        return application;
    }

}
