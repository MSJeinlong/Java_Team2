package com.game.TankGame;

import java.io.Serializable;

/**
 * 
 * @author JunLong
 * User��
 * �����û���Ϣ��һ���࣬����ָ���û������ԺͶ���
 */
public class User implements Serializable{

	 private static final long serialVersionUID = 1L;
	//���
	private String ID;
	//����
	private String name;
	//�Ա�
	private String sex;
	//����
	private int age;
	//�ֻ�����
	private String PhoneNum;
	//��������
	private String Email;
	//����
	private String passwd;
	

	//�޲εĹ�����
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
	
	//���Ե�Setter��Getter����
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
