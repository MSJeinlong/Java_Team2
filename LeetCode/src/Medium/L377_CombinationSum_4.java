package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class L377_CombinationSum_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1, 2, 3};
		int target = 7;
		System.out.println(combinationSum4(nums, target));
		Set<Integer> keySet = map.keySet();
		for(Integer key:keySet){
			System.out.println(key+": "+map.get(key));
		}
	}

	//速度较低的方法
/*	private static int combinationSum4(int target, int[] nums) {

		List<List<Integer>> res = new ArrayList<>();

		backtrack(res, new ArrayList<Integer>(), nums, target);
		for (List<Integer> list : res) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		return res.size();
	}

	private static void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int remain) {
		if (remain == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		if (remain < 0) {
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			list.add(nums[i]);
			backtrack(res, list, nums, remain - nums[i]);	//注意，这里是 i + 1，不是start + 1， 因为不能使用重复的元素
			list.remove(list.size() - 1);
		}
	}*/
	
	//速度较高的方法
   private static Map<Integer, Integer> map = new HashMap<>();
    private static int combinationSum4(int[] nums, int target) {   
        int count = 0;
        if (nums == null || nums.length ==0 || target < 0 ) return 0;
        if ( target ==0 ) 
            return 1;
        if (map.containsKey(target)) return map.get(target);      
        for (int num: nums){
            count += combinationSum4(nums, target-num);
        }
        map.put(target, count);
        return count;
    }
}

