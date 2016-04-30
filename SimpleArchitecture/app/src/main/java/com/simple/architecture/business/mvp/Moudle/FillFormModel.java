package com.simple.architecture.business.mvp.Moudle;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.Log;

import com.frame.utils.EmptyUtil;
import com.simple.architecture.R;
import com.simple.architecture.business.utils.IdNameFactory;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sucer on 2016/4/23.
 */
public class FillFormModel {
    private String TAG = "FillFormModel";



    public void createtable(Context context) {

        Observable.create(new Observable.OnSubscribe<List<String>>() {


            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> idList = new ArrayList<String>();
                final XmlResourceParser parser = context.getResources().getLayout(R.layout.activity_fill_form);
//                final AttributeSet attrs = Xml.asAttributeSet(parser);
                int eventType = 0;// 获取事件类型
                try {
                    eventType = parser.getEventType();
                    boolean isEnd = false;
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                                // 实例化集合类

                                break;
                            case XmlPullParser.START_TAG://开始读取某个标签
                                //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                                String name = parser.getName();
                                if (name.equalsIgnoreCase("EditText")) {

                                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                                        String attribute = parser.getAttributeName(i);
                                        if (attribute.equalsIgnoreCase("id")) {
                                            String id2 = parser.getAttributeValue(parser.getAttributeNamespace(0), "id");
                                            if (!TextUtils.isEmpty(id2)) {
                                                idList.add(IdNameFactory.getIdNameFromXml(id2));
                                            }
                                            Log.d(TAG, "id = " + id2);
                                        }
                                    }

                                }
                                break;
                            case XmlPullParser.END_TAG:// 结束元素事件
                                //读完一个Person，可以将其添加到集合类中
                                isEnd = true;

                                break;
                        }
                        if (!isEnd){
                            eventType = parser.next();
                        }

                    }

                   // parser.close();
                    subscriber.onNext(idList);
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        })
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "onError :" + e);
                    }

                    @Override
                    public void onNext(List<String> list) {
                        String CREATE_LIST = "CREATE TABLE " + "form" + " ( ";
                        String val = "view_id" + " INTEGER NOT NULL PRIMARY KEY, ";
                        if (!EmptyUtil.isEmpty(list)) {
                            for (int i = 0; i < list.size() - 1; i++) {
                                val += " " + list.get(i) + " TEXT NOT NULL, ";
                            }
                            val += " " + list.get(list.size() - 1) + " TEXT NOT NULL ";

                        }

                        String exe = CREATE_LIST + val + " )";
                        Log.d(TAG, "" + exe);
//                        CustApplication.db.execute(exe);
                    }


                });


    }

}
