package generics;

/**
 * //���Ʒ��Ϳ������ͣ�ʵ�����������ʱ��������κ����ͣ����������Ҫ����ʹ�÷�������ʱ�������ڶ�������ʱ��
//ʹ��extends�ؼ���ָ��������ͱ����Ǽ̳�ĳ�����ʵ��ĳ���ӿ�
//��û���޶�ʱ, class A<T> === class A<T extends Object>,Ĭ�Ͽ��Դ����κ����͵Ĳ�����
//�����е�ͨ�����ʹ�� ? ͬһ�����࣬���ʵ����ʱ������ʵ�����Ͳ�ͬ������Щʵ��������ʱ�����ݵ�
//�����໥��ֵ��A<String> f1 �� A<Integer> f2 ����f1 = f2 �ǲ��Ե�
 *����ʵ��֮��Ĳ����ݻ����ʹ�õĲ����㣬����ʹ�÷���ͨ��������������������⣬���޶�ͨ�����A<String> f1, A<?> f2, ��f2 = f1 �Ϸ�
 *  ? ��ʾ����һ�����ͣ�������extends �޶�ͨ������͵����ޣ��ϱ߽��޶�ͨ���A<? extends Animal> f = A<Dog>(new Dog());
 *  ����ʹ��super��ͨ��������޶�Ϊĳ�����ͼ��丸���ͣ��±߽��޶�ͨ�����A<? super Dog> 
 */
public class Generics1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*��һ��֪ʶ�㣺���Ʒ��͵Ŀ�������*/
/*		FanXin2<Animal> f1 = new FanXin2<Animal>(new Dog());
		f1.getT().eat();
		FanXin2<Cat> f2 = new FanXin2<Cat>(new Cat());
		f2.getT().eat();*/
		/*�ڶ���֪ʶ�㣺�����е����޶�ͨ�����*/
		FanXin2<Animal> f1 = new FanXin2<Animal>(new Cat());
		FanXin2<Dog> f2 = new FanXin2<Dog>(new Dog());
		FanXin2<? > f3 = f1;
		f3.getT().eat();
		//������֪ʶ�㣺������extends �޶�ͨ���ƥ�����͵�����
		FanXin2<? extends Animal> f4 = f2;
		FanXin2<? super Dog> f5 = f1;
		FanXin2<? super Dog> f6 = new FanXin2<Dog>(new MyDog());
		f6.getT().eat();
		System.out.println(f4+"\n"+f5);
	}

}

class FanXin2<T extends Animal>{//extends���޶����͵�����
	public T t;

	public FanXin2(T t) {
		super();
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
}

interface Animal{
	public abstract void eat();
}

class Dog implements Animal{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("dog is eating");
	}
	
}

class Cat implements Animal{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("cat is eating");
	}
	

}

class MyDog extends Dog{
	
	public void eat() {
		System.out.println("MyDog is eating");
	}
	
	public void Sleep() {
		System.out.println("MyDog is Sleep");
	}
}
