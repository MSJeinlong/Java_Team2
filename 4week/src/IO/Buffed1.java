package IO;

import java.io.*;

//������(��װ����װ�Σ� ��������
public class Buffed1 {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("c:\\T1.java");
		BufferedInputStream bis = new BufferedInputStream(fis);
		new FileOutputStream("h:/");
		int i = 0;
		while( (i=bis.read()) != -1) {
			
		}
	}

}
