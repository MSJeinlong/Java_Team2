package Collection;

import java.util.Iterator;
import java.util.LinkedList;

public class Link {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyStack mystack = new MyStack();
		mystack.push("chi");
		mystack.push("jun");
		mystack.push("long");
		System.out.println(mystack.pop());
		
		Iterator it = mystack.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

}

class MyStack{
	private LinkedList<String> myli;
	public MyStack()
	{
		myli = new LinkedList<String>();
	}
	public void push(String s){
		myli.addFirst(s);
	}
	public String pop(){
		return myli.removeFirst();
	}
	public Iterator<String> iterator(){
		return myli.iterator();
	}
}