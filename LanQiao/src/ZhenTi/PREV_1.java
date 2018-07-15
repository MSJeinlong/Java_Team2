package ZhenTi;

import java.util.Scanner;

public class PREV_1 {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fen();
	}
	
	private static void Fen() {
		Scanner sc = new Scanner(System.in);
		int a, b, c;
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		int d = a * b / Gcd(a, b);
		System.out.println(d * c / Gcd(d, c));
	}
	
	private static int Gcd(int m, int n) {
		while( n != 0) {
			int temp = m % n;
			m = n;
			n = temp;

		}
	
		return m;
	}

}
