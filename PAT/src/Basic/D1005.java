package Basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class D1005 {

	public static int Callatz(int n, int count) {
		System.out.print(n+"  ");
		if( n == 1) {
			return count;
		}
		if( n%2== 0)
			return Callatz(n/2, count + 1);
		else
			return Callatz( (3 * n + 1) / 2, count + 1);
	}
	
	public static void AdCallatz(int[]nums, boolean[] numB) {
		for(int i = 0; i < nums.length; i++) {
			if(numB[i])
				continue;
			int n = nums[i];
			while(n != 1) {
				if( n % 2 == 0)
					n /= 2;
				else
					n = (3 * n + 1) / 2;
				for(int j = 0; j < nums.length; j++) {
					if(numB[j])
						continue;
					if(n == nums[j]) {
						numB[j] = true;
						break;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int n = Integer.parseInt(s);
		String[] str = sc.nextLine().split(" ");
		int[] nums = new int[str.length];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(str[i]);
		}
		boolean[] numB = new boolean[nums.length];
		AdCallatz(nums, numB);
		List<Integer> numl = new ArrayList<>();
		for(int i = 0; i < nums.length; i++) {
			if(numB[i])
				continue;
			numl.add(nums[i]);
		}
		Collections.sort(numl);
		for(int i = numl.size() - 1; i >= 0; i--)
			if( i != 0)
				System.out.print(numl.get(i)+" ");
			else
				System.out.println(numl.get(i));
	}
}
