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
 * ObjectOutputStream 对象的序列化，就是将对象（包括基本数类型），能过IO流，写到外部文件中去（持久性文件）
 * ObjectInputStream 对象的反序列化，就是将外部持久性文件，通过IO流，还原成原来的对象
 * 只有实现了Serializabler接口的类才能进行序列化或反序列化
 * 类的序列化由实现java.io.Serializable接口的类启用。 不实现此接口的类将不会使任何状态序列化或反序列化。 可序列化类的所有子类型都是可序列化的。 序列化接口没有方法或字段，仅用于标识可串行化的语义。 
为了允许序列化不可序列化的子类型，子类型可能承担保存和恢复超类型的公共，受保护和（如果可访问）包字段的状态的责任。 子类型可以承担此责任，只有当它扩展的类具有可访问的无参数构造函数来初始化类的状态。 如果不是这样，声明一个类Serializable是一个错误。 错误将在运行时检测到。 

在反序列化期间，非可序列化类的字段将使用该类的public或protected no-arg构造函数进行初始化。 对于可序列化的子类，必须可以访问no-arg构造函数。 可序列化子类的字段将从流中恢复。 

当遍历图形时，可能会遇到不支持Serializable接口的对象。 在这种情况下，将抛出NotSerializableException，并将标识不可序列化对象的类。 


 * 
 * ObjectOutputStream将Java对象的原始数据类型和图形写入OutputStream。 可以使用ObjectInputStream读取（重构）对象。 可以通过使用流的文件来实现对象的持久存储。 如果流是网络套接字流，则可以在另一个主机上或另一个进程中重构对象。 
 *	只有支持java.io.Serializable接口的对象才能写入流中。 每个可序列化对象的类被编码，包括类的类名和签名，对象的字段和数组的值以及从初始对象引用的任何其他对象的关闭。 
	方法writeObject用于将一个对象写入流中。 任何对象，包括字符串和数组，都是用writeObject编写的。 多个对象或原语可以写入流。 必须从对应的ObjectInputstream读取对象，其类型和写入次序相同。 
原始数据类型也可以使用DataOutput中的适当方法写入流中。 字符串也可以使用writeUTF方法写入。 
对象的默认序列化机制写入对象的类，类签名以及所有非瞬态和非静态字段的值。 引用其他对象（除了在瞬态或静态字段中）也会导致这些对象被写入。 使用引用共享机制对单个对象的多个引用进行编码，以便可以将对象的图形恢复为与原始文件相同的形状。
 */
public class ObjectInOut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person("li", 20);
		Person p1 = new Person("孙", 10);
		List<Person> lk = new LinkedList<>();
		lk.add(p);
		lk.add(p1);
		
		try {
			//Person对象序列化存储
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("g:/jun.txt"));	
			oos.writeObject(lk);
			//oos.writeObject(p1);
			oos.close();
			
			//Person对象 反序列化提取
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("g:/jun.txt"));
			List<Person> lk1 = (List<Person>)ois.readObject();
			ois.close();  		//释放资源
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

class Person implements Serializable{	//让Person 实现 Serializable接口, 以便将对象序列化存储 和 反序列化提取

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
