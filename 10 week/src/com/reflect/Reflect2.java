package com.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * 
 * @author JunLong
 *	重点：考试会考的知识点
 */
public class Reflect2 {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		// TODO Auto-generated method stub
		//1、获取类对象
		Class c2 = User.class;
		//2、获取所有的方法
		//getMethods()获取的方法包括其父类的方法，并且只能返回公共的方法
/*		Method[] methods = c2.getMethods();
		System.out.println(methods.length);
		
		for(Method method:methods) {
			System.out.println(methods);
		}*/
		//getDeclareMethods()将会返回类中所有的方法（不包括构造方法）
		Method[] declareMethods = c2.getDeclaredMethods();
		System.out.println(declareMethods.length);
		for(Method method:declareMethods) {
			System.out.println(method);
		}
		try {
			//获取一个方法;第一个参数name要写与之对应的方法名（以字符串的形式），后面的参数与方法参数的字节码对象一样
			Method declaredMethod = c2.getDeclaredMethod("print");
			//System.out.println(declareMethod);
			//4、用反射调用方法,有两个参数,第一个参数obj是一个本类的对象，要用放射来生成。（用到无参的构造器）
			try {
				//declaredMethod.invoke(c2.newInstance());
				Method declaredMethod2 = c2.getDeclaredMethod("print", String.class, int.class);//获取对应Print()方法
				declaredMethod2.setAccessible(true);
				try {
					declaredMethod2.invoke(c2.newInstance(), new Object[] {"chi",20});
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Method declaredMethod2 = c2.getDeclaredMethod("print", String[].class);//获取静态方法
			try {
				// new Object[]{parameter1, ......} 在参数的最外面加包是一个通用的方法
				declaredMethod2.invoke(c2.newInstance(), new Object[] {new String[] {"1","2"}});//static的方法不属于具体的对象，而属于类，故第一个参数用null
			} catch (IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取对应Print()方法
	}

}

class User{
	
	public User() {
		
	}
	
	public User(int a) {
		
	}
	public void print() {
		System.out.println("User.print()");
	}
	
	public void print(String name) {
		System.out.println("User.print()"+name);
	}
	
	private void print(String name, int age) {
		System.out.println("User.print()"+name+" :"+age);
	}
	
	//对于方法中的参数是数组类型的，要分两种情况
	public void print(String...a) {
		for(String i:a) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public static void print1(String name, int age) {
		System.out.println("static -> User.print1()"+name+" :"+age);
	}
}
