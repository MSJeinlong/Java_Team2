package Medium;

import java.util.*;

public class L216_CombinationSum_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> res = combinationSum3(3, 9);
		for (List<Integer> list : res) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	private static List<List<Integer>> combinationSum3(int k, int n) {

		List<List<Integer>> res = new ArrayList<>();

		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		backtrack(k, res, new ArrayList<Integer>(), nums, n, 0);
		return res;
	}

	private static void backtrack(int k, List<List<Integer>> res, List<Integer> list, int[] nums, int n, int start) {
		if (n == 0 && list.size() == k) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		if (list.size() >= k || n == 0) {
			return;
		}
		for (int i = start; i < nums.length; i++) {
			list.add(nums[i]);
			backtrack(k, res, list, nums, n - nums[i], i + 1);	//注意，这里是 i + 1，不是start + 1， 因为不能使用重复的元素
			list.remove(list.size() - 1);
		}
	}
}
