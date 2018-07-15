package week2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//������һ���ַ������飬ͳ��ÿ���ַ������ֵĴ���
public class HashMap3 {

	public static void main(String[] args) {
		String [] strings={"hh", "tt","ee","hh","ff","hh","tt",";", "long", "ee"};
		MyTool.printHP(MyTool.count(strings));
	}

}

/**
 * ���ַ�������hashmap �� Key ����HashMap��������ʼvalue = 1��
 * ��key�ظ�ʱ����value + 1�����ɼ���ÿ���ַ����ĳ��ִ���
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
				hp.put(stringKey, hp.get(stringKey) + 1);//ÿ��key�ظ�ʱ����ԭ����Ԫ��, value +��
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
