package Algorithm;

import java.math.*;

//������һ�����������ӷֽ�
public class SuYinZi {

	private static void Solution(int n) {
		System.out.print(n + " = ");
		int q = (int) Math.sqrt(n);//ȡ��ƽ����
		int i = 2;
		while (i <= q && n != 1) {
			if (Prime(i) && n % i == 0) {	//��� i �������������� n,�� i �� n �������ӣ�
				System.out.print(i);
				n /= i;			//  n = n / i; i��ʱ����
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

	private static boolean Prime(int n) {//�ж�n�Ƿ�������
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
