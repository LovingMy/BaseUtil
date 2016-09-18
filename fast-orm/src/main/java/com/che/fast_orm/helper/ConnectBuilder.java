package com.che.fast_orm.helper;

import com.che.fast_orm.DBHelper;

import java.util.List;

/**
 * 连接符编辑器
 * <p>
 * 作者：余天然 on 16/9/17 上午12:38
 */
public class ConnectBuilder<T> {
    public DBHelper dbHelper;//用于调用终止连接符：query和execute
    public Class<T> clazz;//用于解析Cursor
    public String sql;

    public ConnectBuilder(DBHelper dbHelper, Class<T> clazz, String sql) {
        this.dbHelper = dbHelper;
        this.clazz = clazz;
        this.sql = sql;
    }

    /**
     * where 连接符
     * <p>
     * 1、where的默认比较符是=，如果是其它符号，需在第二个参数说明
     * 2、where与whereInt的区别在于：是否给后面的值加单引号
     */
    public ConnectBuilder<T> where(String s) {
        return where(s, "=");
    }

    public ConnectBuilder<T> where(String s, String operation) {
        this.sql = sql + (" where " + TypeConverter.addQuote(s, operation));
        return this;
    }

    public ConnectBuilder<T> whereInt(String s) {
        this.sql = sql + (" where " + s);
        return this;
    }


    /**
     * and 连接符
     */
    public ConnectBuilder<T> and(String s) {
        return and(s, "=");
    }

    public ConnectBuilder<T> and(String s, String operation) {
        this.sql = sql + (" and " + TypeConverter.addQuote(s, operation));
        return this;
    }

    public ConnectBuilder<T> andInt(String s) {
        this.sql = sql + (" and " + s);
        return this;
    }

    /**
     * set 连接符
     */
    public ConnectBuilder<T> set(String s) {
        return where(s, "=");
    }

    public ConnectBuilder<T> set(String s, String operation) {
        this.sql = sql + (" set " + TypeConverter.addQuote(s, operation));
        return this;
    }

    public ConnectBuilder<T> setInt(String s) {
        this.sql = sql + (" set " + s);
        return this;
    }

    /**
     * order by 连接符
     */
    public ConnectBuilder<T> orderBy(String field) {
        this.sql = sql + (" order by " + field);
        return this;
    }

    /**
     * desc 连接符
     */
    public ConnectBuilder<T> desc() {
        this.sql = sql + (" desc");
        return this;
    }

    /**
     * append 连接符
     * <p>
     * 代表一个空格
     */
    public ConnectBuilder<T> append(String s) {
        this.sql = sql + (" " + s);
        return this;
    }

    /**
     * 执行Sql语句，查询，有返回值
     *
     * @return
     */
    public List<T> query() throws DBException {
        return dbHelper.query(this);
    }

    /**
     * 执行Sql语句,非查询，无返回值
     *
     * @return
     */
    public void execute() throws DBException {
        dbHelper.execute(this);
    }

}
