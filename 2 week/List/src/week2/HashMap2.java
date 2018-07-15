package week2;
import java.util.HashMap;
import java.util.Map;
public class HashMap2 {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Student, String> hashMap = new HashMap<Student, String>();
		hashMap.put(new Student(20, "chi" ,20),"c");
		hashMap.put(new Student(20, "jun", 20), "j");
		hashMap.put(new Student(20, "long", 20), "l");
		hashMap.put(new Student(20, "chi", 21), "c");
		System.out.println(hashMap.size());
	}

}

class Student{
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (ID != other.ID)
			return false;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNameString() {
		return name;
	}
	public void setNameString(String name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public Student(int age, String name, int id) {
		super();
		this.age = age;
		this.name = name;
		ID = id;
	}
	int age;
	String name;
	int ID;
	public String toString() {
		return age+":"+name;
	}
}
