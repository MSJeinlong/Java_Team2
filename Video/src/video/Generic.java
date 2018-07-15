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
 *	利用序列化和反序列化以及泛型保存对象集合
 */
public class Generic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new 出对象
		String filePath = null, filePath1 = null;
		Person p1 = new Person("张三", 20);
		Person p2 = new Student("李四", 30);
		LinkedList<Person> PersonList = new LinkedList<>();
		//将对象加入集合
		PersonList.add(p1);
		PersonList.add(p2);
		filePath = "g:/Person.txt";
		//将对象集合序列化保存到磁盘文件
		FileTools.writeData(PersonList, filePath);
		//将对象集合反序列化从磁盘文件读取出来
		LinkedList<Person> list = FileTools.readData(filePath);
		//打印对象集合
		System.out.println("Person集合");
		for(Person p:list) {
			System.out.print(p.toString()+", ");
			p.say();
		}
		
		LinkedList<Animal> AnimalList = new LinkedList<>();
		Animal a = new Animal();
		Animal cat = new Cat();
		
		AnimalList.add(a);
		AnimalList.add(cat);
		//文件路径
		filePath = "g:/Animal.txt";
		//将对象集合序列化保存到磁盘文件
		FileTools.writeData(AnimalList, filePath);
		//将对象集合反序列化从磁盘文件读取出来
		LinkedList<Animal> list1 = FileTools.readData(filePath);
		//打印对象集合
		System.out.println("Animal集合");
		for(Animal a1:list1) {
			a1.say();
		}
	}

}

//测试类 Person,实现了序列化接口
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

//测试类 Student，继承Person类
class Student extends Person{
	
	public Student(String name, int age) {
		//调用父类方法初始化
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
