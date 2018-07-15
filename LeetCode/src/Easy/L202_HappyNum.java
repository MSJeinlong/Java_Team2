package Easy;
import java.math.*;
import java.util.HashSet;
public class L202_HappyNum {

    public static boolean isHappy(int n) {
    	if(n == 1)
    		return true;
        boolean ishp = false;
        int  curr = n;
        HashSet<Integer> hs = new HashSet<>();
        while(true)
        {
        	int next = 0;
        	while(curr != 0)
        	{
        		next += (int)Math.pow((curr % 10), 2);
        		curr /= 10;
        	}
        	if(next == 1)
        	{
        		return true;
        	}
        	if(hs.contains(next))
        	{
        		return false;
        	}
        	hs.add(next);
        	curr = next;
        }

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isHappy(2));
	}

}
