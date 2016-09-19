package com.che.baseutil.table;


import com.che.fast_orm.annotation.Check;
import com.che.fast_orm.annotation.Column;
import com.che.fast_orm.annotation.Default;
import com.che.fast_orm.annotation.NotNull;
import com.che.fast_orm.annotation.Table;

import lombok.Data;

/**
 * 作者：余天然 on 16/9/15 下午4:39
 */
@Data
@Table
public class Person {

    @Column
    private int id;

    @Check("name!='Fucker'")
    @Column
    private String name;

    @Default
    @Column
    private double height = 180;

    @Column
    private int age;

    @Default
    @NotNull
    @Column
    private String job = "IT";

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
