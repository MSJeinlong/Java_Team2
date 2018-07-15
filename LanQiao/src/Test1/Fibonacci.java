package Test1;

import java.util.Scanner;

public class Fibonacci {
	
    public static void Fibonacci(int n) {
        int f1 = 1, f2 = 1;
        if( n == 1 || n == 2) {
        	System.out.println(1);
        	return;
        }
        int fn = 0;
        for(int i = 3; i <= n; i++){
            fn = (f2 + f1) % 10007;
            f1 = f2 % 10007;
            f2 = fn;
        }
        System.out.println( fn % 10007);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		Fibonacci(sc.nextInt());
	}

}



