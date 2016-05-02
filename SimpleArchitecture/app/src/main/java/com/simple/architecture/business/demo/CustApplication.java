package com.simple.architecture.business.demo;

import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;

import com.blue.frame.imageLoader.cust.CustImageLoader;
import com.blue.frame.log.LogDebugUtil;
import com.facebook.stetho.Stetho;
import com.simple.architecture.business.dagger.ApplicationComponent;
import com.simple.architecture.business.dagger.ApplicationModule;
import com.simple.architecture.business.dagger.DaggerApplicationComponent;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import javax.inject.Inject;

/**
 * Created by sucer on 2016/4/18.
 */
public class CustApplication extends LitePalApplication {

    public static CustApplication instance;
    SQLiteDatabase db;

    private ApplicationComponent component;
    @Inject
    LocationManager locationManager ;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        initDb();
        initStetho();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

      component.inject(this); // As of now, LocationManager should be injected into this.

        LogDebugUtil.d("application", "LocationManager = " + locationManager);
    }

    private void initDb() {
        LitePalApplication.initialize(this);
        db  = Connector.getDatabase();

    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void initImageloader(){
        CustImageLoader.getInstance().init(this);
    }

    private void initLog(){

    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
