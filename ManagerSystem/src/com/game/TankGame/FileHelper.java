package com.game.TankGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 * 
 * @author JunLong �ļ�IO�����࣬������Ϊ�����࣬����Ӧ����Ƴɾ�̬ 1���Ӵ��̶�ȡ�ļ� 2����ʱ�����º����Ϣ���ݱ��浽����
 */
public class FileHelper {

	// �����ȡ�ļ��ı���
	private static ObjectInputStream ois = null;
	private static ObjectOutputStream oos = null;

	// 1���Ӵ��̶�ȡUsers�������ļ�
	// 2����readFile��Ƴɷ�����Ϊ���Ժ��ܶ�ȡ��ͬ�������ļ�
	// 3������ķ�������ȡ
	// readFile�����������ͣ�T ��ʾ List�����༯�ϴ�ŵ��Ǻ��ֶ��� filePath��ʾҪ��ȡ�ļ���·��
	// clazz �� T���ֽ������
	public static <T> LinkedList<T> readData(String filePath) {
		LinkedList<T> userGroup = null;
		FileInputStream fis = null;
		File f = new File(filePath);
		// �����ж��ļ��Ƿ����
		if (!f.exists()) {
			try {
				// �ļ��������򴴽��ļ�
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userGroup = new LinkedList<T>();
			// �ļ�Ϊ�գ�û������
		} else if (f.length() == 0) {
			userGroup = new LinkedList<T>();
		} else {
			try {
				fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				// ���϶���ķ����л���ȡ
				userGroup = (LinkedList<T>) ois.readObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				File file = new File(filePath);
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				e.printStackTrace();
			} finally {
				try {
					ois.close(); // �ر��ļ���
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return userGroup;
	}


	// ���������л����浽�ļ���
	public static <T> void writeData(LinkedList<T> userGroup, String filePath) {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			// ֱ�ӽ�userGroup���󼯺����л�д���ļ�
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

}
