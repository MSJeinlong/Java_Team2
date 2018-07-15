package com.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * 
 * @author JunLong
 *	�ص㣺���Իῼ��֪ʶ��
 */
public class Reflect2 {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		// TODO Auto-generated method stub
		//1����ȡ�����
		Class c2 = User.class;
		//2����ȡ���еķ���
		//getMethods()��ȡ�ķ��������丸��ķ���������ֻ�ܷ��ع����ķ���
/*		Method[] methods = c2.getMethods();
		System.out.println(methods.length);
		
		for(Method method:methods) {
			System.out.println(methods);
		}*/
		//getDeclareMethods()���᷵���������еķ��������������췽����
		Method[] declareMethods = c2.getDeclaredMethods();
		System.out.println(declareMethods.length);
		for(Method method:declareMethods) {
			System.out.println(method);
		}
		try {
			//��ȡһ������;��һ������nameҪд��֮��Ӧ�ķ����������ַ�������ʽ��������Ĳ����뷽���������ֽ������һ��
			Method declaredMethod = c2.getDeclaredMethod("print");
			//System.out.println(declareMethod);
			//4���÷�����÷���,����������,��һ������obj��һ������Ķ���Ҫ�÷��������ɡ����õ��޲εĹ�������
			try {
				//declaredMethod.invoke(c2.newInstance());
				Method declaredMethod2 = c2.getDeclaredMethod("print", String.class, int.class);//��ȡ��ӦPrint()����
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
			Method declaredMethod2 = c2.getDeclaredMethod("print", String[].class);//��ȡ��̬����
			try {
				// new Object[]{parameter1, ......} �ڲ�����������Ӱ���һ��ͨ�õķ���
				declaredMethod2.invoke(c2.newInstance(), new Object[] {new String[] {"1","2"}});//static�ķ��������ھ���Ķ��󣬶������࣬�ʵ�һ��������null
			} catch (IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��ȡ��ӦPrint()����
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
	
	//���ڷ����еĲ������������͵ģ�Ҫ���������
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
