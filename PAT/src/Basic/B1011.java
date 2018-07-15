package Basic;

import java.util.Scanner;

public class B1011 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			if( a + b > c)
				System.out.println("Case #"+(i + 1)+": true");
			else
				System.out.println("Case #"+(i + 1)+": false");
		}
		sc.close();
	}

}
