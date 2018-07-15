package video;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
 * @author JunLong
 *	�������л��ͷ����л��Լ����ͱ�����󼯺�
 */
public class Generic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new ������
		String filePath = null, filePath1 = null;
		Person p1 = new Person("����", 20);
		Person p2 = new Student("����", 30);
		LinkedList<Person> PersonList = new LinkedList<>();
		//��������뼯��
		PersonList.add(p1);
		PersonList.add(p2);
		filePath = "g:/Person.txt";
		//�����󼯺����л����浽�����ļ�
		FileTools.writeData(PersonList, filePath);
		//�����󼯺Ϸ����л��Ӵ����ļ���ȡ����
		LinkedList<Person> list = FileTools.readData(filePath);
		//��ӡ���󼯺�
		System.out.println("Person����");
		for(Person p:list) {
			System.out.print(p.toString()+", ");
			p.say();
		}
		
		LinkedList<Animal> AnimalList = new LinkedList<>();
		Animal a = new Animal();
		Animal cat = new Cat();
		
		AnimalList.add(a);
		AnimalList.add(cat);
		//�ļ�·��
		filePath = "g:/Animal.txt";
		//�����󼯺����л����浽�����ļ�
		FileTools.writeData(AnimalList, filePath);
		//�����󼯺Ϸ����л��Ӵ����ļ���ȡ����
		LinkedList<Animal> list1 = FileTools.readData(filePath);
		//��ӡ���󼯺�
		System.out.println("Animal����");
		for(Animal a1:list1) {
			a1.say();
		}
	}

}

//������ Person,ʵ�������л��ӿ�
class Person  implements Serializable{
	
	//private static final long serialVersionUID = 2L;
	private String name;
	private int age;
	
	public Person() {
		
	}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public void say() {
		System.out.println("I am a Person");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + this.getName() + ", age=" + this.getAge() + "]";
	}

}

//������ Student���̳�Person��
class Student extends Person{
	
	public Student(String name, int age) {
		//���ø��෽����ʼ��
		super(name, age);
	}
	
	@Override
	public String toString() {
		return "Student [name=" + this.getName() + ", age=" + this.getAge() + "]";
	}

	public void say() {
		System.out.println("I am a Student");
	}
	
	public void study(){
		System.out.println("I'am "+this.getName()+", I'am studying");
	}
}

class Animal implements Serializable{
	public void say() {
		System.out.println("I am a Animal");
	}
}

class Cat extends Animal{
	public void say() {
		System.out.println("I am a Cat");
	}
}
