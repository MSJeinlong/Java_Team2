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
 * InputStreamWriter �� OutputStreamWriter ���ֽ���ͨ���ַ��������� ����ת�����������ִ������
 * 1���������ֽ������ַ���ת�� 2�����Զ��ַ����������صĲ������ӹ��췽������Կ�����
 */
public class ChangeStream {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("g:/ops.txt"), "utf-8");
		osw.write("chi");
		osw.write("�й�");
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
