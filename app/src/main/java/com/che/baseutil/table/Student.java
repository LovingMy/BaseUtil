package com.che.baseutil.table;

import com.che.baseutil.orm.annotation.Column;
import com.che.baseutil.orm.annotation.Table;

import lombok.Data;

/**
 * 作者：余天然 on 16/9/15 下午4:39
 */
@Data
@Table
public class Student {

    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer age;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(Integer age) {
        this.age = age;
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
