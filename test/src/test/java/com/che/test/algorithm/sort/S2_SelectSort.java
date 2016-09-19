package com.che.test.algorithm.sort;

/**
 * 作者：余天然 on 16/7/8 下午5:04
 */
public class S2_SelectSort extends BaseSort {

    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int miniPost = i;
            for (int m = i + 1; m < array.length; m++) {
                if (array[m] < array[miniPost]) {
                    miniPost = m;
                }
            }

            if (array[i] > array[miniPost]) {
                int temp;
                temp = array[i];
                array[i] = array[miniPost];
                array[miniPost] = temp;
            }
        }
        return array;
    }
}
