package PREV_54;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


//算法知识-- 并查集
public class Main {

	static int[] head;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		int k = sc.nextInt();
	 head = new int[m * n];
		for(int i = 0; i < m * n; i++) {
			head[i] = i;
		}
		
		for(int i = 0; i < k; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			u(a, b);
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < m * n; i++) {
			set.add(f(i));
		}
		System.out.println(set.size());
	}
	
	private static void u(int a, int b) {
		if(f(a) == f(b))
			return;
		head[f(a)] = f(b);
	}
	
	private static int f(int i) {
		if(head[i] == i)
			return i;
		return head[i] =f(head[i]); 
	}

}
