package com.blue.frame.imageLoader.wrapper;

/**
 * custImageView 的属性
 * 圆角 背景图
 * Created by sucer on 2016/4/17.
 */
public class CustOption {

    //    public Integer defaultUrlR;
//    public Integer errorDrawableRes;
    public Integer defaultRes;
    public Boolean isRound;
    public Float corner;

    public static CustOption Build(){
        return new CustOption();
    }

    public CustOption setDefaultRes(Integer defaultRes) {
        this.defaultRes = defaultRes;
        return this;
    }

    public CustOption setRound(Boolean round) {
        isRound = round;
        return this;
    }

    public CustOption setCorner(Float corner) {
        this.corner = corner;
        return this;
    }
}
