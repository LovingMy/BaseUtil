package com.che.test.algorithm;

import android.app.Application;

import com.che.base_util.LogUtil;
import com.che.test.BuildConfig;
import com.che.test.algorithm.sort.BaseSort;
import com.che.test.algorithm.sort.S1_InsertSort;
import com.che.test.algorithm.sort.S2_SelectSort;
import com.che.test.algorithm.sort.S3_BubbleSort;
import com.che.test.algorithm.sort.S4_QuickSort;
import com.che.test.algorithm.sort.S5_MergeSort;
import com.che.test.algorithm.sort.S6_ShellSort;
import com.che.test.algorithm.sort.S7_HeapSort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static com.google.common.truth.Truth.assertThat;

/**
 * 测试排序算法
 * <p>
 * 作者：余天然 on 16/9/19 下午4:22
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class TestSort {

    private int[] array = {6, 3, 0, 7, 0, 9, 8};
    private BaseSort baseSort;
    private long time;
    private String tag;
    private int N = 1;

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;//用Android.Log代替System.out
        Application application = RuntimeEnvironment.application;//获取应用Context
        time = System.currentTimeMillis();
    }

    @After
    public void finish() {
//        for (int i = 0; i < array.length; i++) {
//            int a = array[i];
//            LogUtil.print("array[" + i + "]=" + a);
//        }
        LogUtil.print(tag + "耗时：" + (System.currentTimeMillis() - time));
        assertThat(array).isEqualTo(new int[]{0, 0, 3, 6, 7, 8, 9});
    }

    @Test
    public void testInsert() {
        tag = "插入排序";
        for (int i = 0; i < N; i++) {
            baseSort = new S1_InsertSort();
            baseSort.sort(array);
        }
    }

    @Test
    public void testSelect() {
        tag = "选择排序";
        for (int i = 0; i < N; i++) {
            baseSort = new S2_SelectSort();
            baseSort.sort(array);
        }
    }

    @Test
    public void testBubble() {
        tag = "冒泡排序";
        for (int i = 0; i < N; i++) {
            baseSort = new S3_BubbleSort();
            baseSort.sort(array);
        }
    }

    @Test
    public void testQuick() {
        tag = "快速排序";
        for (int i = 0; i < N; i++) {
            baseSort = new S4_QuickSort();
            baseSort.sort(array);
        }
    }

    @Test
    public void testMerge() {
        tag = "归并排序";
        for (int i = 0; i < N; i++) {
            baseSort = new S5_MergeSort();
            baseSort.sort(array);
        }
    }

    @Test
    public void testShell() {
        tag = "希尔排序";
        for (int i = 0; i < N; i++) {
            baseSort = new S6_ShellSort();
            baseSort.sort(array);
        }
    }

    @Test
    public void testHeap() {
        tag = "堆排序";
        for (int i = 0; i < N; i++) {
            baseSort = new S7_HeapSort();
            baseSort.sort(array);
        }
    }
}
