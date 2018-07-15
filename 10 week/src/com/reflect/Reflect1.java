package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Reflect1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<?> c1 = null;
		//1����ȡ�����
		try {
			 c1 = Class.forName("com.reflect.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(c1);
		//2����ȡ������,��ʱgetConstructor()ֻ��һ������
		try {
			Constructor constructor = c1.getConstructor();
		//3���÷���ȥ���ù�����
			try {
			 constructor.newInstance();
			//�����޲εĹ����Ĺ��췽���ĵ��ã�����ֱ�����������е���
			//������ʱ��������һ���޲εĹ�����
			 c1.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class Person{
	public Person() {
		System.out.println("Person.Person()");
	}
}
