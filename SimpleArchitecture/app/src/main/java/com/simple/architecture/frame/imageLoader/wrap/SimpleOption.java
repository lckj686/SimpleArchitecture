package com.simple.architecture.frame.imageLoader.wrap;

/**
 * Created by sucer on 2016/4/17.
 */
public class SimpleOption {

    //    public Integer defaultUrlR;
//    public Integer errorDrawableRes;
    public Integer defaultRes;
    public Boolean isRound;
    public Float corner;


    public SimpleOption setDefaultRes(Integer defaultRes) {
        this.defaultRes = defaultRes;
        return this;
    }

    public SimpleOption setRound(Boolean round) {
        isRound = round;
        return this;
    }

    public SimpleOption setCorner(Float corner) {
        this.corner = corner;
        return this;
    }
}
