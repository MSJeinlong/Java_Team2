package video;

import java.io.*;
import java.util.*;
/**
 * 
 * @author JunLong
 *	�������л��ͷ����л��Լ����ͱ�����󼯺�
 */
//������
public class FileTools {
	// �����ȡ�ļ��ı���
		private static ObjectInputStream ois = null;
		private static ObjectOutputStream oos = null;

				

		// 1����������Ƴɷ�����Ϊ���Ժ��ܴ���ͬ���󼯺ϵ������ļ�
		// 2����������л�����
		// 3�������������ͣ�T ��ʾ List�����༯�ϴ�ŵ��Ǻ������͵Ķ��� filePath��ʾҪ�����ļ���·��
		// 4����Ϊ�ǹ����࣬���Է���д�ž�̬��
		// 5�����������л����浽�ļ���
		// 6��list��Ҫ����Ķ��󼯺�, filePath�Ǳ�����ļ�·��
		public static <T> void writeData(LinkedList<T> list, String filePath) {
			try {
				oos = new ObjectOutputStream(new FileOutputStream(filePath));
				// ֱ�ӽ�list���󼯺����л�д���ļ�
				oos.writeObject(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					//�ر��ļ���
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		// ���󼯺ϵķ�������ȡ
		//��ȡ���󼯺��ļ��ķ���������ȡ����������ͨ��LinkedList<T>����
		public static <T> LinkedList<T> readData(String filePath) {
			LinkedList<T> list = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(filePath));
				// ���϶���ķ����л���ȡ
				list = (LinkedList<T>) ois.readObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ois.close(); // �ر��ļ���
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list;
		}


		
}
