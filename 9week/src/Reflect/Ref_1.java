package Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//�������
//���е��ࣨ�ӿڣ������ࣩ��������һ���ֽ��������֮��Ӧ���ļ���,class��,��Ϊ�����������(Class��Ķ���)�ı�����ʽ
//�Ÿ����˸���������+void)����Classʵ��
public class Ref_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1.�������͵�Classʵ���ϵ System.out.println(int.class);
		System.out.println(boolean.class);
		System.out.println(float.class);
		System.out.println(double.class);
		System.out.println(byte.class);
		System.out.println(long.class);
		System.out.println(char.class);
		System.out.println(short.class);
		System.out.println();
		// ��ֵ�Ϳ���

		System.out.println(void.class);
		System.out.println(Void.class);
		System.out.println();
		// �������͵İ�װ��
		System.out.println(Integer.class);
		System.out.println(Boolean.class);
		System.out.println(Float.class);
		System.out.println(Double.class);
		System.out.println(Byte.class);
		System.out.println(Long.class);
		System.out.println(Character.class);
		System.out.println(Short.class);

		// �˴�������ͺ����װ������Ӧ���ֽ�������ǲ�ͬ��
		// ���ǰ�װ�඼��һ����̬�ĳ���TYPE��TYPE����Ӧ�ľ���ԭʼ���͵������

		System.out.println(int.class == Integer.class);// ���Ϊfalse
		System.out.println(int.class == Integer.TYPE);// ���Ϊtrue

		// 2.��ȡ������ֽ�����󣩵����ַ���
		// ����һ��ͨ��class��������ȡ(���е���+9���������Ͷ������Լ���class����)

		System.out.println(Person.class);
		Class c1 = Person.class;
		System.out.println(c1);

		// ��������ͨ����Ķ�������ȡ��ǰ����Ҫ֪��ĳ����Ķ���
		Class c2 = new Person().getClass();
		System.out.println(c1 == c2); // ��֤��c1 �� c2��ͬһ�����󣬼�Person���ֽ������

		// ���������õ���Class���forName()����
		try {
			Class<?> c3 = Class.forName("Reflect.Person");
			System.out.println(c1 == c3);
		} catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��ȡ������ֽ������
		// ���е�ά����ͬ��������ͬ�����鹲��һ���ֽ������

		int[] a = { 1, 2, 3 };
		int[] b = { 3, 2, 3, 4, 5, 6, 7 };
		int[][] c5 = { {}, {} };

		System.out.println(b.getClass() == a.getClass());
	//	System.out.println(a.getClass() == c5.getClass());

		Class c4 = int[].class; // ��ȡһ��һά������ֽ������
		System.out.println("c4 , c5");
		System.out.println(c4 == a.getClass());
		System.out.println(c4 == c5.getClass());
		System.out.println(a.getClass()+" "+float[].class);

		Person[] p = new Person[10];
		Person[] p1 = new Person[10];
		System.out.println(p.getClass() == p1.getClass());

		// ��ȡ���еĹ�����
		// ���裺
		// 1.��ȡ������Ӧ���ֽ������
		Class cp = Person.class;

		// getConstructors()��ȡ���е�public ������
		Constructor[] cs = cp.getConstructors();
		for (Constructor constructor : cs) {
			System.out.println(constructor);
		}

		// ��ȡ���еĹ������ķ�����getDeclaredConstructors(), ������private����public
		Constructor[] cs1 = cp.getDeclaredConstructors();
		for (Constructor constructor : cs1) {
			System.out.println(constructor);
		}

		// ��ȡһ��pulic������
		try {
			Constructor ct1 = cp.getConstructor();
			System.out.println(ct1);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// getDeclaredConstructor�������Ի�ȡ����Ȩ�޵�һ����������������public����private
			Constructor ct2 = cp.getDeclaredConstructor(String.class, int.class);
			System.out.println(ct2);
			try {
				Person p2 = (Person)ct2.newInstance("chi", 20); // ����ct2�������½�����
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

		// ͨ����������һ����Ķ����õ�����Constructor���е�newInstance()����
		try {
			Constructor dct = cp.getDeclaredConstructor(String.class);
			dct.setAccessible(true);// ����private�Ĺ�������Ҫ��������
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
