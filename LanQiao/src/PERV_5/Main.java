package PERV_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Piao();
	}
	
	private static void Piao() {
		Scanner sc = new Scanner(System.in);
		int n;
		List<Integer> arr = new ArrayList<>();
		String line = sc.nextLine();
		n = Integer.parseInt(line);
		//int index = 0;
		for(int i = 0; i < n; i++) {
			line = sc.nextLine();
			String[] str = line.split(" ");
			for(int j = 0; j < str.length; j++) {
				arr.add(Integer.parseInt(str[j]));
			}
		}
		Collections.sort(arr);
		int num1 = 0, num2 = 0;
		int find = 0;
		for(int i = 0; i + 1< arr.size(); i++) {
			int a = arr.get(i);
			int b = arr.get(i + 1);
			if( b - a == 2) {
				num1 = a + 1;
				find++;
			}
			else if( a == b) {
				num2 = a;
				find++;
			}
			
			if(find == 2) {
				break;
			}
		}
		System.out.println(num1+" "+num2);
	}

}
