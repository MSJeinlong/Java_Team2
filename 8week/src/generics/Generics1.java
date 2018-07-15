package generics;

/**
 * //限制泛型可用类型：实例化泛型类的时候可以用任何类型，但是如果想要限制使用泛型类型时，可以在定义类型时，
//使用extends关键字指定这个类型必须是继承某个类或实现某个接口
//当没有限定时, class A<T> === class A<T extends Object>,默认可以传入任何类型的参数。
//泛型中的通配符的使用 ? 同一泛型类，如果实例化时给定的实际类型不同，则这些实例的类型时不兼容的
//不能相互赋值。A<String> f1 和 A<Integer> f2 ，令f1 = f2 是不对的
 *泛型实例之间的不兼容会带来使用的不方便，可以使用泛型通配符“？”来解决这个问题，无限定通配符；A<String> f1, A<?> f2, 令f2 = f1 合法
 *  ? 表示任意一个类型，可以用extends 限定通配符类型的上限；上边界限定通配符A<? extends Animal> f = A<Dog>(new Dog());
 *  可以使用super将通配符类型限定为某个类型及其父类型；下边界限定通配符：A<? super Dog> 
 */
public class Generics1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*第一个知识点：限制泛型的可用类型*/
/*		FanXin2<Animal> f1 = new FanXin2<Animal>(new Dog());
		f1.getT().eat();
		FanXin2<Cat> f2 = new FanXin2<Cat>(new Cat());
		f2.getT().eat();*/
		/*第二个知识点：泛型中的无限定通配符？*/
		FanXin2<Animal> f1 = new FanXin2<Animal>(new Cat());
		FanXin2<Dog> f2 = new FanXin2<Dog>(new Dog());
		FanXin2<? > f3 = f1;
		f3.getT().eat();
		//第三个知识点：可以用extends 限定通配符匹配类型的上限
		FanXin2<? extends Animal> f4 = f2;
		FanXin2<? super Dog> f5 = f1;
		FanXin2<? super Dog> f6 = new FanXin2<Dog>(new MyDog());
		f6.getT().eat();
		System.out.println(f4+"\n"+f5);
	}

}

class FanXin2<T extends Animal>{//extends来限定泛型的类型
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
