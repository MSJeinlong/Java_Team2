package chi;
/**
 * 静态代理模式的设计
 * 1、要有一个真实的角色
 * 2、代理的角色:一定要有对真实角色的引用
 * 3、以上二者要实现同一个接口
 *
 */
public class StaticProxy {

	public static void main(String[] args){
		//1、首先要有一个真实的对象
		//2、要有一个代理角色的对象（注意把真实对象传递到代理对象中）
		//3、通过代理对象来调用真实对象
		Study xh = new Student();
		JGProxy pr = new JGProxy(xh);
		pr.study();
	}
}

interface Study{
	void study();
}

class Student implements Study{

	@Override
	public void study() {
		System.out.println("I am studying Java SE");
		
	}
	
}

class JGProxy implements Study{

	private Study xh;	//代理者用这种方式来引用真实角色
	
	public JGProxy(Study xh) {
		super();
		this.xh = xh;
	}

	private void beforeStudy(){
		System.out.println("it's necessary to study C language.");
	}
	
	@Override
	public void study() {
		// TODO Auto-generated method stub
		beforeStudy();
		xh.study();
		nextStudy();
	}
	private void nextStudy(){
		System.out.println("then study Java EE");
		//xh.study();
	}
}