package chi;

/**
 *	Thread����java.lang�У�ʵ����Runnable�ӿ�
 *
 */
public class Thread1 {

	public static void main(String[] args){
		//��ȡ��ǰ�̵߳�����
		//String name = Thread.currentThread().getName();
		//System.out.print(name);
/*		Rabbit ra = new Rabbit();
		ra.start();
		Tortoise tor = new Tortoise();
		tor.start();*/
/*		for (int i = 0; i < 50; i++){
			String name = Thread.currentThread().getName();
			System.out.println(name);
		}*/
		ImRuThread it = new ImRuThread();
		Thread t = new Thread(it, "it");
		t.start();
		System.out.println(t.getName()+":"+t.getId());
	}
}
