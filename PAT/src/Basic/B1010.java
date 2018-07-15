package Basic;

import java.util.Scanner;

public class B1010 {

	public static int[] Ferivation(String s) {
		String[] str = s.split(" ");
		int[] nums = new int[str.length];
		//int[] nums1 = new int[str.length];
		for(int i = 0; i < nums.length;i++)
			nums[i] = Integer.parseInt(str[i]);
		for(int i = 0; i < nums.length; i += 2) {
			if(nums[i+ 1] == 0 || nums[i] == 0) {
				nums[i] = nums[i + 1] = 0;
			}
			else {
				nums[i] = nums[i] * nums[i + 1];
				nums[i + 1] -= 1;
			}
		}
//		for(int i:nums)
//			System.out.println(i);
		return nums;
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int[] nums = Ferivation(s);
		for(int i = 0; i < nums.length; i += 2) {
			if( nums[i] == 0 && nums[i + 1] == 0)
				continue;
			if( i + 2 < nums.length && i != 0)
				System.out.print(" ");
			System.out.print(nums[i]+" "+nums[i+1]);
		}
	}

}
