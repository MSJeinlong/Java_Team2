package chi;
//1.�����̵߳ĵ�һ�ַ������̳�Thread��
public class Rabbit extends Thread {
	public void run(){
		for(int i = 0; i < 500; i++){
			System.out.println("��������:"+i+"��");
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
