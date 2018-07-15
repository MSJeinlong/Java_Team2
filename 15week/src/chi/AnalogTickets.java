package chi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AnalogTickets {
	public static void main(String[] args){
		Sail1 s = new Sail1();
		Thread t1 = new Thread(s);
		Thread t2 = new Thread(s);
		Thread t3 = new Thread(s);
		t1.start();
		t2.start();
		t3.start();
	}
}

//ʵ��Runnable�ķ�ʽ��ģ����Ʊ�ĳ���
class Sail1 implements Runnable{
	private int tickets = 100;
	public  void run(){
		
		for(int i = 0; i < 100; i++){
			if(tickets <= 0){
				break;
			}
			System.out.println(Thread.currentThread().getName()+":������ "+(tickets--)+" ��Ʊ");
			
		}
	}
}
