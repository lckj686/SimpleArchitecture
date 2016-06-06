package com.blue.moudle.http.retrofit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.blue.utils.NetUtils;

import java.io.File;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description：
 * okhttp 的缓存。 http header缓存有配则按缓存走，无网络的时候读本地。
 * 没有配置不缓存，无网络的时候也不读本地
 * Created by sucer on 2016/6/4.
 */
public class CacheOkhttp {
    static private final String TAG = CacheOkhttp.class.getSimpleName();

    static public OkHttpClient getCacheClient(Context context) {
        //设置缓存路径
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        Log.d(TAG, "httpCacheDirectory = " + httpCacheDirectory);
        //设置缓存 10M
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);


        /**
         * 云端响应头拦截器，用来配置缓存策略。 若未设置无网络不缓存，有设置无网络读缓存
         * Dangerous interceptor that rewrites the server's cache-control header.
         */
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            //@Headers里的配置
            String cacheControl = request.cacheControl().toString();

            //无网络
            if (!NetUtils.isConnected(context)) {
                //规避无缓冲设置
                if (TextUtils.isEmpty(cacheControl)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .build();
                } else {
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
            } else if (TextUtils.isEmpty(cacheControl)) {//规避未设置
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
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache).build();

        return client;
    }
}
