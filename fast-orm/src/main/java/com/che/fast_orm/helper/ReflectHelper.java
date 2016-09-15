package com.che.fast_orm.helper;

import android.database.Cursor;

import com.che.base_util.LogUtil;
import com.che.fast_orm.annotation.Column;
import com.che.fast_orm.annotation.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射辅助类
 * <p/>
 * 作者：余天然 on 16/9/15 下午9:06
 */
public class ReflectHelper {

    /**
     * 直接反射，获取字段值
     */
    public static <T> Object getFieldValue(T t, Field field) {
        // TODO: 16/9/15 这里怎么将返回值自动强转成fieldType呢？求解！！！
        Object value = null;
        try {
            field.setAccessible(true);
            value = field.get(t);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 解析类的信息
     */
    public static <T> TableWrapper parseObject(T t) {
        List<String> filedList = new ArrayList<>();//字段名
        List<Class<?>> typeList = new ArrayList<>();//字段类型
        List<Object> valueList = new ArrayList<>();//字段值

        // 获取类的class
        Class<?> clazz = t.getClass();

        //判断是否存在表注解
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new RuntimeException(clazz.getName() + "没有添加表注解");
        }

        int column = 0;
        //遍历所有的字段
        for (Field field : clazz.getDeclaredFields()) {
            //判断是否存在列注解
            if (field.isAnnotationPresent(Column.class)) {
                column++;
                String fieldName = field.getName(); //获取字段名
                Class<?> fieldType = field.getType();//获取字段类型
                Object fieldValue = getFieldValue(t, field);//获取字段值

                //忽略空字段
                if (fieldValue == null) {
                    continue;
                }

                //添加字段名、字段类型、字段值到列表中
                filedList.add(fieldName);
                typeList.add(fieldType);
                valueList.add(fieldValue);
            }
        }
        if (column == 0) {
            throw new RuntimeException(clazz.getName() + "表中没有添加任何列注解");
        }
        if (filedList.isEmpty()) {
            throw new RuntimeException(clazz.getName() + "表中所有列均为空");
        }
        return new TableWrapper(clazz.getSimpleName(), filedList, typeList, valueList);
    }

    /**
     * 解析对象的信息
     */
    public static TableWrapper parseClass(Class<?> clazz) {
        List<String> filedList = new ArrayList<>();//字段名
        List<Class<?>> typeList = new ArrayList<>();//字段类型
        List<Object> valueList = new ArrayList<>();//字段值

        //判断是否存在表注解
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new RuntimeException(clazz.getName() + "没有添加表注解");
        }

        //遍历所有的字段
        for (Field field : clazz.getDeclaredFields()) {
            //判断是否存在列注解
            if (field.isAnnotationPresent(Column.class)) {
                String fieldName = field.getName(); //获取字段名
                Class<?> fieldType = field.getType();//获取字段类型

                //添加字段名、字段类型到列表中
                filedList.add(fieldName);
                typeList.add(fieldType);
            }
        }
        if (filedList.isEmpty()) {
            throw new RuntimeException(clazz.getName() + "表中没有添加任何列注解");
        }
        return new TableWrapper(clazz.getSimpleName(), filedList, typeList, valueList);
    }

    /**
     * 解析数据库游标
     *
     * @param cursor
     * @param clazz
     * @return
     */
    public static <T> List<T> parseCursor(Cursor cursor, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            TableWrapper wrapper = ReflectHelper.parseClass(clazz);
            while (cursor.moveToNext()) {
                T t = clazz.newInstance();
                int pos = 0;
                for (String filedName : wrapper.getFiledList()) {
                    Class<?> type = wrapper.getTypeList().get(pos);
                    Object value = TypeConverter.convertCursor(cursor, filedName, type);
                    Field field = clazz.getDeclaredField(filedName);
                    field.setAccessible(true);
                    field.set(t, value);
                    field.setAccessible(false);
                    pos++;
                }
                LogUtil.print("-->:" + t.toString());
                list.add(t);
            }
            cursor.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return list;
    }
}
