package com.che.test.algorithm.sort;

/**
 * 作者：余天然 on 16/7/8 下午4:11
 */
public abstract class BaseSort {
    public abstract int[] sort(int[] array);

    public void swap(int[] arr, int a, int b) {
        int temp;
        temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
