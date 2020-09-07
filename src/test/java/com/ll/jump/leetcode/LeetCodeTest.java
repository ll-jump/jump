package com.ll.jump.leetcode;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈力扣测试〉
 *
 * @author LiLin
 * @date 2020/5/18 0018
 */
public class LeetCodeTest {

    @Test
    public void testLeetCode() {

//        Map<String,String> map = new HashMap<>();
//        System.out.println(map.size());
//       for (int m = 0; m < 5; m++) {
//           aa:
//           for (int i = 0; i < 10; i++) {
//               for (int j = 0; j < 5; j++) {
//                   System.out.println(String.format("i:%s,j:%s", i,j));
//                   if (j == 2){
//                       break aa;
//                   }
//               }
//           }
//       }

//        int[] nums = new int[]{2,3,-2,4};
//        int result = maxProduct(nums);
//        System.out.println(result);
    }


    /**
     * 乘积最大子数组
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int maxProduct = 0;
        int minusNum;
        int maxLocation;
        int tempMaxProduct;
        int tempMaxProduct2;
        for (int i = 0; i < nums.length; i++) {
            minusNum = 0;
            maxLocation = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) {
                    tempMaxProduct2 = 1;
                    for (int x = i; x <= maxLocation; x++) {
                        tempMaxProduct2 = tempMaxProduct2 * nums[x];
                    }
                    maxLocation = tempMaxProduct2 >= 0 ? maxLocation : j;
                    break;
                } else if (nums[j] < 0) {
                    minusNum++;
                }

                if (minusNum % 2 == 0) {
                    maxLocation = j;
                }
            }
            tempMaxProduct = 1;
            for (int m = i; m <= maxLocation; m++) {
                tempMaxProduct = tempMaxProduct * nums[m];
            }
            if (i == 0) {
                maxProduct = tempMaxProduct;
            } else {
                maxProduct = Math.max(maxProduct, tempMaxProduct);
            }
            if (maxLocation == nums.length - 1 && nums[maxLocation] != 0) {
                break;
            }
        }
        return maxProduct;
    }
}