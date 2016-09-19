package com.che.test.a_other;

import com.google.gson.Gson;

import org.junit.Test;

/**
 * Created by yutianran on 16/7/6.
 */
public class TestJson {

    Gson gson = new Gson();

    @Test
    public void test1() {
        JsonBean bean = new JsonBean();
        bean.setName("zhangsan");
        System.out.println(gson.toJson(bean));
    }

    @Test
    public void test2() {
        JsonBean bean = new JsonBean();
        bean.setName("xiaoming");
        bean.setAge(13);
        System.out.println(gson.toJson(bean));
    }

    @Test
    public void test3() {
        String json = "{}";
        JsonBean bean = gson.fromJson(json, JsonBean.class);
        System.out.println(bean.toString());
    }
}
