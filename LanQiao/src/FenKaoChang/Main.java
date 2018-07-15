import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		/*
		 * ע�⣬Ҫ��ı�Ĭ�ϵ�����˳�򣬲���ʹ�û������ͣ�int,double, char�� ��Ҫʹ�����Ƕ�Ӧ����
		 */
		Integer[] a = { 9, 8, 7, 2, 3, 4, 1, 0, 6, 5 };
		// ����һ���Զ�����MyComparator�Ķ���
		Comparator cmp = new MyComparator();
		Arrays.sort(a, cmp);
		for (int arr : a) {
			System.out.print(arr + " ");
		}
	}
}

// ʵ��Comparator�ӿ�
class MyComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		/*
		 * ���o1С��o2�����Ǿͷ�����ֵ�����o1����o2���Ǿͷ��ظ�ֵ�� �����ߵ�һ�£��Ϳ���ʵ�ֽ���������,��֮�����Զ�������������
		 */
		return o2 - o1;
	}

}