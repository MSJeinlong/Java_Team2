import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(fun(n));
		
	}
	
	private static boolean fun(int n) {
		int a, b, c;
		a = n % 10;
		b = (n / 10) % 10;
		c = ( n / 100) % 10;
		
		if( n == (a * a * a + b *b *b + c *c *c)) {
			return true;
		}
		else {
			return false;
		}
	}
}
