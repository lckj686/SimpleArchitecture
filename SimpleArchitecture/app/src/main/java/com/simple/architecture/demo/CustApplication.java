package com.simple.architecture.demo;

import android.app.Application;

import com.simple.architecture.ImageLoaderUtil.fresco.ImageLoaderFresso;

/**
 * Created by sucer on 2016/4/18.
 */
public class CustApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderFresso.getInstance().init(this);
    }
}
