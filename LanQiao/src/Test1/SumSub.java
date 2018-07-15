package Test1;

import java.util.Scanner;

public class SumSub {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		long n = sc.nextInt();
		long sum = (n * (1 + n)) / 2;
		System.out.print(sum);
	}

}
