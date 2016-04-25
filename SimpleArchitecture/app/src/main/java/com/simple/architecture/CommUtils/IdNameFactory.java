package com.simple.architecture.CommUtils;

import com.simple.architecture.demo.CustApplication;

/**
 * Created by sucer on 2016/4/23.
 */
public class IdNameFactory {
    static public String getIdName(int id) {
        String idName = CustApplication.instance.getResources().getResourceName(id);
        String[] ids = idName.split("/");
        if (ids != null && ids.length >= 2) {
            return ids[1];
        }
        return null;
    }

    static public String getIdNameFromXml(String id2) {
        String[] srcId = id2.split("@");
        if (srcId != null && srcId.length >= 2) {

            return getIdName(Integer.valueOf(srcId[1].trim()));

        }
        return null;
    }


}
