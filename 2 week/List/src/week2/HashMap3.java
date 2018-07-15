package week2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//假设有一个字符串数组，统计每个字符串出现的次数
public class HashMap3 {

	public static void main(String[] args) {
		String [] strings={"hh", "tt","ee","hh","ff","hh","tt",";", "long", "ee"};
		MyTool.printHP(MyTool.count(strings));
	}

}

/**
 * 将字符串当做hashmap 的 Key 放入HashMap容器，初始value = 1，
 * 当key重复时，让value + 1，即可计算每个字符串的出现次数
 * 
 */
class MyTool{
	public static Map<String, Integer> count(String[] s){
		HashMap<String, Integer> hp = new HashMap<String, Integer>();
		for(int i = 0; i < s.length; i++){
			String stringKey = s[i];
			if(hp.get(stringKey) == null){
				hp.put(stringKey, 1);
			} else {
				hp.put(stringKey, hp.get(stringKey) + 1);//每次key重复时覆盖原来的元素, value +１
			}
		}
		return hp;
	}
	
	public static void printHP(Map<String, Integer> map){
		Set<Entry<String, Integer>> enSet = map.entrySet();
		for(Entry<String, Integer> entry:enSet){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
	}
}
