package com.blue.moudle.bean;

/**
 * Created by sucer on 2016/6/4.
 */
public class ResEntity {

    /**
     * status : 0
     * hash : fa4e1bbc0b0b7eea
     * data : {}
     */

    private int status;
    private String hash;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResEntity{" +
                "status=" + status +
                ", hash='" + hash + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
