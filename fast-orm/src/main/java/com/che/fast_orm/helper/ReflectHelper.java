package com.che.fast_orm.helper;

import android.database.Cursor;
import android.text.TextUtils;

import com.che.base_util.LogUtil;
import com.che.fast_orm.annotation.Check;
import com.che.fast_orm.annotation.Column;
import com.che.fast_orm.annotation.Default;
import com.che.fast_orm.annotation.NotNull;
import com.che.fast_orm.annotation.Table;
import com.che.fast_orm.annotation.Unique;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射辅助类
 * <p>
 * 作者：余天然 on 16/9/15 下午9:06
 */
public class ReflectHelper {


    /**
     * 直接反射，获取字段值
     */
    private static <T> Object getFieldValue(T t, Field field) {
        // TODO: 16/9/15 这里怎么将返回值自动强转成fieldType呢？求解！！！
        Object value = null;
        try {
            field.setAccessible(true);
            value = field.get(t);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 解析数据库游标
     *
     * @param cursor
     * @param clazz
     * @return
     */
    public static <T> List<T> parseCursor(Cursor cursor, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            TableWrapper wrapper = ReflectHelper.parseClass(clazz);
            while (cursor.moveToNext()) {
                T t = clazz.newInstance();
                int pos = 0;
                for (String filedName : wrapper.filedList) {
                    Class<?> type = wrapper.typeList.get(pos);
                    Object value = getCursorValue(cursor, filedName, type);
                    Field field = clazz.getDeclaredField(filedName);
                    field.setAccessible(true);
                    field.set(t, value);
                    field.setAccessible(false);
                    pos++;
                }
                LogUtil.print("-->:" + t.toString());
                list.add(t);
            }
            cursor.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析类或对象的信息
     */
    private static <T> TableWrapper parse(Class<?> clazz, T t) {
        List<String> filedList = new ArrayList<>();//字段名
        List<Class<?>> typeList = new ArrayList<>();//字段类型
        List<String> constraintList = new ArrayList<>();//字段约束(一个列的约束可能不止一个)
        List<Object> valueList = new ArrayList<>();//字段值
        //判断是否存在表注解
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new RuntimeException(clazz.getName() + "没有添加表注解");
        }

        int column = 0;
        //遍历所有的字段
        for (Field field : clazz.getDeclaredFields()) {
            //判断是否存在列注解
            if (field.isAnnotationPresent(Column.class)) {
                column++;
                String fieldName = ReflectHelper.getColumnName(field); //获取字段名
                Class<?> fieldType = field.getType();//获取字段类型
                Object fieldValue = t == null ? null : getFieldValue(t, field);//获取字段值

                //非创建时，忽略id字段
                if (t != null && fieldName.toLowerCase().equals("id".toLowerCase())) {
                    continue;
                }

                //创建表时，添加字段约束
                if (t == null) {
                    addConstraint(clazz, field, constraintList);
                }

                //插入数据时，忽略空字段
                if (t != null && fieldValue == null) {
                    continue;
                }

                //添加字段名、字段类型、字段值到列表中
                filedList.add(fieldName);
                typeList.add(fieldType);
                valueList.add(fieldValue);
            }
        }
        if (column == 0) {
            throw new RuntimeException(clazz.getName() + "表中没有添加任何列注解");
        }
        if (t != null && filedList.isEmpty()) {
            throw new RuntimeException(clazz.getName() + "表中对象所有列均为空");
        }
        return new TableWrapper(getTableName(clazz), filedList, typeList, constraintList, valueList);
    }

    /**
     * 获取表名
     *
     * @param clazz
     * @return
     */
    public static String getTableName(Class<?> clazz) {
        Table annotation = clazz.getAnnotation(Table.class);
        String value = annotation.value();
        return TextUtils.isEmpty(value) ? clazz.getSimpleName() : value;

    }

    /**
     * 获取列名
     *
     * @param field
     * @return
     */
    private static String getColumnName(Field field) {
        Column annotation = field.getAnnotation(Column.class);
        String value = annotation.value();
        return TextUtils.isEmpty(value) ? field.getName() : value;
    }


    /**
     * 添加字段约束
     *
     * @param clazz
     * @param field
     * @param list
     */
    private static <T> void addConstraint(Class<T> clazz, Field field, List<String> list) {
        StringBuffer sb = new StringBuffer();
        //遍历该字段的所有注解
        for (Annotation item : field.getDeclaredAnnotations()) {
            if (item instanceof NotNull) {
                sb.append(Constraint.NOT_NULL);
            } else if (item instanceof Default) {
                String value = getDefaultValue(clazz, field);
                sb.append(Constraint.DEFAULT + " " + value);
            } else if (item instanceof Unique) {
                sb.append(Constraint.UNIQUE);
            } else if (item instanceof Check) {
                Check annotation = field.getAnnotation(Check.class);
                String value = annotation.value();
                sb.append(Constraint.CHECK + "(" + value + ")");
            } else {
                sb.append("");
            }
        }
        list.add(sb.toString());
    }

    /**
     * 获取列的默认值
     *
     * @param clazz
     * @param field
     * @return
     */
    private static <T> String getDefaultValue(Class<T> clazz, Field field) {
        try {
            T t = clazz.newInstance();
            return getFieldValue(t, field).toString();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取列的默认值异常");
    }

    /**
     * 解析对象的信息
     */
    public static <T> TableWrapper parseObject(T t) {
        return parse(t.getClass(), t);
    }

    /**
     * 解析类的信息
     */
    public static TableWrapper parseClass(Class<?> clazz) {
        return parse(clazz, null);
    }

    /**
     * 解析列表的信息
     */
    public static <T> List<TableWrapper> parseList(List<T> list) {
        List<TableWrapper> wrappers = new ArrayList<>();
        for (T t : list) {
            wrappers.add(parse(t.getClass(), t));
        }
        return wrappers;
    }

    /**
     * 获取数据库Cursor的值
     * <p>
     * 例如:'Stay',23
     */
    private static Object getCursorValue(Cursor cursor, String filedName, Class<?> type) {
        //文本
        if (type == String.class) {
            return cursor.getString(cursor.getColumnIndex(filedName));
        }
        // TODO: 16/9/15 获取整数时，如果数据库存的是null，这里会自动变成0，是个问题！
        //整数
        else if (type == int.class) {
            return cursor.getInt(cursor.getColumnIndex(filedName));
        } else if (type == Integer.class) {
            return cursor.getInt(cursor.getColumnIndex(filedName));
        } else if (type == long.class) {
            return cursor.getLong(cursor.getColumnIndex(filedName));
        } else if (type == Long.class) {
            return cursor.getLong(cursor.getColumnIndex(filedName));
        } else if (type == boolean.class) {
            int anInt = cursor.getInt(cursor.getColumnIndex(filedName));
            return anInt == 0 ? false : true;
        } else if (type == Boolean.class) {
            int anInt = cursor.getInt(cursor.getColumnIndex(filedName));
            return anInt == 0 ? false : true;
        }
        //实数
        else if (type == float.class) {
            return cursor.getFloat(cursor.getColumnIndex(filedName));
        } else if (type == Float.class) {
            return cursor.getFloat(cursor.getColumnIndex(filedName));
        } else if (type == double.class) {
            return cursor.getDouble(cursor.getColumnIndex(filedName));
        } else if (type == Double.class) {
            return cursor.getDouble(cursor.getColumnIndex(filedName));
        }
        //输入形式
        else {
            return " BLOB";
        }
    }


}
