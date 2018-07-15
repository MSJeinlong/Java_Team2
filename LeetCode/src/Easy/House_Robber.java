package Easy;
import java.math.*;
public class House_Robber {

	public static int maxRob(int[] nums)
	{
		int PreRob = 0;	//前一次Rob总和
		int CurRob = 0;//当前Rob总和
		for(int i = 0; i < nums.length; i++)
		{
			int temp = CurRob;
			CurRob = Math.max(CurRob, PreRob + nums[i]);
			PreRob = temp;
			//System.out.println("i = "+i+", b = "+b+", a = "+ a);
		}
		return CurRob;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[] {3,8,4,101,9,5,100,100};
		System.out.println(maxRob(a));
	}

}
