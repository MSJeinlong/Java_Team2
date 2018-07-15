package generics;
/**
 * 类中的方法可以声明仅用于自身的泛型，
 * 可用于此方法的返回类型，参数类型和方法中的局部变量类型，
 * 类中的其他方法
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
	//泛型方法重载
	//限定泛型的类型
	public <T extends Animal> void print(T t) {
		System.out.println(t);
		t.eat();
	}
}