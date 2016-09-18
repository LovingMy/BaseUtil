package com.che.fast_orm.helper;

/**
 * Sql语句生成器
 * <p>
 * 作者：余天然 on 16/9/15 下午9:03
 */
public class SqlGenerater {

    public final static String BAK_SUFFIX = "_bak";//备份的后缀

    /**
     * 生成create语句
     * <p>
     * 格式：create table Student(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, age TEXT)
     */
    public static String create(Class<?> clazz) {
        TableWrapper wrapper = ReflectHelper.parseClass(clazz);
        //拼接：create table Student(id INTEGER PRIMARY KEY AUTOINCREMENT,
        StringBuilder sb = new StringBuilder("create table if not exists " + wrapper.name);
        //拼接：(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, age TEXT)
        sb.append(TypeConverter.zipNameType(wrapper));
        return sb.toString();
    }

    /**
     * 生成drop语句
     * <p>
     * 格式：drop table if exists Student;
     */
    public static String drop(Class<?> clazz) {
        StringBuilder sb = new StringBuilder("drop table if exists " + ReflectHelper.getTableName(clazz));
        return sb.toString();
    }

    /**
     * 生成insert语句
     * <p>
     * 格式：insert or replace into Student (name,age) values ('Fishyer',23)
     */
    public static <T> String insert(T t) {
        TableWrapper wrapper = ReflectHelper.parseObject(t);
        //拼接：insert into Student
        StringBuilder sb = new StringBuilder("insert or replace into " + wrapper.name + " ");
        //拼接：(name,age)
        sb.append(TypeConverter.zipName(wrapper));
        //拼接： values
        sb.append(" values ");
        //拼接：('Fishyer',23)
        sb.append(TypeConverter.zipValue(wrapper));
        return sb.toString();
    }

    /**
     * 生成queryAll语句
     * <p>
     * 格式：select * from Student
     */
    public static String queryAll(Class<?> clazz) {
        StringBuilder sb = new StringBuilder("select * from " + ReflectHelper.getTableName(clazz));
        return sb.toString();
    }

    /**
     * 生成deleteAll语句
     * <p>
     * 格式：delete from Student
     */
    public static String deleteAll(Class<?> clazz) {
        StringBuilder sb = new StringBuilder("delete from " + ReflectHelper.getTableName(clazz));
        return sb.toString();
    }


    /**
     * 生成queryObj语句
     * <p>
     * 格式：select * from Student where name='Fishyer' and age=23
     */
    public static <T> String queryObj(T t) {
        TableWrapper wrapper = ReflectHelper.parseObject(t);
        //拼接：select * from Student
        StringBuilder sb = new StringBuilder("select * from " + wrapper.name);
        //拼接： where name='Fishyer' and age=23
        sb.append(TypeConverter.zipConnNameValue(wrapper));
        return sb.toString();
    }

    /**
     * 生成deleteObj语句
     * <p>
     * 格式：delete from Student where name='Fishyer' and age=23
     */
    public static <T> String deleteObj(T t) {
        TableWrapper wrapper = ReflectHelper.parseObject(t);
        //拼接：select * from Student
        StringBuilder sb = new StringBuilder("delete from " + wrapper.name);
        //拼接： where name='Fishyer' and age=23
        sb.append(TypeConverter.zipConnNameValue(wrapper));
        return sb.toString();
    }

    /**
     * 生成bak语句
     * <p>
     * 格式：create table Student2 as select *from Student
     */
    public static <T> String bak(Class<T> clazz) {
        String table = ReflectHelper.getTableName(clazz);
        String tableBak = table + BAK_SUFFIX;
        StringBuilder sb = new StringBuilder("create table " + tableBak + " as select *from " + table);
        return sb.toString();
    }

    /**
     * 生成queryBak语句
     * <p>
     * 格式：select * from Student
     */
    public static String queryBak(Class<?> clazz) {
        StringBuilder sb = new StringBuilder("select * from " + ReflectHelper.getTableName(clazz) + BAK_SUFFIX);
        return sb.toString();
    }

}
