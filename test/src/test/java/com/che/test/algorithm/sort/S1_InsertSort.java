package com.che.test.algorithm.sort;

/**
 * 作者：余天然 on 16/7/8 下午5:04
 */
public class S1_InsertSort extends BaseSort {

    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            for (j = i - 1; j >= 0 && temp < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
        return array;
    }
}
