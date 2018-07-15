package sort1;

import java.util.Scanner;

public class InsertSort {

	public static void insertSort(int []nums) {
		for( int i = 1; i < nums.length;i++) {
			int temp = nums[i];
			int j = i;
			for(; j > 0 && nums[j - 1] > temp; j--) {
				nums[j] = nums[j - 1];
			}
			nums[j] = temp;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = sc.nextInt();
		}
		insertSort(nums);
		for(int i:nums) {
			System.out.println(i);
		}
	}

}
