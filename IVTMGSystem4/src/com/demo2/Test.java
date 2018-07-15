package com.demo2;

public class Test {

	public static void main(String[] args) {
		SupplierModel.readData();
		for (Supplier s : SupplierModel.getSupplierGroup()) {
			System.out.println(s);
		}
	}

}