package Algorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 3, 5, 2, 1, 6, 5, 8, 1};		//测试用例
        System.out.println(new Solution().removeDuplicates(nums));	//打印结果
    }

    public List<Integer> removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        Set<Integer> hashSet =new HashSet();       //利用HashSet不可以存放重复元素的特点
        for(int n:nums){
            hashSet.add(n);
        }

        //HashSet不能排序，所以转换成List集合
        List<Integer> res = new ArrayList<Integer>(hashSet);
        //升序
        Collections.sort(res);
        return res;
    }
}