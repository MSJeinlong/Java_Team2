package Basic;

import java.util.Scanner;

public class B1008 {

	public static void rightMove(int[] a, int M) {
		M = M % a.length;
		reverse(a, 0, a.length - M - 1);
		reverse(a, a.length - M, a.length - 1);
		reverse(a, 0, a.length - 1);
	}
	
	public static void reverse(int[] a, int begin, int end) {
		for(int i = begin, j = end; i <= (begin + end) / 2; i++, j--) {
			int t =a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] s = sc.nextLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int[] a = new int[n];
		String[] str = sc.nextLine().split(" ");
		for(int i = 0; i < str.length; i++) {
			a[i] = Integer.parseInt(str[i]);
		}
		rightMove(a, M);
		for(int i = 0; i < a.length; i++) {
			if(i < a.length - 1)
				System.out.print(a[i]+" ");
			else
				System.out.print(a[i]);
		}
	}

}
