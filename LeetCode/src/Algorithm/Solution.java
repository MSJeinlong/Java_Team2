package Algorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 3, 5, 2, 1, 6, 5, 8, 1};		//��������
        System.out.println(new Solution().removeDuplicates(nums));	//��ӡ���
    }

    public List<Integer> removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        Set<Integer> hashSet =new HashSet();       //����HashSet�����Դ���ظ�Ԫ�ص��ص�
        for(int n:nums){
            hashSet.add(n);
        }

        //HashSet������������ת����List����
        List<Integer> res = new ArrayList<Integer>(hashSet);
        //����
        Collections.sort(res);
        return res;
    }
}