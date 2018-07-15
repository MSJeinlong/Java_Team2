package Basic;
import java.util.Scanner;
public class Main {

	public static int Callatz(int n, int count) {
		System.out.print(n+"  ");
		if( n == 1) {
			return count;
		}
		if( n%2== 0)
			return Callatz(n/2, count + 1);
		else
			return Callatz( (3 * n + 1) / 2, count + 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println(Callatz(sc.nextInt(), 0));
	}

}
