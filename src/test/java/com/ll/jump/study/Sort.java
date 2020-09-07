package com.ll.jump.study;

import org.junit.Test;

/**
 * 〈排序〉
 *
 * @author LiLin
 * @date 2020/6/21 0021
 */
public class Sort {
    /**
     * 冒泡排序
     */
    @Test
    public void bubbleSortTest(){
        int[] arr = new int[]{42,20,17,13,28,24,23,15};
        bubbleSort(arr);
        for (int a : arr ) {
            System.out.println(a);
        }
    }
    private void bubbleSort(int[] arr){
        int temp;
        for(int i =0; i < arr.length - 1; ++i){
            for (int j = arr.length -1 ; j > i; --j){
                if (arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        return;
    }

    /**
     * 选择排序
     */
    @Test
    public void SelectionSort(){
        int[] arr = new int[]{42,20,17,13,28,24,23,15};
        selectionSort(arr);
        for (int a : arr ) {
            System.out.println(a);
        }
    }
    private void selectionSort(int[] arr){
        int index;
        int temp;
        for (int i = 0; i < arr.length - 1; ++i){
            index = i;
            for (int j = i + 1; j <= arr.length - 1; ++j){
                if (arr[j] < arr[index]){
                    index = j;
                }
            }
            if (index != i){
                temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * 插入排序
     */
    @Test
    public void insertionSort(){
        int[] arr = new int[]{42,20,17,13,28,24,23,15};
        insertionSort(arr);
        for (int a : arr ) {
            System.out.println(a);
        }
    }

    private void insertionSort(int[] arr){
        int temp;
        for (int i = 0; i < arr.length - 1; ++ i){
            for(int j = i + 1; j > 0; --j){
                if (arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] =  arr[j-1];
                    arr[j-1] = temp;
                }else {
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序
     */
    @Test
    public void shellSort(){
        int[] arr = new int[]{42,20,17,13,28,24,23,15};
        shellSort(arr);
        for (int a : arr ) {
            System.out.println(a);
        }
    }
    private void shellSort(int[] arr){
        int incre = arr.length;
        int temp;
        while (incre != 1){
            incre = incre/2;
            for (int k = 0; k < incre; ++k){
                for (int i = k + incre; i < arr.length; i+=incre){
                    for (int j = i; j > k; j-=incre){
                        if (arr[j] < arr[j-incre]){
                            temp = arr[j];
                            arr[j] = arr[j-incre];
                            arr[j-incre] = temp;
                        }else{
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 快速排序
     */
    @Test
    public void quickSort(){
//        int[] arr = new int[]{42,20,17,13,28,24,23,15};
        int[] arr = new int[]{72,6,57,88,60,42,83,73,48,85};
        quickSort(arr, 0, 9);
        for (int a : arr ) {
            System.out.println(a);
        }
    }
    private void quickSort(int[] arr, int l, int r){
        if (l >= r){
            return;
        }

        int i = l;
        int j = r;
        //取左边第一个数为比较的key
        int key = arr[l];
        while (i < j){
            //找到右边第一个小于key的数
            while (i < j && arr[j] >= key){
                j--;
            }
            if (i < j){
                //将右边第一个小于key的数放到左边i地方，同时左边i右移
                arr[i] = arr[j];
                i++;
            }
            //找到左边第一个大于key的数
            while (i < j && arr[i] < key){
                i ++;
            }
            if (i < j){
                //将左边第一个大于key的数放到右边j地方，同时右边j左移
                arr[j] = arr[i];
                j --;
            }
        }
        //设置左边i地方为key
        arr[i] = key;
        //递归左部分
        quickSort(arr, l, i - 1);
        //递归右部分
        quickSort(arr, i + 1, r);
    }

    /**
     * 归并排序
     */
    @Test
    public void mergeSort(){
        int[] arr = new int[]{72,6,57,88,60,42,83,73,48,85};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,9,temp);
        for (int a : arr ) {
            System.out.println(a);
        }
    }
    private void mergeSort(int[] a, int first, int last, int[] temp){
        if (first >= last){
            return;
        }
        int middle = (first+last)/2;
        //分解数组
        mergeSort(a,first,middle,temp);
        mergeSort(a,middle+1,last,temp);
        //合并 ：将两个序列a[first-middle],a[middle+1-end]合并
        mergeArray(a,first,middle,last,temp);
    }

    /**
     * 将两个有序数组合并
     * @param a
     * @param first
     * @param middle
     * @param last
     * @param temp
     */
    private void mergeArray(int[] a, int first, int middle, int last, int[] temp) {
        int i = first;
        int j = middle;
        int m = middle + 1;
        int n = last;
        int k = 0;
        while (i <= j && m <= n){
            if (a[i] <= a[m]){
                temp[k++] = a[i++];

            }else {
                temp[k++] = a[m++];
            }
        }
        while (i <= j){
            temp[k++] = a[i++];
        }

        while (m <= n){
            temp[k++] = a[m++];
        }

        for (int x = 0; x < k; x++){
            a[first+x] = temp[x];
        }
    }

    /**
     * 堆排序
     */
    @Test
    public void minHeapSort(){
        int[] arr = new int[]{72,6,57,88,60,42,83,73,48,85};
        minHeapSort(arr, 10);
        for (int a : arr ) {
            System.out.println(a);
        }
    }
    private void minHeapSort(int[] a, int n){
        int temp;
        //构建最大堆
        makeHeadMinHeap(a, n);
        for (int i = n - 1; i > 0; i --){
            //将最大堆 尾部和头部互换，然后再对头部到i进行构建最大堆
            temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            minHeapFixdown(a, 0, i);
        }
    }

    /**
     * 构建最大堆
     * @param a
     * @param n
     */
    private void makeHeadMinHeap(int[] a, int n){
        for (int i = (n-1)/2; i >= 0; i--){
            minHeapFixdown(a, i, n);
        }
    }

    /**
     * 对0到i进行构建最大堆（前提是除了头部0之外已符合最大堆条件）
     * @param a
     * @param i
     * @param n
     */
    private void minHeapFixdown(int[] a, int i, int n){
        int temp;
        int j = 2*i + 1;
        while (j < n){
            if (j+1<n && a[j+1]>a[j]){
                j++;
            }
            if (a[i] >= a[j]){
                break;
            }
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i = j;
            j = 2*i+1;
        }
    }

    /**
     * 基数排序
     */
    @Test
    public void radixSort(){
        int[] arr = new int[]{72,6,57,88,60,42,83,73,48,85};
        radixSort(arr, 2);
        for (int a : arr ) {
            System.out.println(a);
        }
    }
    private void radixSort(int[] a, int k){
        int[] temp = new int[a.length];
        int n = a.length;
        int r = 10;
        //每个桶的个数
        int[] cnt = new int[r];
        for(int i = 0, rtok = 1; i < k; i++,rtok = rtok*r){
            for(int j = 0; j < r; j++){
                //初始化每个桶个数为0
                cnt[j] = 0;
            }

            for (int j = 0; j < n; j++){
                //将数组a中的单个位数放到对应桶中，桶个数+1
                cnt[(a[j]/rtok)%r] ++;
            }

            for (int j = 1; j < r; j++){
                //设置桶个数为前边桶个数之和
                cnt[j] = cnt[j-1] + cnt[j];
            }

            for (int j = n -1; j >= 0; j--){
                //根据桶个数获取所在数组排序位置
                cnt[(a[j]/rtok)%r]--;
                temp[cnt[(a[j]/rtok)%r]] = a[j];
            }
            for (int j = 0; j < n; j++){
                a[j] = temp[j];
            }
        }
    }
}