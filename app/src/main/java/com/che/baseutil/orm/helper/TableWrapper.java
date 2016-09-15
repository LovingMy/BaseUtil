package com.che.baseutil.orm.helper;

import java.util.List;

import lombok.Data;

/**
 * 反射信息包装类
 * <p/>
 * 作者：余天然 on 16/9/15 下午7:00
 */
@Data
public class TableWrapper {
    private String name;//类名
    private List<String> filedList;//字段名
    private List<Class<?>> typeList;//字段类型
    private List<Object> valueList;//字段值

    public TableWrapper(String name, List<String> filedList, List<Class<?>> typeList, List<Object> valueList) {
        this.name = name;
        this.filedList = filedList;
        this.typeList = typeList;
        this.valueList = valueList;
    }

}
