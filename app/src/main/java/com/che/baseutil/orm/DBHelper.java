package com.che.baseutil.orm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.che.baseutil.orm.helper.ReflectHelper;
import com.che.baseutil.orm.helper.SqlGenerater;

import java.util.List;

/**
 * 可以通过SQLiteOpenHelper的以下两个方法来或得SQLiteDatabase的对象：
 * getReadableDatabase() 创建或者打开一个查询数据库
 * getWritableDatabase() 创建或者打开一个可写数据库
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * 构造函数，必须实现
     *
     * @param context 上下文路径
     * @param name    数据库名称
     * @param version 当前数据库版本号
     */
    public DBHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    //数据库第一次创建时会调用，一般在其中创建数据库表
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    //当数据库需要修改的时候，Android系统会主动的调用这个方法。
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public <T> void create(Class<?> clazz) {
        String createSql = SqlGenerater.create(clazz);
        getWritableDatabase().execSQL(createSql);
    }

    public <T> void insert(T t) {
        String insertSql = SqlGenerater.insert(t);
        getWritableDatabase().execSQL(insertSql);
    }

    public <T> List<T> select(T t) {
        String selectSql = SqlGenerater.select(t);
        Cursor cursor = getReadableDatabase().rawQuery(selectSql, null);
        return (List<T>) ReflectHelper.parseCursor(cursor, t.getClass());
    }

    public <T> List<T> selectAll(Class<T> clazz) {
        String selectAllSql = SqlGenerater.selectAll(clazz);
        Cursor cursor = getReadableDatabase().rawQuery(selectAllSql, null);
        return ReflectHelper.parseCursor(cursor, clazz);
    }

    public void execSQL(String sql) {
        getWritableDatabase().execSQL(sql);
    }

    public Cursor rawQuery(String sql) {
        return getReadableDatabase().rawQuery(sql, null);
    }

}