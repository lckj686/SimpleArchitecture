package com.blue.moudle.http.retrofit.wrapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.blue.moudle.bean.ResEntity;
import com.blue.moudle.http.ErrorHttpLoic;
import com.blue.moudle.http.HttpUser;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sucer on 2016/6/4.
 */
public abstract class RetrofitCustCallBack<T> implements Callback<ResEntity>, HttpUser<T> {


    private Type type;
    private TypeReference<T> typeReference;

    public RetrofitCustCallBack(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    public RetrofitCustCallBack(Type type) {
        this.type = type;
    }

    @Override
    final public void onResponse(Call<ResEntity> call, Response<ResEntity> response) {

        ResEntity entity = response.body();
        T t = null;
        if (entity != null) {
            try {

                if (type != null) {
                    t = JSON.parseObject(entity.getData(), type);
                } else if (typeReference != null) {
                    t = JSON.parseObject(entity.getData(), typeReference);
                }
                try {
                    onLogicResponse(t, entity.getData());
                } catch (Exception e) {
                    onLogicFailure(ErrorHttpLoic.E_BUSINESS_LOGIC, entity.getData(), e);
                }

                return;
            } catch (Exception e) {
                onLogicFailure(ErrorHttpLoic.E_JSON_PARSE, entity.getData(), e);
            }
        } else {
            onLogicFailure(ErrorHttpLoic.E_JSON_PARSE_NUll, null, new Exception("ERROR_JSON_PARSE_NUll"));
        }

    }

    @Override
    final public void onFailure(Call<ResEntity> call, Throwable t) {
        onHttpFailure(t);
    }
}
