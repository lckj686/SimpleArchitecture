package com.simple.architecture.business.mvp.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.simple.architecture.R;
import com.simple.architecture.business.dagger.DaggerDemoComponent;
import com.simple.architecture.business.dagger.DemoComponent;
import com.simple.architecture.business.dagger.DemoModule;
import com.simple.architecture.business.demo.CustApplication;
import com.simple.architecture.business.mvp.BaseActivity;
import com.simple.architecture.business.mvp.bean.FormEvent;
import com.simple.architecture.business.mvp.contract.FillFormContract;
import com.simple.architecture.business.mvp.prefsenter.FillFormPresenter;
import com.simple.architecture.business.rxjava.utils.RxCustView;
import com.simple.architecture.business.utils.IdNameFactory;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by sucer on 2016/4/23.
 */
public class FillFormActivity extends BaseActivity implements FillFormContract.Ui {

    @Bind(R.id.edt_fillform_name)
    EditText edtFillformName;
    @Bind(R.id.edt_fillform_eage)
    EditText edtFillformEage;
    @Bind(R.id.edt_fillform_height)
    EditText edtFillformHeight;
    @Bind(R.id.edt_fillform_weight)
    EditText edtFillformWeight;
    @Bind(R.id.edt_fillform_address)
    EditText edtFillformAddress;
    @Bind(R.id.edt_fillform_phone)
    EditText edtFillformPhone;
    @Bind(R.id.edt_fillform_email)
    EditText edtFillformEmail;
    @Bind(R.id.edt_fillform_more)
    EditText edtFillformMore;

    List<EditText> list;

    private Context mContext;
    private String TAG = "FillFormActivity";

//    @Inject
//    Context applicationContext;

    @Inject
    FormEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);
        ButterKnife.bind(this);
        mContext = this;

        list = Arrays.asList(new EditText[]{edtFillformName, edtFillformAddress, edtFillformEage, edtFillformEmail, edtFillformHeight, edtFillformPhone, edtFillformWeight, edtFillformMore});
        CustApplication.instance.getComponent().injectA(this);

        DemoComponent demoComponent =
         DaggerDemoComponent.builder().demoModule(new DemoModule()).build();
        demoComponent.inject(this);



        new FillFormPresenter(this);


//        DemoP p = new DemoP();
//        LogDebugUtil.d(TAG, "demoP event = " + p.getEvent());

        Log.d(TAG, "event = " + demoComponent.getDemoP().getEvent());

    }


    @Override
    public Observable<FormEvent> onEditInputChangeCompleted() {


        Observable<FormEvent> observable = null;
        for (EditText edt : list) {
            Observable<FormEvent> tmpObservable = null;
            if (edt == edtFillformMore) {
                tmpObservable = RxTextView.textChanges(edt)
                        .map(new Func1<CharSequence, FormEvent>() {
                            @Override
                            public FormEvent call(CharSequence s) {
                                FormEvent event = new FormEvent();
                                event.value = String.valueOf(s);

                                try {
                                    event.tag = IdNameFactory.getIdName(edt.getId());
                                } catch (Exception e) {
                                    Log.e(TAG, "" + e);
                                }


                                return event;
                            }
                        }).filter(new Func1<FormEvent, Boolean>() {
                            @Override
                            public Boolean call(FormEvent event) {
                                if (edt.isFocused() == false) {
                                    return false;
                                }
                                return true;
                            }
                        });
            } else {
                tmpObservable = RxCustView.editTextChange(edt).map(new Func1<String, FormEvent>() {
                    @Override
                    public FormEvent call(String s) {
                        FormEvent event = new FormEvent();
                        event.value = s;

                        try {
                            event.tag = IdNameFactory.getIdName(edt.getId());
                        } catch (Exception e) {
                            Log.e(TAG, "" + e);
                        }


                        return event;
                    }
                });
            }


            if (observable == null) {
                observable = tmpObservable;
            } else {
                observable = observable.mergeWith(tmpObservable);
            }
        }

        return observable;
    }

    @Override
    public void show(String name, String vale) {

        for (EditText edit : list) {
            if (IdNameFactory.getIdName(edit.getId()).equals(name)) {
                edit.setText(vale);
            }
        }

    }
}
