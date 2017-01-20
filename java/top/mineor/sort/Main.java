package top.mineor.sort;

import java.util.Stack;

/**
 * Created by mineor on 2017/1/16.
 */
public class Main {
    public static void main(String[] args) {
        int[] array = {8,2,7,4,1,9,5,3,6};
        bitMapSort(array);
        print(array);
    }

    public static void print(int[] array){
        for (int i: array) {
            System.out.print(i+" ");
        }
    }

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubblingSort(int[] array){
        for(int i = 0; i < array.length-1; i++){
            for(int j = 0; j < array.length-i-1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * @param array
     */
    public static void selectionSort(int[] array){
        for(int i = 0; i < array.length; i++){
            int min = i;
            for(int j = i+1; j < array.length; j++){
                if(array[min] > array[j])
                    min = j;
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    /**
     * 组合选择和冒泡的排序
     * @param array
     */
    public static void acnameSort(int[] array){
        for(int i = 0; i < array.length; i++){
            for(int j = i+1; j < array.length; j++){
                if(array[i] > array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * @param array
     */
    public static void insertSort(int[] array){
        for(int i = 1; i < array.length; i++){
            int current = array[i];
            int j = i-1;
            while(j >= 0 && array[j] > current){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = current;
        }
    }

    /**
     * 归并排序的递归实现
     * @param array
     */
    public static void mergeSortByRecursive(int[] array){
        sort(array,0,array.length-1);
    }
    public static void sort(int[] array,int start,int end){
        if(start < end){
            int mid = (start+end)/2;
            sort(array,start,mid);
            sort(array,mid+1,end);
            merge(array,start,mid,end);
        }
    }
    /**
     * 归并排序的非递归实现(迭代)
     * @param array
     */
    public static void mergeSortByIterative(int[] array){
        int step = 1;
        /**
         * 分别以1,2,4,8,16为步长,两两合并序列
         */
        while(step < array.length){
            MergePass(array,step);
            step *= 2;
        }
    }

    /**
     * 以step为一组,两两合并
     * @param array
     * @param step
     */
    private static void MergePass(int[] array, int step){
        int i = 0;
        /**
         * 当i+2*step-1<array.length
         * 说明还可以找到两组长度为step的序列
         */
        while(i+2*step-1 < array.length){
            merge(array,i,i+step-1,i+2*step-1);
            i += 2*step;
        }
        /**
         * 如果i+step-1>=array.length
         * 说明不足一个step长度,剩余部分仍然有序
         * 如果i+step-1<array.length
         * 说明超过一个step,需要将这部分合并
         */
        if(i+step-1 < array.length)
            merge(array,i,i+step-1,array.length-1);
    }

    /**
     * 合并子序列,空间复杂度为O(n)
     * @param array
     * @param start
     * @param mid
     * @param end
     */
    public static void merge(int[] array,int start,int mid,int end){
        int[] left = new int[mid-start+1];
        int[] right = new int[end-mid];
        for(int i = start,k = 0; i <= mid; i++)
            left[k++] = array[i];
        for(int j = mid+1,k = 0; j <= end; j++)
            right[k++] = array[j];
        int i = 0;
        int j = 0;
        int k = start;
        while(i < left.length && j < right.length){
            if(left[i] <= right[j])
                array[k++] = left[i++];
            else
                array[k++] = right[j++];
        }
        while(i < left.length){
            array[k++] = left[i++];
        }
        while(j < right.length)
            array[k++] = right[j++];
    }

    /**
     * 快速排序的递归实现
     * @param array
     */
    public static void quickSortByRecursive(int[] array){
        quickByRecursive(array,0,array.length-1);
    }

    public static void quickByRecursive(int[] array, int start, int end){
        if(start >= end)
            return;
        int index = array[start];
        int i = start;
        int j = end;
        while(i < j){
            while(i < j && array[j] >= index)
                j--;
            if(array[j] < index)
                array[i++] = array[j];
            while(i < j && array[i] < index)
                i++;
            if(array[i] >= index)
                array[j--] = array[i];
        }
        array[i] = index;
        quickByRecursive(array,start,i);
        quickByRecursive(array,i+1,end);
    }

    /**
     * 快速排序的非递归实现(迭代)
     * @param array
     */
    public static void quickSortByIterative(int[] array){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        stack.push(array.length-1);
        while(!stack.isEmpty()){
            int right = stack.pop();
            int left = stack.pop();
            int index = quickByInterative(array,left,right);
            if(left < index-1){
                stack.push(left);
                stack.push(index-1);
            }
            if(index < right-1){
                stack.push(index+1);
                stack.push(right);
            }
        }
    }

    /**
     * 一次快排过程
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int quickByInterative(int[] array,int start,int end){
        int index = array[start];
        while(start < end){
            while(start < end && array[end] >= index)
                end--;
            if(array[end] < index)
                array[start++] = array[end];
            while(start < end && array[start] < index)
                start++;
            if(array[start] >= index)
                array[end--] = array[start];
        }
        array[start] = index;
        return start;
    }

    /**
     * 堆排序
     * @param array
     */
    public static void heapSort(int[] array){
        for(int i = (array.length-1) / 2; i >= 0; i--) {
            heapAdjustByIter(array, i, array.length - 1);
        }
        swapValue(array,0,array.length-1);
        for(int i = array.length-2; i > 0; i--){
            heapAdjustByIter(array,0,i);
            swapValue(array,0,i);
        }
    }

    /**
     * 递归调整成最大堆
     * @param array
     * @param root
     * @param max
     */
    public static void heapAdjustByResv(int[] array,int root,int max){
        int left = root * 2 + 1;
        int right = root * 2 + 2;
        if(left <= max){
            if(right <= max && array[left] < array[right]){
                if(array[right] > array[root]){
                    swapValue(array,root,right);
                    heapAdjustByResv(array,right,max);
                }
            }
            else if(array[left] > array[root]){
                swapValue(array,root,left);
                heapAdjustByResv(array,left,max);
            }
            else
                return;
        }
    }

    /**
     * 迭代调整成最大堆
     * @param array
     * @param root
     * @param max
     */
    public static void heapAdjustByIter(int[] array,int root,int max){
        int left;
        int right;
        while(true){
            left = root * 2 + 1;
            right = root * 2 + 2;
            if(left <= max){
                if(right <= max && array[left] < array[right]){
                    if(array[right] > array[root]){
                        swapValue(array,root,right);
                        root = right;
                    }
                }
                else if(array[left] > array[root]){
                    swapValue(array,root,left);
                    root = left;
                }
                else
                    return;
            }
            else
                return;
        }
    }

    /**
     * 交换变量
     * @param array
     * @param x
     * @param y
     */
    public static void swapValue(int[] array,int x,int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * 位图排序
     * @param array
     */
    public static void bitMapSort(int[] array){
        if(array == null || array.length < 1)
            return;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        int[] bit = new int[max/32+1];
        for (int i = 0; i < array.length; i++) {
            int m = array[i] % 32;
            int n = array[i] / 32;
            bit[n] |= (1 << m);
        }

        int count = 0;
        for (int i = 0; i < bit.length; i++) {
            int num = bit[i];
            for(int j = 0; j < 32; j++){
                if((num & (1 << j)) != 0){
                    array[count++] = i * 32 + j;
                }
            }
        }

    }

}
