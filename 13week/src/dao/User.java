package dao;

public class User {
	
private int id;
private String name;
private int age;

  public User(String name, int age) {
	super();
	this.name = name;
	this.age = age;
}

  public User(){
	  
  }
  
  public String toString(){
	  return id+": "+name+": "+age;
  }
  
  public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
