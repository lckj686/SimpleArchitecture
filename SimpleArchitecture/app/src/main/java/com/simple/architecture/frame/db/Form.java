package com.simple.architecture.frame.db;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by sucer on 2016/4/23.
 */
public class Form extends DataSupport {

//    @Column(unique = true, defaultValue = "unknown")
//    public String view_id;
    public String attr_value;

    @Column(unique = true, defaultValue = "unknown")
    public String attrr_name;

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }

    public String getAttrr_name() {
        return attrr_name;
    }

    public void setAttrr_name(String attrr_name) {
        this.attrr_name = attrr_name;
    }

    @Override
    public String toString() {
        return "Form{" +
                "attr_value='" + attr_value + '\'' +
                ", attrr_name='" + attrr_name + '\'' +
                '}';
    }
}
