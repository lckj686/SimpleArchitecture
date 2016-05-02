package com.simple.architecture.business.mvp.prefsenter;

import android.util.Log;

import com.simple.architecture.business.mvp.contract.FillFormContract;
import com.simple.architecture.business.mvp.model.FillFormModel2;
import com.blue.moudle.db.Form;

import java.util.List;

import rx.Subscriber;


/**
 * Created by sucer on 2016/4/24.
 */
public class FillFormPresenter {
    FillFormContract.Ui ui;
    FillFormModel2 model2 = new FillFormModel2();

    public FillFormPresenter(FillFormContract.Ui ui) {
        this.ui = ui;
        run();
    }

    public void run() {
        ui.onEditInputChangeCompleted().subscribe(model2.updateForm());

        model2.lastData().subscribe(new Subscriber<List<Form>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.e("FillFormPresenter", "" + e.getMessage());
            }

            @Override
            public void onNext(List<Form> forms) {
                Log.e("FillFormPresenter", "" + forms);
                for (Form form : forms) {

                    ui.show(form.attrr_name, form.attr_value);
                }
            }
        });
    }


}
