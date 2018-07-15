package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * BufferedWriter 将文本写入字符输出流，缓冲字符，以提供单个字符，数组和字符串的高效写入。 可以指定缓冲区大小，或者可以接受默认大小。
 * 默认值足够大，可用于大多数用途。
 * 
 * BufferedReader 从字符输入流读取文本，缓冲字符，以提供字符，数组和行的高效读取。
 */

public class BufferRWder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader fr = new FileReader("g:/Array2.java");
			BufferedReader br = new BufferedReader(fr);

			BufferedWriter bw = new BufferedWriter(new FileWriter("g:/chi.java"));
			String line = null;
			while ((line = br.readLine()) != null) { // 方法readline 读入一行文字，返回类型为String
				//System.out.println(line);
				bw.write(line, 0, line.length());
				bw.newLine();
			}
			bw.close();
			br.close();

			// BufferedReader 对比  FileReader
			FileReader fileR = new FileReader("g:/chi.java");
			char[] ch = new char[8192];//手动创建缓冲区
			while ( fileR.read(ch, 0, ch.length) != -1) {
				System.out.println(ch);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
