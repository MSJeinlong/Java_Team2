package com.demo2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User("1001", "张三", "男",20, "15917362227","29678854@qq.com");
		User user1 = new User("1002", "李四", "男",20, "15917439529","29678854@qq.com");
		String filePath = "G:/JavaTrain1/UsersData.txt";
/*		UserModel.getUserGroup().add(user);
		UserModel.getUserGroup().add(user1);*/
		//FileHelper.writeData(UserModel.getUserGroup(), filePath);
		UserModel.setUserGroup(FileHelper.readData(filePath));
		for(User u:UserModel.getUserGroup()) {
			System.out.println(u);
		}
	}
	
	private static void ReadWrite(List<User> l1, List<User> l2, String filePath ) {
		 l2 = l1;
//		try {
//			//Person对象序列化存储
//			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));	
//			oos.writeObject(l1);
//			//oos.writeObject(p1);
//			oos.close();
//			
//			//Person对象 反序列化提取
//			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
//			l2 = (List<User>)ois.readObject();
//			ois.close();  		//释放资
//			for(User u:l2) {
//				System.out.println(u);
//			}
//			User user1 = new User("1003", "王五", "男",20, "15917439529","29678854@qq.com");
//			l2.add(user1);
//			System.out.println(l2.size());
//			} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//return l2;
	}

}
