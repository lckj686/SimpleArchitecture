package com.simple.architecture.business.mvp.FillFormTask;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.simple.architecture.business.utils.IdNameFactory;
import com.simple.architecture.R;
import com.simple.architecture.business.mvp.BaseActivity;
import com.simple.architecture.business.mvp.Bean.FormEvent;
import com.simple.architecture.business.rxjava.utils.RxCustView;

import java.util.Arrays;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);
        ButterKnife.bind(this);
        mContext = this;

        list = Arrays.asList(new EditText[]{edtFillformName, edtFillformAddress, edtFillformEage, edtFillformEmail, edtFillformHeight, edtFillformPhone, edtFillformWeight, edtFillformMore});


        new FillFormPresenter(this);

//        onEditInputChangeCompleted().subscribe(new Action1<FormEvent>() {
//            @Override
//            public void call(FormEvent s) {
//                Log.d(TAG, "call - " + s);
//            }
//        });

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
