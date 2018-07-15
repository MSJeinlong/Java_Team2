package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * BufferedWriter ���ı�д���ַ�������������ַ������ṩ�����ַ���������ַ����ĸ�Чд�롣 ����ָ����������С�����߿��Խ���Ĭ�ϴ�С��
 * Ĭ��ֵ�㹻�󣬿����ڴ������;��
 * 
 * BufferedReader ���ַ���������ȡ�ı��������ַ������ṩ�ַ���������еĸ�Ч��ȡ��
 */

public class BufferRWder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader fr = new FileReader("g:/Array2.java");
			BufferedReader br = new BufferedReader(fr);

			BufferedWriter bw = new BufferedWriter(new FileWriter("g:/chi.java"));
			String line = null;
			while ((line = br.readLine()) != null) { // ����readline ����һ�����֣���������ΪString
				//System.out.println(line);
				bw.write(line, 0, line.length());
				bw.newLine();
			}
			bw.close();
			br.close();

			// BufferedReader �Ա�  FileReader
			FileReader fileR = new FileReader("g:/chi.java");
			char[] ch = new char[8192];//�ֶ�����������
			while ( fileR.read(ch, 0, ch.length) != -1) {
				System.out.println(ch);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
