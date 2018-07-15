package T2;

import java.util.Arrays;
import java.util.Scanner;

public class L2_2 {
	
	public static double HongBao(String s, double[] a) {
		String[] str = s.split(" ");
		double sum = 0.0;
		//System.out.println(" length = "+s.length());
		for(int i = 1; i < str.length; i += 2) {
			int num = Integer.parseInt(str[i]);
			int money = Integer.parseInt(str[i + 1]);
			//System.out.print(num+" "+money+" ");
			a[num] += money;
			sum += money;
		}
		//System.out.println(" start ");
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		double[] money = new double[n];
		for(int i = 0; i < n; i++) {
			//System.out.print(i);			
			money[i] -= HongBao(sc.nextLine(), money);
		}
		double[] m1 = Arrays.copyOf(money, money.length);
		Arrays.sort(m1);
		for(int i = 0; i < money.length; i++) {
			for(int j = 0; j < m1.length; j++) {
				if( money[i] == m1[j])
					System.out.println(i+" "+m1);
			}
		}
	}

}
