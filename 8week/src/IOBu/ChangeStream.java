package IOBu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * InputStreamWriter 和 OutputStreamWriter 是字节流通向字符流的桥梁 对于转换流，有两种大的作用
 * 1、可用于字节流向字符的转换 2、可以对字符编码进行相关的操作（从构造方法里可以看到）
 */
public class ChangeStream {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("g:/ops.txt"), "utf-8");
		osw.write("chi");
		osw.write("中国");
		System.out.println(osw.getEncoding());
		osw.close();

		/*
		 * BufferedReader br = new BufferedReader(new FileReader("g:/ops.txt")); String
		 * s = null; while((s = br.readLine()) != null) { System.out.println(s); }
		 * br.close();
		 */

/*		InputStreamReader isr = new InputStreamReader(new FileInputStream("g:/ops.txt"), "utf-8");
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}
		br.close();*/
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		while ((s = br.readLine()) != null) {
			if("chi".equalsIgnoreCase(s)) {
				break;
			}
			System.out.println(s);
		}
		br.close();
	}

}
