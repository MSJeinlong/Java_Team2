package BiSai;

import java.util.Scanner;

public class L2_2 {
	
	public static double HongBao(String s, double[] a) {
		String[] str = s.split(" ");
		double sum = 0.0;
		for(int i = 1; i < str.length; i += 2) {
			int num = Integer.parseInt(str[i]);
			int money = Integer.parseInt(str[i + 1]);
			a[num] += money;
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		double[] money = new double[n];
		for(int i = 0; i < n; i++) {
			String s = sc.nextLine();
			money[i] -= HongBao(s, money);
		}
	}

}
