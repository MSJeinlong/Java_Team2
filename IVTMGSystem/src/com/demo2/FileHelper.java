package com.demo2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author JunLong 文件IO操作类，该类作为工具类，所以应该设计成静态 1、从磁盘读取文件 2、及时将更新后的信息数据保存到磁盘
 */
public class FileHelper {

	// 定义读取文件的变量
	private static ObjectInputStream ois = null;
	private static ObjectOutputStream oos = null;

	// 1、从磁盘读取Users的数据文件
	// 2、将readFile设计成泛型是为了以后能读取不同的数据文件
	// 3、对象的反序列提取
	// readFile方法参数解释：T 表示 List的子类集合存放的是何种对象， filePath表示要读取文件的路径
	// clazz 类 T的字节码对象
	public static <T>LinkedList<T>readData(String filePath) {
		LinkedList<T> userGroup = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));
			// 集合对象的反序列化提取
			userGroup  = (LinkedList<T>)ois.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ois.close(); // 关闭文件流
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userGroup;
	}

	// 将对象序列化保存到文件中
	public static <T>void writeData(LinkedList<T> userGroup, String filePath) {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			//直接将userGroup对象集合序列化写入文件
			oos.writeObject(userGroup);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 泛型反射，通过反射机制获取，获取泛型的构造器，进而new 出实例
	private static <T> T newTclass(Class<T> clazz) {
		T a = null;
		try {
			a = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
}
