package Medium;

public class L91_Decode_Ways {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(numDecodings1("226"));
	}

	private static int numDecodings(String s) {
		int n = s.length();
		if (n == 0)
			return 0;

		int[] nums = new int[n + 1];
		nums[0] = 1;
		nums[1] = s.charAt(1) != '0' ? 1 : 0;
		for (int i = 2; i <= n; i++) {
			System.out.println(i);
			if (s.charAt(i) == '0')
				continue;

			nums[i] = (Integer.parseInt(s.substring(i - 2, i)) <= 26) ? nums[i - 1] + nums[i - 2] : nums[i - 1];
		}
		return nums[n];
	}

	public static int numDecodings1(String s) {
		int n = s.length();
		if (n == 0)
			return 0;

		int[] memo = new int[n + 1];
		memo[n] = 1;
		memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

		for (int i = n - 2; i >= 0; i--) {
			System.out.println(i);
			if (s.charAt(i) == '0') {
				continue;
			} else {
				memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];
			}
		}
		return memo[0];
	}
}
