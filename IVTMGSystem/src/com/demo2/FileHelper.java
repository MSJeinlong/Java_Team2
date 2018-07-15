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
	public static <T>LinkedList<T>readData(String filePath) {
		LinkedList<T> userGroup = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));
			// ���϶���ķ����л���ȡ
			userGroup  = (LinkedList<T>)ois.readObject();
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
		return userGroup;
	}

	// ���������л����浽�ļ���
	public static <T>void writeData(LinkedList<T> userGroup, String filePath) {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			//ֱ�ӽ�userGroup���󼯺����л�д���ļ�
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

	// ���ͷ��䣬ͨ��������ƻ�ȡ����ȡ���͵Ĺ�����������new ��ʵ��
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
