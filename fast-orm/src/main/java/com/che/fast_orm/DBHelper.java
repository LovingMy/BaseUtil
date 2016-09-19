package com.che.fast_orm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.che.base_util.LogUtil;
import com.che.fast_orm.helper.ConnectBuilder;
import com.che.fast_orm.helper.DBException;
import com.che.fast_orm.helper.ReflectHelper;
import com.che.fast_orm.helper.SqlGenerater;

import java.util.List;

/**
 * ORM工具类
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * 构造函数，必须实现
     *
     * @param context 上下文
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

    //基本修改命令
    public void execSQL(String sql) throws DBException {
        try {
            sql += ";";
            LogUtil.print(sql);
            getWritableDatabase().execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(e.getMessage());
        }
    }

    //基本查询命令
    public Cursor rawQuery(String sql) throws DBException {
        Cursor cursor = null;
        try {
            sql += ";";
            LogUtil.print(sql);
            cursor = getReadableDatabase().rawQuery(sql, null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(e.getMessage());
        }
        return cursor;
    }

    /**
     * 表操作命令
     */
    public void create(Class<?> clazz) throws DBException {
        String createSql = SqlGenerater.create(clazz);
        execSQL(createSql);
    }

    public void drop(Class<?> clazz) throws DBException {
        String dropSql = SqlGenerater.drop(clazz);
        execSQL(dropSql);
    }

    public <T> void bak(Class<T> clazz) throws DBException {
        String bakSql = SqlGenerater.bak(clazz);
        execSQL(bakSql);
    }

    public <T> boolean isExist(Class<T> clazz) throws DBException {
        return isExist(ReflectHelper.getTableName(clazz));
    }

    public boolean isExist(String tableName) throws DBException {
        Cursor cursor = rawQuery("select count(*) from sqlite_master where type='table' and name='" + tableName + "'");
        if (cursor.moveToNext()) {
            int count = cursor.getInt(0);
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 新增
     */
    public <T> void insert(T t) throws DBException {
        String insertSql = SqlGenerater.insert(t);
        execSQL(insertSql);
    }

    public <T> void insertAll(List<T> list) throws DBException {
        getWritableDatabase().beginTransaction();
        for (T t : list) {
            insert(t);
        }
        getWritableDatabase().setTransactionSuccessful();
        getWritableDatabase().endTransaction();
    }

    /**
     * 删除
     */
    public <T> void deleteObj(T t) throws DBException {
        String whereSql = SqlGenerater.deleteObj(t);
        execSQL(whereSql);
    }

    public <T> void deleteAll(Class<T> clazz) throws DBException {
        String deleteAllSql = SqlGenerater.deleteAll(clazz);
        execSQL(deleteAllSql);
    }

    /**
     * 查询
     */
    public <T> List<T> queryObj(T t) throws DBException {
        String whereSql = SqlGenerater.queryObj(t);
        Cursor cursor = rawQuery(whereSql);
        return (List<T>) ReflectHelper.parseCursor(cursor, t.getClass());
    }

    public <T> List<T> queryAll(Class<T> clazz) throws DBException {
        String queryAllSql = SqlGenerater.queryAll(clazz);
        Cursor cursor = rawQuery(queryAllSql);
        return ReflectHelper.parseCursor(cursor, clazz);
    }

    public <T> List<T> queryBak(Class<T> clazz) throws DBException {
        String selectAllSql = SqlGenerater.queryBak(clazz);
        Cursor cursor = rawQuery(selectAllSql);
        return ReflectHelper.parseCursor(cursor, clazz);
    }

    /**
     * 创建连接符编辑器
     */
    //查询
    public <T> ConnectBuilder<T> select(Class<T> clazz) throws DBException {
        return new ConnectBuilder(this, clazz, "select * from " + ReflectHelper.getTableName(clazz));
    }

    //去重查询
    public <T> ConnectBuilder<T> distinct(Class<T> clazz) throws DBException {
        return new ConnectBuilder(this, clazz, "select distinct * from " + ReflectHelper.getTableName(clazz));
    }

    //删除
    public <T> ConnectBuilder<T> delete(Class<T> clazz) throws DBException {
        return new ConnectBuilder(this, clazz, "delete from " + ReflectHelper.getTableName(clazz));
    }

    //修改
    public <T> ConnectBuilder<T> update(Class<T> clazz) throws DBException {
        return new ConnectBuilder(this, clazz, "update " + ReflectHelper.getTableName(clazz));
    }

    /**
     * 连接符编辑器-执行，无返回值
     */
    public <T> void execute(ConnectBuilder<T> builder) throws DBException {
        execSQL(builder.sql);
    }

    /**
     * 编辑器-查询，有返回值
     */
    public <T> List<T> query(ConnectBuilder<T> builder) throws DBException {
        Cursor cursor = rawQuery(builder.sql);
        return ReflectHelper.parseCursor(cursor, builder.clazz);
    }

    /**
     * 开启事务
     */
    public void beginTransaction() {
        getReadableDatabase().beginTransaction();
    }

    /**
     * 关闭事务
     */
    public void endTransaction() {
        getReadableDatabase().setTransactionSuccessful();
        getReadableDatabase().endTransaction();
    }

}