package Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D1004 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> stus = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int n = Integer.parseInt(s);
		for(int i = 0; i < n; i++) {
			String[] str = sc.nextLine().split(" ");
			Student st = new Student(str[0], str[1], Integer.parseInt(str[2]));
			stus.add(st);
		}
		Student Max = stus.get(0), min = stus.get(0);
		for(int i = 1; i < stus.size(); i++) {
			Student st = stus.get(i);
			if(Max.getScore() < st.getScore())
				Max = st;
			if(min.getScore() > st.getScore())
				min = st;
		}
		System.out.println(Max.getName()+" "+Max.getId());
		System.out.println(min.getName()+" "+min.getId());
	}
	

}

class Student{
	private String name;
	private String id;
	private int score;
	
	public Student(String name, String id, int score) {
		this.name = name;
		this.id = id;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	
}
