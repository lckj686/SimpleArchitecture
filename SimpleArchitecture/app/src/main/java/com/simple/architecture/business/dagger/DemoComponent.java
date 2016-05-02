package com.simple.architecture.business.dagger;

import com.simple.architecture.business.mvp.ui.FillFormActivity;

import dagger.Component;

/**
 * Created by sucer on 2016/5/1.
 */
@PerActivity
@Component(modules = {DemoModule.class})
public interface DemoComponent {


//    void inject(Activity homeActivity);
    void inject(FillFormActivity homeActivity);
    void inject(DemoP homeActivity);

    DemoP getDemoP();
}
