package com.che.baseutil.sqlite;

import android.app.Application;

import com.che.base_util.LogUtil;
import com.che.baseutil.BuildConfig;
import com.che.baseutil.table.Person;
import com.che.baseutil.table.Teacher;
import com.che.fast_orm.DBHelper;
import com.che.fast_orm.helper.DBException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 作者：余天然 on 16/9/16 下午10:17
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class DBTestClient {

    private DBHelper dbHelper;//数据库辅助类

    @Before
    public void setUp() throws DBException {
        ShadowLog.stream = System.out;
        Application application = RuntimeEnvironment.application;
        dbHelper = new DBHelper(application, "mydb", 1);
        //删除表
        dbHelper.drop(Person.class);
        //创建表
        dbHelper.create(Person.class);
        //初始化数据，方便之后操作
        initData();
    }

    /**
     * 插入
     */
    public void initData() {
        try {
            //插入多条数据
            List<Person> persons = new ArrayList<>();
            persons.add(new Person("Fishyer", 23));
            persons.add(new Person("Stay", 23));
            persons.add(new Person("Ricky"));
            persons.add(new Person("Stay", 23));
            persons.add(new Person("Fuck", 24));
            persons.add(new Person("Albert"));
            dbHelper.insertAll(persons);

            //插入单条数据
            Person untitled = new Person();
            untitled.setAge(21);
            untitled.setHeight(200);
            dbHelper.insert(untitled);
        } catch (DBException e) {
            LogUtil.print("数据库异常：" + e.getMessage());
        }
    }

    /**
     * 自定义Sql
     */
    @Test
    public void testSql() throws DBException {
        dbHelper.execSQL("drop table if exists Person");
        dbHelper.execSQL("create table if not exists Person(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,age INTEGER DEFAULT 100)");
        dbHelper.execSQL("insert into Person (age) values (21)");
        dbHelper.execSQL("insert into Person (name,age) values ('Fishyer',23)");
    }

    /**
     * 表操作
     */
    @Test
    public void testTable() throws DBException {
        //删除表: drop table if exists Teacher
        dbHelper.drop(Teacher.class);
        //断言表不存在:select count(*) from sqlite_master where type='table' and name='Teacher'
        assertThat(dbHelper.isExist(Teacher.class)).isEqualTo(false);
        //创建表:create table if not exists Teacher(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,age INTEGER,course TEXT)
        dbHelper.create(Teacher.class);
        //断言表存在:
        assertThat(dbHelper.isExist("Teacher")).isEqualTo(true);
    }

    /**
     * 事务
     */
    @Test
    public void testTransaction() throws DBException {
        Person person = new Person("Fishyer", 23);
        dbHelper.beginTransaction();
        for (int i = 0; i < 100; i++) {
            //insert or replace into Person (name,height,age) values ('Fishyer',180.0,23)
            dbHelper.insert(person);
        }
        dbHelper.endTransaction();
    }

    /**
     * 删除
     */
    @Test
    public void testDelete() throws DBException {
        //删除指定数据(不推荐，建议使用条件删除):delete from Person where name='Stay' and height=180.0 and age=-1;
        dbHelper.deleteObj(new Person("Stay"));

        //删除所有数据:delete from Person
        dbHelper.deleteAll(Person.class);

        //条件删除：delete from Person where name='Stay'
        dbHelper.delete(Person.class).where("name=Stay").execute();
    }

    @Test
    public void testQuery() throws DBException {
        //查询所有数据: select * from Person
        dbHelper.queryAll(Person.class);

        //查询指定数据: select * from Person where name='Stay'
        dbHelper.queryObj(new Person("Stay"));
    }

    @Test
    public void testSelect() throws DBException {
        //条件查询1：select * from Person where age>='21' order by name
        dbHelper.select(Person.class).whereInt("age>=21").orderBy("name").query();

        //条件查询2：select * from Person order by age desc
        dbHelper.select(Person.class).where("name=Stay").append("order by id").desc().query();

        //条件查询3：select * from Person where age='23' order by name
        dbHelper.select(Person.class).whereInt("age=23").orderBy("id").query();

        //去重查询：select distinct * from Person order by age desc
        dbHelper.distinct(Person.class).whereInt("age=23").orderBy("id").query();
    }

    @Test
    public void testBackup() throws DBException {
        //建立备份：create table Student_bak as select *from Person
        dbHelper.bak(Person.class);

        //查询备份：select * from Student_bak
        dbHelper.queryBak(Person.class);
    }

    @Test
    public void testUpdate() throws DBException {
        //更新数据：update Person set age=99 where name='Fishyer'
        dbHelper.update(Person.class).setInt("age=99").where("name=Fishyer").execute();

        dbHelper.queryAll(Person.class);
    }

    @Test
    public void testIndex() throws DBException {
        // TODO: 16/9/17 添加索引
    }

    @Test
    public void testMap() throws DBException {
        // TODO: 16/9/17 添加多表关联
    }


}
