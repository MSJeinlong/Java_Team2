package chi;
/**
 * ��̬����ģʽ�����
 * 1��Ҫ��һ����ʵ�Ľ�ɫ
 * 2������Ľ�ɫ:һ��Ҫ�ж���ʵ��ɫ������
 * 3�����϶���Ҫʵ��ͬһ���ӿ�
 *
 */
public class StaticProxy {

	public static void main(String[] args){
		//1������Ҫ��һ����ʵ�Ķ���
		//2��Ҫ��һ�������ɫ�Ķ���ע�����ʵ���󴫵ݵ���������У�
		//3��ͨ�����������������ʵ����
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

	private Study xh;	//�����������ַ�ʽ��������ʵ��ɫ
	
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