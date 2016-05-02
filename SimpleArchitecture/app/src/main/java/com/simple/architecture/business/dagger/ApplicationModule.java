package com.simple.architecture.business.dagger;

import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sucer on 2016/5/1.
 */
@Module
public class ApplicationModule {
    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
    }

}
