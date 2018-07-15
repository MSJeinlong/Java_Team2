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
		if (formFile.isDirectory()) { // 判断是文件目录
			// 就地新建文件夹
			File newDir = new File(toFile, formFile.getName());
			newDir.mkdirs();
			// 获取该文件夹下的所有文件
			File[] fileArray = formFile.listFiles();
			for (File file : fileArray) {
				copyDir(file, newDir); // 递归调用，对该文件夹下的所有文件/文件夹进行复制
			}
		} else {
			File newFile = new File(toFile, formFile.getName());
			copyFile(formFile, newFile);
		}
	}

	private static void copyFile(File formFile, File newFile) throws IOException {
		// TODO Auto-generated method stub
		// 文件大于 1k,设置缓冲区
		long fileSize = formFile.length();
		if (fileSize > 1024) {
			// .txt, .java, .class等用字符流
			if (formFile.getName().endsWith(".txt") || formFile.getName().endsWith(".java")
					|| formFile.getName().endsWith(".class")) {
				
				BufferedReader br = new BufferedReader(new FileReader(formFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
				String line = null;
				while ((line = br.readLine()) != null) { // 方法readline 读入一行文字，返回类型为String
					// System.out.println(line);
					bw.write(line, 0, line.length());
					bw.newLine();
				}
				bw.close();
				br.close();
			} else { // 其他文件如 mp3, png, jpg等用字节流
				
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(formFile));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
				byte[] b = new byte[(int) (fileSize / 10)];// 缓冲区大小设置为文件大小的10%
				int len = 0;
				while ((len = bis.read(b)) != -1) {
					bos.write(b, 0, len);
				}
				bos.close();
				bis.close();
			}
		} else {// 文件大于 1k,不设置缓冲区
				// 尚未处理
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
