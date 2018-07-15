package com.game.TankGame;

import java.io.Serializable;

/**
 * 
 * @author JunLong
 * User类
 * 描述用户信息的一个类，该类指定用户的属性和动作
 */
public class User implements Serializable{

	 private static final long serialVersionUID = 1L;
	//编号
	private String ID;
	//姓名
	private String name;
	//性别
	private String sex;
	//年龄
	private int age;
	//手机号码
	private String PhoneNum;
	//电子邮箱
	private String Email;
	//密码
	private String passwd;
	

	//无参的构造器
	public User() {
		
	}
	
	@Override
	public String toString() {
		return "User [ID=" + ID + ", name=" + name + ", sex=" + sex + ", age=" + age + ", PhoneNum=" + PhoneNum
				+ ", Email=" + Email + "]";
	}

	public User(String iD, String name, String sex, int age, String phoneNum, String email) {
		super();
		ID = iD;
		this.name = name;
		this.sex = sex;
		this.age = age;
		PhoneNum = phoneNum;
		Email = email;
	}
	
	//属性的Setter和Getter方法
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
