package BiSai;

import java.util.Scanner;

public class L1_2 {

	public static String Dang(int hh, int mm) {
		String s = "";

		 if(mm == 0) {
			for(int i = 0; i < hh - 12; i++)
				s += "Dang";
		}
		else {
			for(int i = 0; i < hh - 12 + 1; i++)
				s += "Dang";
		}
		//System.out.println(s);
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] s = sc.nextLine().split(":");
		int hh = Integer.parseInt(s[0]);
		int mm = Integer.parseInt(s[1]);
		if( (hh >= 0 && hh < 12) || (hh == 12  || hh == 24)&& mm == 0) {
			
			System.out.print( "Only "+s[0]+":"+s[1]+".  Too early to Dang.");
		}
		else
			System.out.println(Dang(hh, mm));
	}

}
