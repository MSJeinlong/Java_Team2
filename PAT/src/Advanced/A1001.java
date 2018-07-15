package Advanced;

import java.util.Scanner;

public class A1001 {
	
	public static String format(int a, int b) {
		String ans = "";
		int sum = a + b;
		
		String s = String.valueOf(sum);
		int c = 0;
		//System.out.println(s);
		for(int i = s.length() - 1; i >= 0; i--) {
			if( c == 3 && s.charAt(i) != '-') {
				c = 0;
				ans = ","+ans;
				i++;
				continue;
			}
			ans = s.charAt(i) + ans;
			c++;
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] s = sc.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int b = Integer.parseInt(s[1]);
		System.out.print(format(a, b));
	}

}
