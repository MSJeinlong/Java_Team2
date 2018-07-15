package sort1;

public class MSort {

	public static void MSort(int[] A, int Tmp[], int left, int right) {
		int Center;
		if(left < right) {
			Center = (left + right) / 2;
			MSort(A, Tmp, left, Center);
			MSort(A, Tmp, Center + 1, right);
			Merge(A, Tmp, left, Center + 1, right);
		}
	}
	
	public static void MergeSort(int []A) {
		int[] Tmp = new int[A.length];
		MSort(A, Tmp, 0, A.length - 1);
	}
	
	public static void Merge(int [] A, int[] Tmp, int Lpos, int Rpos, int RightEnd) {
		int LeftEnd = Rpos - 1;
		int TmpPos = Lpos;
		int Nums = RightEnd - Lpos + 1;
		while(Lpos <= LeftEnd && Rpos <= RightEnd) {
			if(A[Lpos] <= A[Rpos])
				Tmp[TmpPos++] = A[Lpos++];
			else
				Tmp[TmpPos++] = A[Rpos++];
		}
		while(Lpos <= LeftEnd)
			Tmp[TmpPos++] = A[Lpos++];
		while(Rpos <= RightEnd)
			Tmp[TmpPos++] = A[Rpos++];
		for(int i = 0; i < Nums; i++, RightEnd--)
			A[RightEnd] = Tmp[RightEnd];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1, 13, 26, 15, 2, 27, 38, 9};
		MergeSort(nums);
		for(int i:nums) {
			System.out.print(i+"  ");
		}
	}

}
