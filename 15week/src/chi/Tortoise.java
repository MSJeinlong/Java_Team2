package chi;

public class Tortoise extends Thread {
	public void run(){
		for(int i = 0; i < 500; i++){
			System.out.println("ÎÚ¹êÅÜÁË:"+i+"²½");
		}
	}
}