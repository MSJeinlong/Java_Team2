package T1;

import java.util.Scanner;

public class Main {

	public static String calSum(String s) {
		String sum = "";
		if(s == null)
			return sum;
		int ans = 0;
		for(int i = 0; i < s.length(); i++) {
			ans += s.charAt(i) - '0';
		}
		String[] strs = new String[] {"ling", "yi", "er", "san", "si", "wu", "liu", "qi", "ba", "jiu"};
		while (ans != 0) {
			sum = strs[ans % 10] + sum;
			ans /= 10;
			if(ans != 0)
				sum = " " + sum;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println(calSum(sc.nextLine()));
	}

}
