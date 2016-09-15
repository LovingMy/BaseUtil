package com.che.baseutil.orm.helper;

import com.che.base_util.LogUtil;

/**
 * Sql语句生成器
 * <p/>
 * 作者：余天然 on 16/9/15 下午9:03
 */
public class SqlGenerater {

    /**
     * 生成Create语句
     * <p/>
     * 格式：create table Student(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)
     */
    public static String create(Class<?> clazz) {
        TableWrapper wrapper = ReflectHelper.parseClass(clazz);
        //拼接：create table Student(
        StringBuilder sb = new StringBuilder("create table " + wrapper.getName() + "(");
        //拼接：id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER
        for (int i = 0; i < wrapper.getFiledList().size(); i++) {
            String filed = wrapper.getFiledList().get(i);
            Class<?> type = wrapper.getTypeList().get(i);
            sb.append(filed + " " + TypeConverter.convertCreate(filed, type));
            if (i != wrapper.getFiledList().size() - 1) {
                sb.append(",");
            }
        }
        //拼接：）
        sb.append(")");
        LogUtil.print(sb.toString());
        return sb.toString();
    }

    /**
     * 生成Insert语句
     * <p/>
     * 格式：insert into Student (name,age) values ('Fishyer',23)
     */
    public static <T> String insert(T t) {
        TableWrapper wrapper = ReflectHelper.parseObject(t);
        //拼接：insert into Student （
        StringBuilder sb = new StringBuilder("insert into " + wrapper.getName() + " (");
        //拼接：name,age
        for (int i = 0; i < wrapper.getFiledList().size(); i++) {
            String filed = wrapper.getFiledList().get(i);
            sb.append(filed);
            if (i != wrapper.getFiledList().size() - 1) {
                sb.append(",");
            }
        }
        //拼接：) values (
        sb.append(") values (");
        //拼接：'Fishyer',23
        for (int i = 0; i < wrapper.getTypeList().size(); i++) {
            Class<?> type = wrapper.getTypeList().get(i);
            Object value = wrapper.getValueList().get(i);
            sb.append(TypeConverter.convertInsert(type, value));
            if (i != wrapper.getFiledList().size() - 1) {
                sb.append(",");
            }
        }
        //拼接：)
        sb.append(")");
        LogUtil.print(sb.toString());
        return sb.toString();
    }

    /**
     * 生成Select语句
     * <p/>
     * 格式：select * from Student where name='Fishyer' and age=23
     */
    public static <T> String select(T t) {
        TableWrapper wrapper = ReflectHelper.parseObject(t);
        //拼接：select * from Student
        StringBuilder sb = new StringBuilder("select * from " + wrapper.getName());
        //拼接： where name='Fishyer' and age=23
        for (int i = 0; i < wrapper.getFiledList().size(); i++) {
            if (i == 0) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            String filed = wrapper.getFiledList().get(i);
            Class<?> type = wrapper.getTypeList().get(i);
            Object value = wrapper.getValueList().get(i);
            sb.append(filed + "=" + TypeConverter.convertInsert(type, value));
        }
        LogUtil.print(sb.toString());
        return sb.toString();
    }

    /**
     * 生成SelectAll语句
     * <p/>
     * 格式：select * from Student
     */
    public static <T> String selectAll(Class<?> clazz) {
        StringBuilder sb = new StringBuilder("select * from " + clazz.getSimpleName());
        LogUtil.print(sb.toString());
        return sb.toString();
    }
}
