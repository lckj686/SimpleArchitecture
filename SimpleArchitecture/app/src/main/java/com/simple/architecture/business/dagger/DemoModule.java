package com.simple.architecture.business.dagger;

import com.simple.architecture.business.mvp.bean.FormEvent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sucer on 2016/5/1.
 */
@Module
public class DemoModule {

    public DemoModule(){

    }

    @Provides
    FormEvent
    provideFormEvent(){
        return new FormEvent();
    }
}
