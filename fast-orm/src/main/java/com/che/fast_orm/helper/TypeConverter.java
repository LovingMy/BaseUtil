package com.che.fast_orm.helper;

/**
 * 类型转换器
 * <p>
 * 作者：余天然 on 16/9/15 下午9:03
 */
public class TypeConverter {

    //wrapper --> (name,age)
    public static String zipName(TableWrapper wrapper) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < wrapper.filedList.size(); i++) {
            String filed = wrapper.filedList.get(i);
            sb.append(filed);
            if (i != wrapper.filedList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    //wrapper --> ('Fishyer',23)
    public static String zipValue(TableWrapper wrapper) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int j = 0; j < wrapper.filedList.size(); j++) {
            Class<?> type = wrapper.typeList.get(j);
            Object value = wrapper.valueList.get(j);
            sb.append(TypeConverter.getInsertValue(type, value));
            if (j != wrapper.typeList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    //wrapper --> (name TEXT NOT NULL, age TEXT)
    public static String zipNameType(TableWrapper wrapper) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < wrapper.filedList.size(); i++) {
            String filed = wrapper.filedList.get(i);
            String type = TypeConverter.getCreateType(filed, wrapper.typeList.get(i));
            String constraint = wrapper.constraintList.get(i);
            sb.append(filed + type + constraint);
            if (i != wrapper.filedList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    //wrapper --> where name='Fishyer' and age=23
    public static String zipConnNameValue(TableWrapper wrapper) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wrapper.filedList.size(); i++) {
            if (i == 0) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            String filed = wrapper.filedList.get(i);
            Class<?> type = wrapper.typeList.get(i);
            Object value = wrapper.valueList.get(i);
            sb.append(filed + "=" + TypeConverter.getInsertValue(type, value));
        }
        return sb.toString();
    }

    /**
     * 获取create时的存储类型
     * <p>
     * 例如:TEXT、INTEGER
     */
    private static String getCreateType(String field, Class<?> type) {
        //主键
        if (field.toLowerCase().equals("id".toLowerCase())) {
            return " INTEGER PRIMARY KEY AUTOINCREMENT";
        }
        //文本
        if (type == String.class) {
            return " TEXT";
        }
        //整数
        else if (type == int.class) {
            return " INTEGER";
        } else if (type == Integer.class) {
            return " INTEGER";
        } else if (type == long.class) {
            return " INTEGER";
        } else if (type == Long.class) {
            return " INTEGER";
        } else if (type == boolean.class) {
            return " INTEGER";
        } else if (type == Boolean.class) {
            return " INTEGER";
        }
        //实数
        else if (type == float.class) {
            return " REAL";
        } else if (type == Float.class) {
            return " REAL";
        } else if (type == double.class) {
            return " REAL";
        } else if (type == Double.class) {
            return " REAL";
        }
        //输入形式
        else {
            return " BLOB";
        }
    }

    /**
     * 获取Insert时的存储值
     * <p>
     * 例如:'Stay',23 (主要就是为了给String加单引号)
     */
    private static String getInsertValue(Class<?> type, Object value) {
        if (type == String.class) {
            return "'" + value + "'";
        }
        else if (type == int.class) {
            return value.toString();
        }else {
            return value.toString();
        }
    }

    /**
     * 给字段加单引号
     * <p>
     * 例：Fishyer --> 'Fishyer'
     */
    public static String addQuote(String s, String operation) {
        String[] strings = s.split(operation);
        return strings[0] + operation + "'" + strings[1] + "'";
    }

}
