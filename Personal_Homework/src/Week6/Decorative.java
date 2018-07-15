package Week6;
/**
 * װ��ģʽ�����
 */
public class Decorative {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Component component = new ConcreComponent();
		Component component1 = new ConDecorator1(component);
		component1.doSomething();
		System.out.println("******************");
		Component component2 = new ConDecorator2(component1);
		component2.doSomething();
	}

}

interface Component{
	public void doSomething();
}

class ConcreComponent implements Component{

	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		System.out.println("����A");
	}
	
}

class Decorator implements Component{

	private Component component;
	
	
	
	public Decorator(Component component) {
		this.component = component;
	}



	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		component.doSomething();
	}
	
}

class ConDecorator1 extends Decorator{

	public ConDecorator1(Component component) {
		super(component);//�����ʼ��
		// TODO Auto-generated constructor stub
	}
	
	public void doSomething() {
		super.doSomething();//����ķ���
		this.doAnotherThing();//����ķ���
	}
	
	public void doAnotherThing() {
		System.out.println("����B");
	}
}

class ConDecorator2 extends Decorator{

	public ConDecorator2(Component component) {
		super(component);//�����ʼ��
		// TODO Auto-generated constructor stub
	}
	
	public void doSomething() {
		super.doSomething();//����ķ���
		this.doAnotherThing();//����ķ���
	}
	
	public void doAnotherThing() {
		System.out.println("����C");
	}
}
