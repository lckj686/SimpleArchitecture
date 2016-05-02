package com.simple.architecture.business.mvp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simple.architecture.R;
import com.simple.architecture.business.mvp.contract.AskContract;
import com.simple.architecture.business.mvp.prefsenter.AskPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sucer on 2016/4/19.
 */
public class AskActivity extends Activity implements AskContract.AskUi {

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
    AskContract.AskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_mvp_demo);
        ButterKnife.bind(this);
        presenter = new AskPresenter(this);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ask("A " + getAsk());
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ask("B " + getAsk());
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ask("C " + getAsk());
            }
        });
    }

    @Override
    public String getAsk() {
        return edtComment.getText().toString();
    }



    @Override
    public void setAck(String s) {

        tvAck.setText(s);
    }
}
