package com.che.fast_orm.helper;

import java.util.List;

/**
 * 表信息包装类
 * <p>
 * 作者：余天然 on 16/9/15 下午7:00
 */
public class TableWrapper {
    public String name;//类名
    public List<String> filedList;//字段名
    public List<Class<?>> typeList;//字段类型
    public List<String> constraintList;//字段约束(一个列的约束可能不止一个,需自己拼接成一个)
    public List<Object> valueList;//字段值

    public TableWrapper(String name, List<String> filedList, List<Class<?>> typeList, List<String> constraintList, List<Object> valueList) {
        this.name = name;
        this.filedList = filedList;
        this.typeList = typeList;
        this.constraintList = constraintList;
        this.valueList = valueList;
    }
}
