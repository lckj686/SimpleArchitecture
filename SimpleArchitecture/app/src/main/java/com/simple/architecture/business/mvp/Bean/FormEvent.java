package com.simple.architecture.business.mvp.bean;

import javax.inject.Inject;

/**
 * Created by sucer on 2016/4/23.
 */
public class FormEvent {
    public String value;
    public String tag;

    @Inject
    public FormEvent(){

    }

    @Override
    public String toString() {
        return "FormEvent{" +
                "value='" + value + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
