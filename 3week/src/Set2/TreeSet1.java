package Set2;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

//TreeSet��set�е�һ������.������Ȼ�����������ĵײ���ʵ��װ��TreeMap����������ʵ��TreeMap�ļ�
//���뵽TreeSet�����еĶ���һ���ǰ���Ȼ����ģ�����ʵ��Comparable�ӿڻ�������дһ������������ȥʵ��Compara����
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
		ts.add(new Person("jun", 100));	//�����ӵ�Ԫ�صļ�ֵ��TreeMap���еļ�ֵ�ظ�ʱ�����޷���ӳɹ�
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
	//�Ƚϵ��Ǹ�����
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
