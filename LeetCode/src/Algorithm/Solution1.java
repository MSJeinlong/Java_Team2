package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Solution1 {


	public String getNameByRank(Map<String, Integer> grades, int i) {
		
		if(i <= 0) {
			System.out.println("��������"+i+" <= 0");
			return null;
		}
		
		if(i > grades.size()) {
			System.out.println("��������"+i+" > ������"+grades.size());
			return null;
		}
		//��HashMap������valueֵ����
		//������Ǹ��ݳɼ�grade�����򣬼��ɵõ�����
		ArrayList<Entry<String, Integer>> rank = new ArrayList<Entry<String, Integer>>(grades.entrySet());  
		Collections.sort(rank, new ValueComparator());
		//System.out.println(rank);
		Map<Integer, String> map1 = new HashMap();	//��������-������ӳ��
		int j = 1;
		for(Entry<String, Integer> entry:rank) {
			map1.put(j++, entry.getKey());
		}
		//System.out.println(map1);
		return map1.get(i);		//����������������
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap();
		map.put("����", 82);
		map.put("����", 79);
		map.put("����", 93);
		map.put("����", 86);
		map.put("��", 66);
		map.put("С��", 92);
		System.out.println(new Solution1().getNameByRank(map, 1));
	}
	
}

class ValueComparator implements Comparator<Map.Entry<String, Integer>>  //ʵ�ֶ�Map������ 
{    
    public int compare(Map.Entry<String, Integer> mp1, Map.Entry<String, Integer> mp2)     
    {    
        return mp2.getValue() - mp1.getValue();    
    }    
}    
