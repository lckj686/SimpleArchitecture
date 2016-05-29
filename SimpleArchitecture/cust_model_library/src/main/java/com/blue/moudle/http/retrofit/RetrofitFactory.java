package com.blue.moudle.http.retrofit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.blue.moudle.http.retrofit.converter.FastConverterFactory;
import com.blue.utils.NetUtils;

import java.io.File;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Description:
 * Created by liw on 2016/4/15.
 */
public class RetrofitFactory {

    private final String TAG = "RetrofitFactory";
    public static final String BASE_URL = "http://api.wukongtv.com";


    public Retrofit retrofit = null;
    static OkHttpClient client;

    private Context context;
    private Interceptor interceptor;

    public RetrofitFactory(Context context) {
        this.context = context;
        init();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(FastConverterFactory.create())
                .addConverterFactory(FastConverterFactory.create())//json
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjav
                .build();


    }


    private final Interceptor LoggingInterceptor = chain -> {
        Request request = chain.request();
        long t1 = System.nanoTime();
//        Logger.t(TAG).i(String.format("Sending request %s on %s%n%s", request.url(),  chain.connection(), request.headers()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
//        Logger.t(TAG).i(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    };

    public void init() {
        //设置缓存路径
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        Log.d(TAG, "httpCacheDirectory = " + httpCacheDirectory);
        //设置缓存 10M
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);


//        client = new OkHttpClient.Builder()
//                .cache(cache).build();
//        client.networkInterceptors().add(interceptor);
//        client.interceptors().add(interceptor);

        /**
         * 云端响应头拦截器，用来配置缓存策略
         * Dangerous interceptor that rewrites the server's cache-control header.
         */
        interceptor = chain -> {
            Request request = chain.request();
            //@Headers里的配置
            String cacheControl = request.cacheControl().toString();

            //无网络
            if (!NetUtils.isConnected(context) ) {
                //规避无缓冲设置
                if (TextUtils.isEmpty(cacheControl)){
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .build();
                }else {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

//            Logger.t(TAG).w("no network");



            }
            Response originalResponse = chain.proceed(request);


            Log.d(TAG, "cacheControl = " + cacheControl);

            if (NetUtils.isConnected(context)) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置

                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else if (TextUtils.isEmpty(cacheControl)) {
                return originalResponse.newBuilder()
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        };

        //创建OkHttpClient，并添加拦截器和缓存代码
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache).build();
    }

}
