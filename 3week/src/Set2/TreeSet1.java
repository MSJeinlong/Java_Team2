package Set2;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

//TreeSet是set中的一个特例.它按自然升序排序。它的底层其实封装了TreeMap，操作的其实是TreeMap的键
//加入到TreeSet容器中的对象一定是按自然升序的（本类实现Comparable接口或者另外写一个第三方的类去实现Compara方法
public class TreeSet1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<Person> ts = new TreeSet<>(new Com());
		ts.add(new Person("chi", 50));
		ts.add(new Person("jun", 60));
		ts.add(new Person("long", 70));
		ts.add(new Person("king", 80));
		ts.add(new Person("chi", 90));
		ts.add(new Person("zong", 30));
		ts.add(new Person("jun", 100));	//如果添加的元素的键值与TreeMap已有的键值重复时，则无法添加成功
		System.out.println(ts);
		System.out.println();
		System.out.println("max = "+Collections.max(ts));
		Iterator<Person> it = ts.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}

class Com implements Comparator<Person>{

	@Override
	//比较的是个两个
	public int compare(Person o1, Person o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}
	
}

class Person implements Comparable<Person>{
	
	int age;
	String name;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return	name+":"+age;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
//		if( this.age > o.age) {
//			return 1;
//		} else if( this.age < o.age) {
//			return -1;
//		}else 
//		return 0;
		int x = this.name.compareTo(o.name);
		if(x == 1)
			return 1;
		else if( x == -1)
			return -1;
		else {
			if(this.age > o.age)
				return 1;
			else if(this.age < o.age)
				return -1;
			else
				return 0;
		}
		//return this.name.compareTo(o.name);
	}


}
