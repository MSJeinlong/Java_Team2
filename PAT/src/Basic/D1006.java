package Basic;

import java.util.Scanner;

public class D1006 {

	public static String OutputInt(int n) {
		int c = 1;
		String s = "";
		while( n != 0) {
			int digits = n % 10;
			switch(c) {
			case 1:
				for(int i = 1; i <= digits; i++)
					s += i;
				break;
			case 2:
				for(int i = 0; i < digits;i++)
					s = "S"+ s;
				break;
			case 3:
				for(int i = 0; i < digits; i++)
					s = "B"+s;
				break;
			}
			c++;
			n /= 10;
		}
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(OutputInt(n));
	}

}
