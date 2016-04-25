package com.simple.architecture.business.rxjava.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

/**
 * editText有修改
 * Created by sucer on 2016/4/23.
 */
public class EditTextChangeOnSubscribe implements Observable.OnSubscribe<String> {
    final EditText view;
    private String srcString;


    public EditTextChangeOnSubscribe(EditText view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super String> subscriber) {
        checkUiThread();

        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    srcString =  view.getText().toString();
                } else {
                    String target = view.getText().toString() ;
                    if (!TextUtils.isEmpty(target) && !target.equals(srcString)) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(target);
                        }
                    }

                }

            }
        };
        view.setOnFocusChangeListener(listener);

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnFocusChangeListener(null);
            }
        });

        // Emit initial value.
//        subscriber.onNext("");
    }
}
