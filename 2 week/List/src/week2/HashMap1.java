package week2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



//HashMap����ͬ���ģ����ܱ�֤Ԫ�صĴ���
public class HashMap1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map <Integer, String> hp= new HashMap<Integer, String>();
		hp.put(1, "chi");
		hp.put(2, "jun");
		hp.put(3, "long");
		hp.put(1, "gt");//���ǵ�ԭ��key = 1��Ԫ��ֵ
//		System.out.println(hp);
//		System.out.println(hp.size());
		
	//�õ����е�key:
		hp.keySet();
		Set<Integer> keySet = hp.keySet();
		for(Integer integer:keySet){
			System.out.println(integer);
		}
		
		//�õ����е�value;
		Collection<String> values = hp.values();
		for(String string:values){
			System.out.println(string);
		}
		//ͬʱ�õ����е�key��value
		hp.keySet();
		//Set<Integer> keySet = hp.keySet();
		for(Integer integer:keySet){
			System.out.println(integer+": "+hp.get(integer));
		}
		
		Set<Entry<Integer, String>> entrySet  = hp.entrySet();
		for(Entry<Integer, String> entry:entrySet){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}

}
