package week2;
import java.util.TreeMap;
//TreeMap是有序的，按自然升序
public class TreeMap1 {

	public static void main(String[] args) {
	TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
	treeMap.put("chi", 1);
	treeMap.put("jun", 2);
	treeMap.put("long", 3);
	treeMap.put("get", 3);
	System.out.println(treeMap);
	}

}
