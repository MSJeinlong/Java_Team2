package Basic;

import java.util.LinkedList;
import java.util.Scanner;

public class B1009 {


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] str = sc.nextLine().split(" ");
		sc.close();
		for(int i = str.length - 1; i > 0; i--) {
			System.out.print(str[i]+" ");
		}
		System.out.print(str[0]);
	}

}
