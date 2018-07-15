package Algorithm;

import java.math.*;

//将任意一个合数素因子分解
public class SuYinZi {

	private static void Solution(int n) {
		System.out.print(n + " = ");
		int q = (int) Math.sqrt(n);//取其平方根
		int i = 2;
		while (i <= q && n != 1) {
			if (Prime(i) && n % i == 0) {	//如果 i 是素数且能整除 n,则 i 是 n 的素因子，
				System.out.print(i);
				n /= i;			//  n = n / i; i暂时不变
				if (n != 1) {
					System.out.print(" x ");
				}
			} else if( i == 2) {
				i++;
			}
			else {
				i += 2;
			}
		}
		if(n!=1) {
			System.out.println(n);
		}
	}

	private static boolean Prime(int n) {//判断n是否是素数
		if (n == 2 || n == 3)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;
		int q = (int) Math.sqrt(n);

		for (int i = 3; i <= q; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(Prime(11));
		Solution(1024);
	}

}
