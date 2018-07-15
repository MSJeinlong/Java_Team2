package Calculator;

import java.math.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(Change((long)1056, 10, 8));
		Calculator();
	}

	private static void Calculator() {
		Scanner sc = new Scanner(System.in);
		String str = null;
		String num = null;
		long a = 0;
		long sum = 0;
		boolean first = true;
		String res = null;
		int n = sc.nextInt();
		int scale = 10;
		char cal = 0;
		for (int i = 0; i < n; i++) {
			str = sc.next();
			switch (str) {
			case "NUM":
				num = sc.next();
				a = Long.parseLong(num, scale); // 转换为十进制
				if (first) {
					char[] num1 = num.toCharArray();
					Arrays.sort(num1);
					char end = num1[num1.length - 1];
					if( end >= 'A' && end <= 'Z') {
						scale = end - 54;
					}
					sum = a;
					first = false;
				} else {
					switch(cal) {
					case '+':
						sum += a;
						break;
					case '-':
						sum -= a;
						break;
					case '/':
						sum /= a;
						break;
					case '*':
						sum *= a;
						break;
					case '%':
						sum %= a;
						break;
					}
				}
				break;
			case "CHANGE":
				scale = sc.nextInt();
				break;
			case "ADD":
				// System.out.println("sum = "+sum+", a = "+a);
				cal = '+';
				break;
			case "SUB":
				cal = '-';
				break;
			case "MUL":
				cal = '*';
				break;
			case "DIV":
				cal = '/';
				break;
			case "MOD":
				cal = '%';
				break;
			case "EQUAL":
				//System.out.println("sum = " + sum + ", scale = " + scale);
				System.out.println(Change(sum, 10, scale));
				break;
			case "CLEAR":
				first = true;
				sum = 0;
				break;
			}
		}
	}

	private static String Change(long n, int k1, int k2) {
		long d = 0;
		if (k1 != 10) {
			for (int i = 0; n != 0; i++) {
				d += (n % 10) * (long) Math.pow(k1, i);
				n /= 10;
			}
		} else {
			d = n;
		}
		String s = "";
		String arr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		while (d != 0) {
			int m = (int) (d % k2);
			if (m >= 10) {
				char c = arr.charAt(m - 10);
				s = c + s;
			} else {
				s = m + s;
			}
			d /= k2;
		}

		// System.out.println(s);
		// for (int i = 0, j = s.length() - 1; i < s.length(); i++, j--) {
		// res += (s.charAt(j) - '0') * (long)Math.pow(10, i);
		// }
		return s;
	}

}
