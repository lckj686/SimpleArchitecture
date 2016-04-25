package com.simple.architecture.business.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.simple.architecture.frame.net.retrofit.DemoRetrofit;
import com.simple.architecture.business.rxjava.OperateSubject;


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
