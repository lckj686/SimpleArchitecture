package com.simple.architecture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.simple.architecture.master.R;
import com.simple.architecture.rxjava.OperateSubject;
import com.simple.architecture.retrofit.DemoRetrofit;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private Button button_run_scheduler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OperateSubject subject = new OperateSubject();
        subject.publishSubject();

        new DemoRetrofit().request();
    }


}
