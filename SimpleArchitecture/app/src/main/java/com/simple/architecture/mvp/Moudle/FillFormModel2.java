package com.simple.architecture.mvp.Moudle;

import android.text.TextUtils;
import android.util.Log;

import com.simple.architecture.db.Form;
import com.simple.architecture.mvp.Bean.FormEvent;

import org.litepal.crud.DataSupport;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by sucer on 2016/4/23.
 */
public class FillFormModel2 {
    private final String TAG = "FillFormModel2";

    /**
     * 接收到信号存储起来
     *
     * @return
     */
    public Observer<FormEvent> updateForm() {

        return new Observer<FormEvent>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "e2 = " + e);
            }


            @Override
            public void onNext(FormEvent event) {
                Log.d(TAG, "evebt = " + event);
                if (event == null || TextUtils.isEmpty(event.tag)) {

                    return;
                }

                Form form = new Form();
                form.attr_value = event.value;
                form.attrr_name = event.tag;

                int val = form.updateAll("attrr_name = ?", event.tag);
                if (val == 0) {
                    form.save();
                }

//                List<Form> list = DataSupport.where("attrr_name = ?", event.tag).find(Form.class);
//                if (PreconditionsUtil.isEmpty(list)) {
//                    form.save();
//                }else{
//                    form.updateAll("attrr_name = ?", event.tag);
//                }
            }
        };

    }

    /**
     * 读取本地数据
     *
     * @return
     */
    public Observable<List<Form>> lastData() {
        return Observable.create(new Observable.OnSubscribe<List<Form>>() {
            @Override
            public void call(Subscriber<? super List<Form>> subscriber) {
                List<Form> list = DataSupport.where("attrr_name IS NOT NULL").find(Form.class);
                subscriber.onNext(list);
            }
        });
    }

}
