package IOBu;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * printwriter printstream ֻ�������û�����룬û���쳣�׳������Զ���flush���ܣ�
 * 1���ض���ʱ����
 */
public class prints {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
/*		PrintStream ps = new PrintStream(new FileOutputStream("g:/print.txt", true));
		System.setOut(ps);//����ض���
		ps.write(12);
		ps.write(15);		
		
		System.out.println("Java Lesson is going, I love Coding");	
		ps.close();*/
		BufferedReader br = new  BufferedReader(new FileReader("g:/print.txt"));
		String line = null;
		while( (line = br.readLine()) != null) {
			//System.out.println(1);
			System.out.println(line);
		}
		br.close();
	}

}
