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
			System.out.println("错误，名次"+i+" <= 0");
			return null;
		}
		
		if(i > grades.size()) {
			System.out.println("错误，名次"+i+" > 总人数"+grades.size());
			return null;
		}
		//将HashMap，按照value值排序
		//这里就是根据成绩grade来升序，即可得到排名
		ArrayList<Entry<String, Integer>> rank = new ArrayList<Entry<String, Integer>>(grades.entrySet());  
		Collections.sort(rank, new ValueComparator());
		//System.out.println(rank);
		Map<Integer, String> map1 = new HashMap();	//构建排名-姓名的映射
		int j = 1;
		for(Entry<String, Integer> entry:rank) {
			map1.put(j++, entry.getKey());
		}
		//System.out.println(map1);
		return map1.get(i);		//根据排名返回姓名
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap();
		map.put("张三", 82);
		map.put("李四", 79);
		map.put("王五", 93);
		map.put("孙莉", 86);
		map.put("大锤", 66);
		map.put("小美", 92);
		System.out.println(new Solution1().getNameByRank(map, 1));
	}
	
}

class ValueComparator implements Comparator<Map.Entry<String, Integer>>  //实现对Map的升序 
{    
    public int compare(Map.Entry<String, Integer> mp1, Map.Entry<String, Integer> mp2)     
    {    
        return mp2.getValue() - mp1.getValue();    
    }    
}    
