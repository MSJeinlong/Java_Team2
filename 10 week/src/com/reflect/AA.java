package com.reflect;

public class AA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print();
		print(1);
		print(1,2,3,405849,64587);
	}
	
	//可变参数，可以理解为一个数组
	private static void print(int...a) {
		
		for(int i:a) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
}
