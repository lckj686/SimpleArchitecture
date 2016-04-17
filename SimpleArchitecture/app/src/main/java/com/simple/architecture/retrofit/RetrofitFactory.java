package com.simple.architecture.retrofit;

import com.simple.architecture.retrofit.converter.FastConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Description:
 * Created by liw on 2016/4/15.
 */
public class RetrofitFactory {

    public static final String BASE_URL = "http://api.wukongtv.com";


    private static Retrofit retrofit = null;

    synchronized public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(FastConverterFactory.create())
                    .addConverterFactory(FastConverterFactory.create())//json
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjav
                    .build();
        }
        return retrofit;

    }

}
