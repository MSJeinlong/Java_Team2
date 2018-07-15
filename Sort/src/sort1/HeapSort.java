package sort1;

public class HeapSort {

	public static void creatHeap(int nums[], int n,  int h) {
		int i = h;
		int j = 2 * i + 1;
		int temp = nums[i];
		boolean flag = false;
		while( j < n && !flag) {
			if(j < n - 1 && nums[j] < nums[j + 1])
				j++;
			if(temp > nums[j])
				flag = true;
			else {
				nums[i] = nums[j];
				i = j;
				j = 2 * i + 1;
			}			
		}
		nums[i] = temp;
	}
	
	public static void heapSort(int[] nums) {
		for(int i = (nums.length - 2) /2; i >= 0; i-- )
			creatHeap(nums, nums.length, i);
		for(int i:nums) {
			System.out.print(i+" ");
		}
		System.out.println();
		for(int i = nums.length - 1; i > 0; i--) {
			int t = nums[0];
			nums[0] = nums[i];
			nums[i] = t;
			creatHeap(nums, i, 0);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {31, 41, 59, 26, 53, 58, 97};
		heapSort(nums);
		for(int i:nums) {
			System.out.print(i+" ");
		}
	}

}
