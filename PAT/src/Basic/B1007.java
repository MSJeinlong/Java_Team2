package Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B1007 {

	public static boolean Prime(int n) {
		if(n%2 == 0 && n != 2)
			return false;
		for(int i = 3; i <= (int)Math.sqrt(n); i += 2) {
			if( n % i == 0)
				return false;
		}
		return true;
	}
	
	public static int NumPrime(int n) {
		int c = 0;
		List<Integer> Primes = new ArrayList<>();
		Primes.add(2);
		for(int i = 3; i <= n; i += 2) {
			if(Prime(i)) {
				Primes.add(i);
				int x = Primes.size() - 1;
				if(Primes.get(x) - Primes.get(x - 1) == 2 )
					c++;
			}
		}
//		for(int i = 1; i <Primes.size(); i++) {
//			if(Primes.get(i) -Primes.get(i - 1) == 2 )
//				c++;
//		}
		return c;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(NumPrime(n));
		
	}

}
