package com.che.baseutil.orm.helper;

import android.database.Cursor;

/**
 * 字段转换器
 * <p/>
 * 作者：余天然 on 16/9/15 下午9:03
 */
public class TypeConverter {

    /**
     * 转换Create的类型
     * <p/>
     * 例如:TEXT、INTEGER
     */
    public static String convertCreate(String field, Class<?> type) {
        if (field.toLowerCase().equals("id".toLowerCase())) {
            return "INTEGER PRIMARY KEY AUTOINCREMENT";
        }
        if (type == String.class) {
            return "TEXT";
        }

        if (type == Integer.class) {
            return "INTEGER";
        }
        //根据情况else 其他数据类型，看着玩儿吧....
        return null;
    }

    /**
     * 转换Insert的值
     * <p/>
     * 例如:'Stay',23
     */
    public static String convertInsert(Class<?> type, Object value) {
        if (type == String.class) {
            return "'" + value + "'";
        }
        if (type == Integer.class) {
            return value.toString();
        }
        //根据情况else 其他数据类型，看着玩儿吧....
        return null;
    }


    /**
     * 转换Cursor的值
     * <p/>
     * 例如:'Stay',23
     */
    public static Object convertCursor(Cursor cursor, String filedName, Class<?> type) {
        if (type == Integer.class) {
            // TODO: 16/9/15 这里null会自动变成0，是个问题！
            Integer integer = cursor.getInt(cursor.getColumnIndex(filedName));
            return integer;
        }
        if (type == String.class) {
            String string = cursor.getString(cursor.getColumnIndex(filedName));
            return string;
        }
        //根据情况else 其他数据类型，看着玩儿吧....
        return null;
    }
}
