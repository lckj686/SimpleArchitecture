package com.blue.moudle.http.retrofit;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface RequestApi {

    @GET
    Call<Object> requestFullUrl(@Url String s);

//    @Headers("Cache-Control: public, max-age=12")
    @GET("/appstore/new1")
    Observable<HashMap<String, Object>> requestDemo(@QueryMap Map<String, Object> map);

    @POST("/appstore/new1")
    Observable<HashMap<String, Object>> requestDemo3(@Body Map<String, Object> map);
}
