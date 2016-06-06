/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.blue.moudle.http.retrofit;

import android.app.Application;
import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.blue.moudle.bean.DemoEntity;
import com.blue.moudle.bean.ResEntity;
import com.blue.moudle.http.ErrorHttpLoic;
import com.blue.moudle.http.retrofit.wrapper.RetrofitCustCallBack;
import com.blue.moudle.http.retrofit.wrapper.RetrofitCustSubscriber;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class DemoRetrofitTest {
    private String TAG = DemoRetrofitTest.class.getSimpleName();
    RequestApi apiService;// = RetrofitFactory.getInstance().create(RequestApi.class);

    long lastTime = 0;

    public void init(Application context) {
        apiService = new RetrofitFactory().addCacheClient(context).build().create(RequestApi.class);
    }

    public void request() {

        requestPackageRx();
    }

    public void requestPackageRx() {


        apiService.requestRx()
                .subscribeOn(Schedulers.newThread())
                .subscribe(new RetrofitCustSubscriber<DemoEntity>(new TypeReference<DemoEntity>() {
                }) {
                    @Override
                    public void onLogicResponse(DemoEntity demoEntity, String src) {
                     Log.d(TAG, "res = " + demoEntity);

                    }

                    @Override
                    public void onLogicFailure(ErrorHttpLoic code, String msg, Throwable t) {
                        Log.w(TAG, "e = " + t);
                    }

                    @Override
                    public void onHttpFailure(Throwable t) {
                        Log.w(TAG, "e = " + t);
                    }


                });
    }

    public void requestPackage() {
        Call<ResEntity> call = apiService.request();
        call.enqueue(new RetrofitCustCallBack<DemoEntity>(new TypeReference<DemoEntity>() {
        }) {
            @Override
            public void onLogicResponse(DemoEntity o, String src) {

                Log.d(TAG, "res = " + o);
            }

            @Override
            public void onLogicFailure(ErrorHttpLoic code, String msg, Throwable t) {

                Log.e(TAG, "e = " + t);
            }

            @Override
            public void onHttpFailure(Throwable t) {

            }


        });
    }

    public void requestFullUrl() {

        Call<Object> call = apiService.requestFullUrl("http://www.baidu.com");
        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Log.d(TAG, "call url = " + call.request().url());
                Log.d(TAG, "  class= " + response.body().getClass().getSimpleName() + "  " + response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d(TAG, "  " + t.toString());
            }


        });
    }

    public void request2() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("c", "huawei");
        map.put("v", 200);
        map.put("dev", "android");
        map.put("start", 0);
        map.put("num", 30);
        map.put("hash", 0);
        apiService.requestDemo(map)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<HashMap<String, Object>>() {

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted  ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError  " + e.toString());
                    }

                    @Override
                    public void onNext(HashMap<String, Object> o) {
                        long current = System.currentTimeMillis() / 1000;
                        Log.d(TAG, (current - lastTime) + "  onNext  " + o.toString());
                        lastTime = current;
                    }
                });
    }

    public void request3() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("c", "huawei");
        map.put("v", 200);
        map.put("dev", "android");
        map.put("start", 0);
        map.put("num", 30);
        map.put("hash", 0);
        apiService.requestDemo3(map)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<HashMap<String, Object>>() {

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted  ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError  " + e.toString());
                    }

                    @Override
                    public void onNext(HashMap<String, Object> o) {
                        Log.d(TAG, "onNext  " + o.toString());
                    }
                });
    }
}
