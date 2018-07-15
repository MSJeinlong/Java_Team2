package Medium;

import java.util.ArrayList;
import java.util.List;

public class L60 {

	// public static String getPermutation(int n, int k) {
	// StringBuilder ans = new StringBuilder("");
	// boolean[] isUse = new boolean[n];
	// boolean[] stop = new boolean[1];
	// int[] c = new int[1];
	// DFS(ans, n, k, c, stop, isUse);
	// return ans.toString();
	// }
	//
	//
	// public static void DFS(StringBuilder ans, int n, int k, int[] c, boolean[]
	// stop, boolean[] isUse)
	// {
	// if(ans.length() == n)
	// {
	// c[0]++;
	// if(c[0] == k) {
	// stop[0] = true;
	// return;
	// }
	// }
	//
	// for(int i = 1; i <= n;i++)
	// {
	// if(isUse[i - 1])
	// continue;
	// ans.append(i);
	// isUse[i - 1] = true;
	// DFS(ans, n, k, c, stop, isUse);
	//
	// if(stop[0])
	// break;
	// if(ans.length() != 0)
	// ans.deleteCharAt(ans.length() - 1);
	// isUse[i - 1] = false;
	// }
	// }

	public  static String getPermutation(int n, int k) {
		String res = "";
		int fact = 1;
		List<Integer> nums = new ArrayList<>();
		for(int i = 1; i <= n;i++) {
			fact *= i;
			nums.add(i);
		}
		for(int i = 0, l = k - 1; i < n; i++)
		{
			fact /= (n - i);
			int index = (l / fact);
			res += nums.remove(index);
			l -= index * fact;
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getPermutation(3, 5));
	}

}
