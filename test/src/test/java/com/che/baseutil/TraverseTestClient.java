package com.che.baseutil;

import com.che.base_util.LogUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 作者：余天然 on 16/9/17 下午6:29
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class TraverseTestClient {

    private static long MAX_NUMBER = 100000;


    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
    }

    @Test
    public void test() {
        List<Integer> linkedList=new LinkedList<Integer>();
        List<Integer> arrayList=new ArrayList<Integer>();
        for(int i=0;i<MAX_NUMBER;i++){   //给两个list赋值
            linkedList.add(100);
            arrayList.add(100);
        }
        getForCirculationTime(linkedList);
        getForCirculationTime(arrayList);
    }

    /**
     * 方法功能：获取两种for循环的时间
     * @param lists
     */
    public static void getForCirculationTime(List<Integer> lists){
        long starTime=System.currentTimeMillis();
        int n=0;
        int size=lists.size();
        for(int i=0;i<size;i++){
            n=lists.get(i);
        }
        Class s =lists.getClass();//获取class的名
        String name=s.getName();
        LogUtil.print(s+"普通for循环 调用get方法花的时间："+(System.currentTimeMillis()-starTime));

        starTime=System.currentTimeMillis();
        for(int  in:lists){
            n=in;
        }
        LogUtil.print(s+"增强型的for循环get方法花的时间："+(System.currentTimeMillis()-starTime));
    }

    @Test
    public void testTraverse() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add("数据：" + (i + 1));
        }

        long startTime = 0;
        long time = 0;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
        }
        time = System.currentTimeMillis() - startTime;
        LogUtil.print("fori:" + time);

        startTime = System.currentTimeMillis();
        for (String s : list) {

        }
        time = System.currentTimeMillis() - startTime;
        LogUtil.print("foreach:" + time);

        startTime = System.currentTimeMillis();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
        }

        time = System.currentTimeMillis() - startTime;
        LogUtil.print("iterator:" + time);
    }


}
