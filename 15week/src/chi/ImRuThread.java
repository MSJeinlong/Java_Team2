package chi;

public class ImRuThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 50; i++){
			System.out.println("i: "+i+" I am "+Thread.currentThread().getName()+" and implement Runnable");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
