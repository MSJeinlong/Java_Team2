package video;

import java.io.*;
import java.util.*;
/**
 * 
 * @author JunLong
 *	利用序列化和反序列化以及泛型保存对象集合
 */
//工具类
public class FileTools {
	// 定义读取文件的变量
		private static ObjectInputStream ois = null;
		private static ObjectOutputStream oos = null;

				

		// 1、将方法设计成泛型是为了以后能处理不同对象集合的数据文件
		// 2、对象的序列化保存
		// 3、方法参数解释：T 表示 List的子类集合存放的是何种类型的对象， filePath表示要保存文件的路径
		// 4、因为是工具类，所以方法写才静态的
		// 5、将对象序列化保存到文件中
		// 6、list是要保存的对象集合, filePath是保存的文件路径
		public static <T> void writeData(LinkedList<T> list, String filePath) {
			try {
				oos = new ObjectOutputStream(new FileOutputStream(filePath));
				// 直接将list对象集合序列化写入文件
				oos.writeObject(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					//关闭文件流
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		// 对象集合的反序列提取
		//读取对象集合文件的方法，将读取出来的数据通过LinkedList<T>返回
		public static <T> LinkedList<T> readData(String filePath) {
			LinkedList<T> list = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(filePath));
				// 集合对象的反序列化提取
				list = (LinkedList<T>) ois.readObject();
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
			return list;
		}


		
}
