package Basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class B1012 {

	public static void ClassifyNum() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		for(int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			int key = a[i]%5;
			if(map.get(key) == null) map.put(key, new ArrayList<Integer>());
			map.get(key).add(a[i]);
		}	
		
		for(int i = 0; i < 5; i++) {
			List<Integer> list = null;
			switch(i) {
			case 0:
				list = map.get(i);
				if(list == null)
					System.out.print("N ");
				else {
					int sum = 0;
					for(int j = 0; j < list.size(); j++) {
						sum += list.get(j);
					}
					System.out.print(sum+" ");
				}
				break;
			case 1:
				list = map.get(i);
				if(list == null)
					System.out.print("N ");
				else {
					int sum = 0;
					for(int j = 0; j < list.size(); j++) {
						if( j % i == 0)
							sum -= list.get(j);
						else 
							sum += list.get(j);
					}
					System.out.print(sum+" ");
				}
				break;
			case 2:
				list = map.get(i);
				if(list == null)
					System.out.print("N ");
				else {
	
					System.out.print(list.size()+" ");
				}
				break;
			case 3:
				list = map.get(i);
				if(list == null)
					System.out.print("N ");
				else {
					double sum = 0;
					for(int j = 0; j < list.size(); j++) {
						sum += list.get(j);
					}
					System.out.print(String.format("%.1f", sum/list.size()));
				}
				break;
			case 4:
				list = map.get(i);
				if(list == null)
					System.out.print("N");
				else {
					int max = 0;
					for(int j = 0; j < list.size(); j++) {
						int x = list.get(j);
						if(max < x) {
							max = x;
						}
					}
					System.out.print(max);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassifyNum();
	}

}
