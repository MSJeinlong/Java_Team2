package reflect;

import java.util.ArrayList;

public class ObjectAnalyzerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> squares = new ArrayList<>();
		for(int i = 1; i <= 5; i++)
			squares.add(i * i);
		System.out.println(new objectAnalyzer().toString(squares));
	}

}