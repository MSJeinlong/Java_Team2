package IO;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//ASCII 码 128
//ISO-8859-1 它总能表示256 个字符
//GB2312
//GBK
//unicode 16位
public class Code1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(new FileOutputStream("g:/test1.txt"));

			try {
				for (int i = 0; i < (1 << 16); i++) {
					dos.writeInt(i);
					// System.out.println((char)i);
				}
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
