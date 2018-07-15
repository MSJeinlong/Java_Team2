import java.util.Scanner;

public class Test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
		int n = sc.nextInt();
		Judge(year, n);
	}
	
	private static void Judge(int year, int n) {
		boolean flag;
		//判断是闰年还是平年
		if(n <= 0)
		{
			System.out.println("n = "+n+" 不合理！");
			return;
		}
		if((year % 400 == 0) || ( year % 100 != 0 && year % 4 == 0)) {
			flag = true;
		}
		else
			flag = false;
		if(flag && n > 366 || !flag && n > 365) {
			System.out.println("n = "+n+" 不合理！");
			return;
		}
		
		int m1 = 31;
		int m2 = m1 + 28;
		//如果是闰年，m2+1；
		if(flag) {
			m2++;
		}
		int m3 = m2 + 31;
		int m4 = m3 + 30;
		int m5 = m4 + 31;
		int m6 = m5 + 30;
		int m7 = m6 + 31;
		int m8 = m7 + 31;
		int m9 = m8 + 30;
		int m10 = m9 + 31;
		int m11 = m10 + 30;
		
		int month = 0, day = 0;
		if(n >= 1 && n <= m1) {
			month = 1;
			day = n - 0;
		} else if( n > m1  && n <= m2) {
			month = 2;
			day = n - m1;
		} else if( n > m2  && n <= m3) {
			month = 3;
			day = n - m2;
		}else if( n > m3  && n <= m4) {
			month = 4;
			day = n - m3;
		}else if( n > m4  && n <= m5) {
			month = 5;
			day = n - m4;
		}else if( n > m5  && n <= m6) {
			month = 6;
			day = n - m5;
		}else if( n > m6  && n <= m7) {
			month = 7;
			day = n - m6;
		}else if( n > m7  && n <= m8) {
			month = 8;
			day = n - m7;
		}else if( n > m8  && n <= m9) {
			month = 9;
			day = n - m8;
		}else if( n > m9  && n <= m10) {
			month = 10;
			day = n - m9;
		}else if( n > m10  && n <= m11) {
			month = 11;
			day = n - m1;
		}else if( n > m11) {
			month = 12;
			day = n - m11;
		}
		System.out.println(year+" 年的第 "+n+" 天是该年的 "+month+" 月 "+day+" 号");
	}

}
