package video;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collects {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ���½�һ��List���󣬽�����Ϊ int �͵Ķ�̬����
		List<Integer> a = new ArrayList<>();
		// ��� 6 ����
		a.add(82);
		a.add(1);
		a.add(9876);
		a.add(90);
		a.add(90);
		a.add(103);
		
				
		// 1��sort() 
/*		List<Integer> b = new ArrayList<>(a);
		// ����ǰ
		print(a);
		//���������
		Collections.sort(a);
		print(a);
		//��������
		Collections.sort(b, Collections.reverseOrder());
		print(b);*/
		
		//2��swap()
/*		System.out.println("����ǰ ");
		print(a);
		Collections.swap(a, 0, a.size() - 1);
		System.out.println("������");
		print(a);*/
		
		//3��replaceAll()
/*		System.out.println("�滻ǰ");
		print(a);
		Collections.replaceAll(a, 90, 100);
		System.out.println("�滻ǰ");
		print(a);*/
		
		//4��reverse()
/*		System.out.println("����ǰ");
		print(a);
		System.out.println("�����");
		Collections.reverse(a);
		print(a);*/
		
		//5.6
		print(a);
		System.out.println("max = "+Collections.max(a));
		System.out.println("min = "+Collections.min(a));
	}

	//��ӡList�б�ķ���
	private static void print(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println();
	}
}