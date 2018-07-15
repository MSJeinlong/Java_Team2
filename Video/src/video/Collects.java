package video;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collects {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 先新建一个List对象，将其作为 int 型的动态数组
		List<Integer> a = new ArrayList<>();
		// 添加 6 个数
		a.add(82);
		a.add(1);
		a.add(9876);
		a.add(90);
		a.add(90);
		a.add(103);
		
				
		// 1、sort() 
/*		List<Integer> b = new ArrayList<>(a);
		// 排序前
		print(a);
		//升序排序后
		Collections.sort(a);
		print(a);
		//降序排序
		Collections.sort(b, Collections.reverseOrder());
		print(b);*/
		
		//2、swap()
/*		System.out.println("交换前 ");
		print(a);
		Collections.swap(a, 0, a.size() - 1);
		System.out.println("交换后");
		print(a);*/
		
		//3、replaceAll()
/*		System.out.println("替换前");
		print(a);
		Collections.replaceAll(a, 90, 100);
		System.out.println("替换前");
		print(a);*/
		
		//4、reverse()
/*		System.out.println("逆序前");
		print(a);
		System.out.println("逆序后");
		Collections.reverse(a);
		print(a);*/
		
		//5.6
		print(a);
		System.out.println("max = "+Collections.max(a));
		System.out.println("min = "+Collections.min(a));
	}

	//打印List列表的方法
	private static void print(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println();
	}
}