package com.che.test.algorithm.sort;

/**
 * 作者：余天然 on 16/7/8 下午5:05
 */
public class S4_QuickSort extends BaseSort {

    @Override
    public int[] sort(int[] array) {
        return sortSub(array, 0, array.length - 1);
    }

    //排序子数组，采用分治思想，不断递归迭代，当每个子数组都排好了，源数组也就排好了
    private int[] sortSub(int[] arr, int low, int heigh) {
        if (low < heigh) {
            int division = partition(arr, low, heigh);
            sortSub(arr, low, division - 1);
            sortSub(arr, division + 1, heigh);
        }
        return arr;
    }

    // 分水岭,基位,左边的都比这个位置小,右边的都大
    private int partition(int[] arr, int low, int heigh) {
        int base = arr[low]; //用子表的第一个记录做枢轴(分水岭)记录
        while (low < heigh) { //从表的两端交替向中间扫描
            while (low < heigh && arr[heigh] >= base) {
                heigh--;
            }
            // base 赋值给 当前 heigh 位,base 挪到(互换)到了这里,heigh位右边的都比base大
            swap(arr, heigh, low);
            while (low < heigh && arr[low] <= base) {
                low++;
            }
            // 遇到左边比base值大的了,换位置
            swap(arr, heigh, low);
        }
        // now low = heigh;
        return low;
    }

}
