package com.blue.moudle.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sucer on 2016/4/23.
 */
public class DbUtil {
    private String TABLE_NAME = "srcForm";


    public String sqlCreateTableNotExit(String tableName) {
        String queryStr = "create table if not exists %s ";
        return String.format(queryStr, tableName);

    }

    public String sqlAddField(String tableName, String fieldName) {
        String queryStr = "alter table %s add %s TEXT";
        return String.format(queryStr, tableName, fieldName);
    }



    /**
     * 判断某表里某字段是否存在
     *
     * @param db
     * @param tableName
     * @param fieldName
     * @return
     */
    public boolean isFieldExist(SQLiteDatabase db, String tableName, String fieldName) {
        String queryStr = "select sql from sqlite_master where type = 'table' and name = '%s'";
        queryStr = String.format(queryStr, tableName);
        Cursor c = db.rawQuery(queryStr, null);
        String tableCreateSql = null;
        try {
            if (c != null && c.moveToFirst()) {
                tableCreateSql = c.getString(c.getColumnIndex("sql"));
            }
        } finally {
            if (c != null)
                c.close();
        }
        if (tableCreateSql != null && tableCreateSql.contains(fieldName))
            return true;
        return false;
    }


}
