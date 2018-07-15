package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Reflect1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<?> c1 = null;
		//1、获取类对象
		try {
			 c1 = Class.forName("com.reflect.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(c1);
		//2、获取构造器,此时getConstructor()只有一个参数
		try {
			Constructor constructor = c1.getConstructor();
		//3、用反射去调用构造器
			try {
			 constructor.newInstance();
			//对于无参的公共的构造方法的调用，可以直接用类对象进行调用
			//设计类的时候最好设计一个无参的构造器
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
