package com.blue.moudle.http.retrofit.wrapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.blue.moudle.bean.ResEntity;
import com.blue.moudle.http.ErrorHttpLoic;
import com.blue.moudle.http.HttpUser;

import java.lang.reflect.Type;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by sucer on 2016/6/5.
 */
public abstract class RetrofitCustSubscriber<T> extends Subscriber<ResEntity> implements HttpUser<T> {


    private Type type;
    private TypeReference<T> typeReference;

    public RetrofitCustSubscriber(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    public RetrofitCustSubscriber(Type type) {
        this.type = type;
    }


    @Override
    public void onError(Throwable e) {

        if (e instanceof HttpException) {
            onHttpFailure(e);
        } else {
            onLogicFailure(ErrorHttpLoic.E_RX, null, e);
        }


    }

    @Override
    public void onNext(ResEntity entity) {
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
    public void onCompleted() {

    }
}
