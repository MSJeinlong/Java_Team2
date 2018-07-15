package week2;
import java.util.Stack;
public class Stack1 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Ticket> stack = new Stack<Ticket>();
		for(int i = 0; i < 30; i++){
			//Ticket x = new Ticket(i);
			stack.push(new Ticket(i+1));
		}
		for(int i = 0; i < 30;i++){
			System.out.println(stack.pop());
		}
	}
	


	
}

class Ticket{
	private int ID;

	public Ticket(int iD){
		super();
		ID = iD;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String toString() {
		return "ticket:"+ID+" ";
	}
}
