package com.blue.moudle.http;

/**
 * Created by sucer on 2016/6/5.
 */
public enum ErrorHttpLoic {

    E_JSON_PARSE_NUll(-1201),//第一次的内容为空
    E_JSON_PARSE(-1202),//json解析出错
    E_RX(-1203),//rx 出错和http错误有可能分不开
    E_BUSINESS_LOGIC(-1204);//业务


    private int value;

    private ErrorHttpLoic(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
