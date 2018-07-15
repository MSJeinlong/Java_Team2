package BiSai;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class L1_1 {

	public static void Paiban(String s, int m) {
		List<String> list = new ArrayList<>();
		String s1 = "";
		for(int i = 0; i < s.length(); i++) {
			if(i % m == 0) {
				list.add(s1);
			 s1 = "";
			}
			s1 += s.charAt(i);
		}
		
		list.add(s1);
		int k = 0;
		for(int i = 0; i < m; i++) {
			for(int j = list.size() - 1; j >= 0;j--) {
				String s2  = list.get(j);
				if( k >= s2.length())
					System.out.print("%");
				else
					System.out.print(s2.charAt(k));
			}
			k++;
			if( i != m - 1)
				System.out.println();
		}
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Scanner sc = new Scanner(System.in);
//		String s = sc.nextLine();
//		int m = Integer.parseInt(s);
//		s = sc.nextLine();
//		Paiban(s, m);
//		sc.close();
//	}

}
