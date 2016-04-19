package com.simple.architecture.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simple.architecture.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sucer on 2016/4/19.
 */
public class RxDemonActivity extends Activity {


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
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_rx_mvp_demo);
        ButterKnife.bind(this);


    }
}
