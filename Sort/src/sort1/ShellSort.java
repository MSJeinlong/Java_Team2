package sort1;

public class ShellSort {

	public static void shellSort(int[] nums) {
		int i, j, Increment;
		for(Increment = nums.length/2; Increment > 0; Increment /= 2) {
			for( i = Increment; i < nums.length; i++) {
				int t = nums[i];
				for(j = i; j >= Increment; j -= Increment) {
					if(t < nums[j - Increment])
						nums[j] = nums[j - Increment];
					else
						break;
				}
				nums[j] = t;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
		shellSort(nums);
		for(int i:nums) {
			System.out.print(i+"  ");
		}
	}

}
