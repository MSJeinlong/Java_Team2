package Generic;

//����
//����������ֻ���������Ͳ�һ���������Ľṹ��ȫһ����д�ܶ����
//Solution1�������Ͷ�д��Object,�ڽ�������ת���ǻ����
//Solution2,���͵�ʵ�ʾ��ǽ����������Ͳ������������������е�һ����ʽ�� i ����
public class Generic1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fan1<String>  f1 = new Fan1<String>("huang");
		Fan1<Integer> f2 = new Fan1<Integer>(1);
		System.out.println(f1.getData());
	}

}

//��Ʒ�����
class Fan1<T> {
	private T data;

	public Fan1(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	} 
}