package com.che.baseutil.table;


import com.che.fast_orm.annotation.Column;
import com.che.fast_orm.annotation.Table;

/**
 * 作者：余天然 on 16/9/15 下午4:52
 */
@Table
public class Teacher {

    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
