package com.simple.architecture.business.mvp.Bean;

import java.util.Arrays;

/**
 * Created by sucer on 2016/4/23.
 */
public class FormBean {
    private String name;
    private String age;
    private String height;
    private String weight;
    private String[] address;//地址
    private String shippingAddress;//默认收货地址
    private String hobby;//兴趣

    private String codeTips;
    private String phone;
    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCodeTips() {
        return codeTips;
    }

    public void setCodeTips(String codeTips) {
        this.codeTips = codeTips;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "FormBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", address=" + Arrays.toString(address) +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", hobby='" + hobby + '\'' +
                ", codeTips='" + codeTips + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
