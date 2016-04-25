package com.simple.architecture.business.demo;

import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.Stetho;
import com.simple.architecture.frame.imageLoader.fresco.ImageLoaderFresso;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

/**
 * Created by sucer on 2016/4/18.
 */
public class CustApplication extends LitePalApplication {
//    public static SqlBrite sqlBrite;
//    public static BriteDatabase db;
    public static CustApplication instance;
    SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        ImageLoaderFresso.getInstance().init(this);
        initDb();
        initStetho();


    }

    private void initDb() {
        LitePalApplication.initialize(this);
        db  = Connector.getDatabase();

    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }
}
