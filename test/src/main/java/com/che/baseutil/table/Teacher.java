package com.che.baseutil.table;


import com.che.fast_orm.annotation.Column;
import com.che.fast_orm.annotation.Table;

import lombok.Data;

/**
 * 作者：余天然 on 16/9/15 下午4:52
 */
@Data
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
}
