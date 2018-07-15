package chi;
//1.创建线程的第一种方法：继承Thread类
public class Rabbit extends Thread {
	public void run(){
		for(int i = 0; i < 500; i++){
			System.out.println("兔子跑了:"+i+"步");
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
