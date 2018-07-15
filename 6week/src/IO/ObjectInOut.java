package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * ObjectOutputStream ��������л������ǽ����󣨰������������ͣ����ܹ�IO����д���ⲿ�ļ���ȥ���־����ļ���
 * ObjectInputStream ����ķ����л������ǽ��ⲿ�־����ļ���ͨ��IO������ԭ��ԭ���Ķ���
 * ֻ��ʵ����Serializabler�ӿڵ�����ܽ������л������л�
 * ������л���ʵ��java.io.Serializable�ӿڵ������á� ��ʵ�ִ˽ӿڵ��ཫ����ʹ�κ�״̬���л������л��� �����л�������������Ͷ��ǿ����л��ġ� ���л��ӿ�û�з������ֶΣ������ڱ�ʶ�ɴ��л������塣 
Ϊ���������л��������л��������ͣ������Ϳ��ܳе�����ͻָ������͵Ĺ������ܱ����ͣ�����ɷ��ʣ����ֶε�״̬�����Ρ� �����Ϳ��Գе������Σ�ֻ�е�����չ������пɷ��ʵ��޲������캯������ʼ�����״̬�� �����������������һ����Serializable��һ������ ����������ʱ��⵽�� 

�ڷ����л��ڼ䣬�ǿ����л�����ֶν�ʹ�ø����public��protected no-arg���캯�����г�ʼ���� ���ڿ����л������࣬������Է���no-arg���캯���� �����л�������ֶν������лָ��� 

������ͼ��ʱ�����ܻ�������֧��Serializable�ӿڵĶ��� ����������£����׳�NotSerializableException��������ʶ�������л�������ࡣ 


 * 
 * ObjectOutputStream��Java�����ԭʼ�������ͺ�ͼ��д��OutputStream�� ����ʹ��ObjectInputStream��ȡ���ع������� ����ͨ��ʹ�������ļ���ʵ�ֶ���ĳ־ô洢�� ������������׽����������������һ�������ϻ���һ���������ع����� 
 *	ֻ��֧��java.io.Serializable�ӿڵĶ������д�����С� ÿ�������л�������౻���룬�������������ǩ����������ֶκ������ֵ�Լ��ӳ�ʼ�������õ��κ���������Ĺرա� 
	����writeObject���ڽ�һ������д�����С� �κζ��󣬰����ַ��������飬������writeObject��д�ġ� ��������ԭ�����д������ ����Ӷ�Ӧ��ObjectInputstream��ȡ���������ͺ�д�������ͬ�� 
ԭʼ��������Ҳ����ʹ��DataOutput�е��ʵ�����д�����С� �ַ���Ҳ����ʹ��writeUTF����д�롣 
�����Ĭ�����л�����д�������࣬��ǩ���Լ����з�˲̬�ͷǾ�̬�ֶε�ֵ�� �����������󣨳�����˲̬��̬�ֶ��У�Ҳ�ᵼ����Щ����д�롣 ʹ�����ù�����ƶԵ�������Ķ�����ý��б��룬�Ա���Խ������ͼ�λָ�Ϊ��ԭʼ�ļ���ͬ����״��
 */
public class ObjectInOut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person("li", 20);
		Person p1 = new Person("��", 10);
		List<Person> lk = new LinkedList<>();
		lk.add(p);
		lk.add(p1);
		
		try {
			//Person�������л��洢
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("g:/jun.txt"));	
			oos.writeObject(lk);
			//oos.writeObject(p1);
			oos.close();
			
			//Person���� �����л���ȡ
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("g:/jun.txt"));
			List<Person> lk1 = (List<Person>)ois.readObject();
			ois.close();  		//�ͷ���Դ
			for(Person p3: lk1) {
				System.out.println(p3);
			}
			
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Person implements Serializable{	//��Person ʵ�� Serializable�ӿ�, �Ա㽫�������л��洢 �� �����л���ȡ

	private static final long serialVersionUID = 1295462628307446319L;
	private String name;
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	private int age;
	private int x;
	private int y;
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

}
