package IO;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

public class File1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f1 = new File("g:\\Youxun");
		File f12 = f1;
		System.out.println(f12.exists());
		System.out.println(f12.isDirectory());
		System.out.println(f1.mkdir());
		System.out.println(f1.getName());
		System.out.println(f12.length());

		f1.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".java");
			}
			
		});
		String[] list = f1.list();
		for(String fileName:list) {
			System.out.println(fileName);
		}
		File[] listFiles = f1.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".class");
			}
			
		});
		for(File file:listFiles) {
			System.out.println(file.getName()+":"+file.length());
		}
		File[] listFiles1 = f12.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.getName().endsWith(".class");
			}
			
		});
		for(File file:listFiles) {
			System.out.println(file.getName()+": "+file.length());
		}
		File f2 = new File("g:/long.txt");
		f2.createNewFile();
		//f2.delete();
		System.out.println(f2.exists());
		System.out.println(f2.isDirectory());
		System.out.println(f2.isFile());
	}

}
