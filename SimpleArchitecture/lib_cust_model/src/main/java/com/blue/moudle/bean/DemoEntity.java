package com.blue.moudle.bean;

import java.util.List;

/**
 * Created by sucer on 2016/6/4.
 */
public class DemoEntity {

    private List<String> operate;
    private List<String> live;
    private List<String> app;

    public List<String> getOperate() {
        return operate;
    }

    public void setOperate(List<String> operate) {
        this.operate = operate;
    }

    public List<String> getLive() {
        return live;
    }

    public void setLive(List<String> live) {
        this.live = live;
    }

    public List<String> getApp() {
        return app;
    }

    public void setApp(List<String> app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return "DemoEntity{" +
                "operate=" + operate +
                ", live=" + live +
                ", app=" + app +
                '}';
    }
}
