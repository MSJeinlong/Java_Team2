package IO;

import java.io.*;

/**
 * 
 * 
 */
public class ByteArrayInpiutStream {

	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		String s = "huang, guo, quan";
//		byte[] by = s.getBytes();
//		ByteArrayInputStream bis = new ByteArrayInputStream(by);
//		int i = 0;
//		while((i = bis.read())!= -1) {	//从内存到内存的读操作
//			System.out.print((char)i);
//		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.write(97);
		bos.write('A');
		bos.write("jijisdf".getBytes());
/*		byte[] byteArray = bos.toByteArray();
		for(byte b:byteArray)
		{
			System.out.print((char)b);
		}*/
		String str = bos.toString();
		System.out.println(str);
		bos.writeTo(new FileOutputStream("f:\\t1.txt"));
		bos.close();
	}
	
}
