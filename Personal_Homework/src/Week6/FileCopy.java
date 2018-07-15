package Week6;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File formFile = new File("g:/Test");
		File toFile = new File("F:/");
		copyDir(formFile, toFile);
	}

	private static void copyDir(File formFile, File toFile) throws IOException {
		if (formFile.isDirectory()) { // �ж����ļ�Ŀ¼
			// �͵��½��ļ���
			File newDir = new File(toFile, formFile.getName());
			newDir.mkdirs();
			// ��ȡ���ļ����µ������ļ�
			File[] fileArray = formFile.listFiles();
			for (File file : fileArray) {
				copyDir(file, newDir); // �ݹ���ã��Ը��ļ����µ������ļ�/�ļ��н��и���
			}
		} else {
			File newFile = new File(toFile, formFile.getName());
			copyFile(formFile, newFile);
		}
	}

	private static void copyFile(File formFile, File newFile) throws IOException {
		// TODO Auto-generated method stub
		// �ļ����� 1k,���û�����
		long fileSize = formFile.length();
		if (fileSize > 1024) {
			// .txt, .java, .class�����ַ���
			if (formFile.getName().endsWith(".txt") || formFile.getName().endsWith(".java")
					|| formFile.getName().endsWith(".class")) {
				
				BufferedReader br = new BufferedReader(new FileReader(formFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
				String line = null;
				while ((line = br.readLine()) != null) { // ����readline ����һ�����֣���������ΪString
					// System.out.println(line);
					bw.write(line, 0, line.length());
					bw.newLine();
				}
				bw.close();
				br.close();
			} else { // �����ļ��� mp3, png, jpg�����ֽ���
				
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(formFile));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
				byte[] b = new byte[(int) (fileSize / 10)];// ��������С����Ϊ�ļ���С��10%
				int len = 0;
				while ((len = bis.read(b)) != -1) {
					bos.write(b, 0, len);
				}
				bos.close();
				bis.close();
			}
		} else {// �ļ����� 1k,�����û�����
				// ��δ����
			FileInputStream fis = new FileInputStream(formFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			int len = 0;
			while ((len = fis.read()) != -1) {
				fos.write(len);
			}
			fos.close();
			fis.close();
		}
	}
}
