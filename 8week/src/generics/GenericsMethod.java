package generics;
/**
 * ���еķ���������������������ķ��ͣ�
 * �����ڴ˷����ķ������ͣ��������ͺͷ����еľֲ��������ͣ�
 * ���е���������
 */
public class GenericsMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new A().print("huang");
		new A().print(4);
		new A().print(4.0);
		new A().print(false);
		new A().print(new MyDog());
	}

}

class A{
	public <T> void print(T t) {
		System.out.println(t);
	}
	//���ͷ�������
	//�޶����͵�����
	public <T extends Animal> void print(T t) {
		System.out.println(t);
		t.eat();
	}
}