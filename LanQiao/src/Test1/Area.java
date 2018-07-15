package Test1;

import java.util.Scanner;

public class Area {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		double Area = r * r * Math.PI;
		System.out.println(String.format("%.7f", Area));
	}

}
