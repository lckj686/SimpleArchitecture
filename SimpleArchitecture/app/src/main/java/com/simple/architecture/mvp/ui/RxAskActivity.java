package com.simple.architecture.mvp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.simple.architecture.R;
import com.simple.architecture.mvp.contract.RxAskContract;
import com.simple.architecture.mvp.presenter.RxAskP;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by sucer on 2016/4/19.
 */
public class RxAskActivity extends Activity implements RxAskContract.AskUi {

    @Bind(R.id.tv_comment_tip)
    TextView tvCommentTip;
    @Bind(R.id.edt_comment)
    EditText edtComment;
    @Bind(R.id.btn_a)
    Button btnA;
    @Bind(R.id.btn_b)
    Button btnB;
    @Bind(R.id.btn_c)
    Button btnC;
    @Bind(R.id.tv_ack_tip)
    TextView tvAckTip;
    @Bind(R.id.tv_ack)
    TextView tvAck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_mvp_demo);
        ButterKnife.bind(this);

        new RxAskP(this).run();


    }

    @Override
    public Observable<String> getAskObservable() {
        Observable aObservable = RxView.clicks(btnA).map(new Func1() {
            @Override
            public Object call(Object o) {
                return "A: " + edtComment.getText().toString();
            }
        });


        Observable bObservable = RxView.clicks(btnB).map(new Func1() {
            @Override
            public Object call(Object o) {
                return "B" + edtComment.getText().toString();
            }
        });
        Observable cObservable = RxView.clicks(btnC).map(new Func1() {
            @Override
            public Object call(Object o) {
                return "C" + edtComment.getText().toString();
            }
        }).mergeWith(aObservable).mergeWith(bObservable);


        return cObservable;
    }

    @Override
    public Observer<String> ackObserver() {


        return null;
    }

    @Override
    public Action1 askAction() {


        return RxTextView.text(tvAck);
    }

}
