package IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * DataOutputStream �������������Ӧ�ó������ʵ���ʽ������Java��������(�� byte, char, int ,float��)д���������С�
 * Ȼ��Ӧ�ó������ʹ�������������ݶ��롣public DataOutputStream(OutputStream out)
 */
public class DataOutInp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 1�� ������(���������ķ��򣩣�����������
		FileOutputStream fos = new FileOutputStream("g:/long.txt");
		DataOutputStream dos = new DataOutputStream(fos);
		//2.���ж�д����
		int x = 12;
		float y = 12.1f;
		double z = 12.3;
		boolean b = false;
		String name = "chi";
		char c = 'v';
		dos.writeInt(x);
		dos.writeDouble(z);
		dos.writeFloat(y);
		dos.writeBoolean(b);
		dos.writeUTF(name);
		dos.writeChar(c);
		//3.�ر��ļ�������/�����,�ر����л�������
		dos.close();
		fos.close();
		
		//��ȡ�ļ�(�������ļ�)
		DataInputStream dis = new DataInputStream(new FileInputStream("g:/long.txt"));
		System.out.println(dis.readInt());
		System.out.println(dis.readDouble());
		System.out.println(dis.readFloat());
		System.out.println(dis.readBoolean());
		System.out.println(dis.readUTF());
		System.out.println(dis.readChar());
		
	}

}
