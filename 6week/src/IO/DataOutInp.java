package IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * DataOutputStream 数据输出流允许应用程序以适当方式将基本Java数据类型(如 byte, char, int ,float等)写入输入流中。
 * 然后，应用程序可以使用数据流将数据读入。public DataOutputStream(OutputStream out)
 */
public class DataOutInp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 1。 构建流(决定了流的方向），包括处理流
		FileOutputStream fos = new FileOutputStream("g:/long.txt");
		DataOutputStream dos = new DataOutputStream(fos);
		//2.进行读写操作
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
		//3.关闭文件，输入/输出流,特别是有缓冲区的
		dos.close();
		fos.close();
		
		//读取文件(二进制文件)
		DataInputStream dis = new DataInputStream(new FileInputStream("g:/long.txt"));
		System.out.println(dis.readInt());
		System.out.println(dis.readDouble());
		System.out.println(dis.readFloat());
		System.out.println(dis.readBoolean());
		System.out.println(dis.readUTF());
		System.out.println(dis.readChar());
		
	}

}
