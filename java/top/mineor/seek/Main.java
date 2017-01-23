package top.mineor.seek;

import static java.util.Arrays.binarySearch;

/**
 * Created by mineor on 2017/1/22.
 */
public class Main {
    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println(binarySearchByIter(array,-4));
        System.out.println(binarySearchByResv(array,77,0,array.length-1));
    }

    /**
     * 二分查找递归版
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static int binarySearchByResv(int[] nums,int target,int start,int end){
        if(start <= end){
            int mid = (start+end)/2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target)
                return binarySearchByResv(nums,target,mid+1,end);
            else
                return binarySearchByResv(nums,target,start,mid-1);
        }
        return -1;
    }

    /**
     * 二分查找非递归版
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearchByIter(int[] nums,int target){
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end){
            mid = (start+end) / 2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] < target){
                start = mid+1;
            }
            else
                end = mid-1;

        }
        return -1;
    }

}
