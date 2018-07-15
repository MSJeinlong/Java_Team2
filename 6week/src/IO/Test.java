package IO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "long";
		System.out.println(s.charAt(0));
		StringBuilder sb = new StringBuilder("Azure");
		sb.append(" By Microsoft");
		System.out.println(sb);
		sb.reverse();
		System.out.println(sb);
		System.out.println(java.io.File.separator);
		String st = java.io.File.separator;
		byte b = (byte)-130;
		System.out.println(b);
		//PrintWriter
		try {
			PrintWriter out = new PrintWriter("f:/t1.txt", "UTF-8");
			out.print("I am PrintWriter\n");
			out.println(21.5f);
			out.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		
		}
	}

}
