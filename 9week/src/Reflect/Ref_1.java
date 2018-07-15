package Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//反射机制
//所有的类（接口，抽象类），都会有一个字节码对象与之对应（文件名,class）,作为此类是类对象(Class类的对象)的表现形式
//九个（八个基本类型+void)内置Class实例
public class Ref_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1.基本类型的Class实验关系 System.out.println(int.class);
		System.out.println(boolean.class);
		System.out.println(float.class);
		System.out.println(double.class);
		System.out.println(byte.class);
		System.out.println(long.class);
		System.out.println(char.class);
		System.out.println(short.class);
		System.out.println();
		// 空值和空类

		System.out.println(void.class);
		System.out.println(Void.class);
		System.out.println();
		// 基本类型的包装类
		System.out.println(Integer.class);
		System.out.println(Boolean.class);
		System.out.println(Float.class);
		System.out.println(Double.class);
		System.out.println(Byte.class);
		System.out.println(Long.class);
		System.out.println(Character.class);
		System.out.println(Short.class);

		// 八大基本类型和其包装类所对应的字节码对象是不同的
		// 但是包装类都有一个静态的常量TYPE，TYPE所对应的就是原始类型的类对象

		System.out.println(int.class == Integer.class);// 结果为false
		System.out.println(int.class == Integer.TYPE);// 结果为true

		// 2.获取类对象（字节码对象）的三种方法
		// 方法一：通过class属性来获取(所有的类+9个基本类型都会有自己的class属性)

		System.out.println(Person.class);
		Class c1 = Person.class;
		System.out.println(c1);

		// 方法二：通过类的对象来获取（前提是要知道某个类的对象）
		Class c2 = new Person().getClass();
		System.out.println(c1 == c2); // 验证了c1 和 c2是同一个对象，即Person的字节码对象

		// 方法三：用到了Class类的forName()方法
		try {
			Class<?> c3 = Class.forName("Reflect.Person");
			System.out.println(c1 == c3);
		} catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 获取数组的字节码对象
		// 所有的维数相同，类型相同的数组共享一份字节码对象

		int[] a = { 1, 2, 3 };
		int[] b = { 3, 2, 3, 4, 5, 6, 7 };
		int[][] c5 = { {}, {} };

		System.out.println(b.getClass() == a.getClass());
	//	System.out.println(a.getClass() == c5.getClass());

		Class c4 = int[].class; // 获取一个一维数组的字节码对象
		System.out.println("c4 , c5");
		System.out.println(c4 == a.getClass());
		System.out.println(c4 == c5.getClass());
		System.out.println(a.getClass()+" "+float[].class);

		Person[] p = new Person[10];
		Person[] p1 = new Person[10];
		System.out.println(p.getClass() == p1.getClass());

		// 获取类中的构造器
		// 步骤：
		// 1.获取类所对应的字节码对象
		Class cp = Person.class;

		// getConstructors()获取所有的public 构造器
		Constructor[] cs = cp.getConstructors();
		for (Constructor constructor : cs) {
			System.out.println(constructor);
		}

		// 获取所有的构造器的方法，getDeclaredConstructors(), 无论是private还是public
		Constructor[] cs1 = cp.getDeclaredConstructors();
		for (Constructor constructor : cs1) {
			System.out.println(constructor);
		}

		// 获取一个pulic构造器
		try {
			Constructor ct1 = cp.getConstructor();
			System.out.println(ct1);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// getDeclaredConstructor（）可以获取所有权限的一个构造器，无论是public还是private
			Constructor ct2 = cp.getDeclaredConstructor(String.class, int.class);
			System.out.println(ct2);
			try {
				Person p2 = (Person)ct2.newInstance("chi", 20); // 利用ct2构造器新建对象
				System.out.println(p2);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 通过反射生成一个类的对象用到的是Constructor类中的newInstance()方法
		try {
			Constructor dct = cp.getDeclaredConstructor(String.class);
			dct.setAccessible(true);// 对于private的构造器，要这样设置
			try {
				System.out.println(dct.newInstance("jun"));

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

class Person {
	int age;
	String name;

	public Person() {
		System.out.println("()");
	}

	public Person(String name, int age) {
		this.age = age;
		this.name = name;
		System.out.println("name + age");
	}

	private Person(String name) {
		this.name = name;
		System.out.println("name");
	}

	public void say() {
		System.out.println("I am " + name + ", I am " + age + " years old!");
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
}
