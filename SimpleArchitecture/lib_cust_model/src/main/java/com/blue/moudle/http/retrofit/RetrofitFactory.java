package com.blue.moudle.http.retrofit;

import android.app.Application;

import com.blue.moudle.http.retrofit.converter.FastConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Description: retrofit 初始化配置
 * Created by liw on 2016/6/4.
 */
public class RetrofitFactory {
    private final String TAG = RetrofitFactory.class.getSimpleName();

    public Retrofit retrofit = null;
    private RetrofitFactory retrofitFactory;
    private OkHttpClient client;

//    synchronized public RetrofitFactory getInstance() {
//        if (retrofitFactory == null) {
//            retrofitFactory = new RetrofitFactory();
//        }
//        return retrofitFactory;
//    }


    /**
     * 添加缓存
     *
     * @param appContext 缓存用的全局上下文
     */
    public RetrofitFactory addCacheClient(Application appContext) {
        client = CacheOkhttp.getCacheClient(appContext);
        return this;
    }

    public Retrofit build() {
        if (client == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ConstUrl.BASE_URL)
                    .addConverterFactory(FastConverterFactory.create())//json
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjav
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ConstUrl.BASE_URL)
                    .client(client)
                    .addConverterFactory(FastConverterFactory.create())//json
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjav
                    .build();
        }
        return retrofit;
    }


}
