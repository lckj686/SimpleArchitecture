package com.simple.architecture.business.dagger;

import com.simple.architecture.business.demo.CustApplication;
import com.simple.architecture.business.mvp.ui.FillFormActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sucer on 2016/5/1.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(CustApplication application);
    void injectA(FillFormActivity homeActivity);
//    Context context();

}
