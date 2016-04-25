package com.simple.architecture.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.simple.architecture.NetUtil.retrofit.DemoRetrofit;
import com.simple.architecture.rxjava.OperateSubject;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private Button button_run_scheduler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        OperateSubject subject = new OperateSubject();
        subject.publishSubject();

        new DemoRetrofit().request();
    }


}
