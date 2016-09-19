package com.che.test.algorithm.sort;

/**
 * 作者：余天然 on 16/7/8 下午5:05
 */
public class S6_ShellSort extends BaseSort {

    @Override
    public int[] sort(int[] array) {
        // 取增量
        int step = array.length / 2;
        while (step >= 1) {
            for (int i = step; i < array.length; i++) {
                int temp = array[i];
                int j = 0;
                // 跟插入排序的区别就在这里
                for (j = i - step; j >= 0 && temp < array[j]; j -= step) {
                    array[j + step] = array[j];
                }
                array[j + step] = temp;
            }
            step /= 2;
        }
        return array;
    }
}
