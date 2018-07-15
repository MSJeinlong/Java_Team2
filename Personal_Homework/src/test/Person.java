package test;

public class Person implements Runnable{
	

	String name;
	int age;
	
	public Person() {
		
	}
	
	private Person(int age) {
		
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
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
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	//私有方法
	private void teach() {
		System.out.println("I am teaching");
	}

	//静态方法
	public static void eat() {
		System.out.println("I am eating");
	}
	
	//私有的静态方法
	private static void learn(String target) {
		System.out.println("I am learning "+target);
	}
}

class Student extends Person{
	public void say() {
		System.out.println("I am a student");
	}
}
