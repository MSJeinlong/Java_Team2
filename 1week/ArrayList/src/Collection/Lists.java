package Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class Lists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("chi");
		arrayList.add("jun");
		arrayList.add("long");
		arrayList.add("Chen");

		//arrayList.add("long");
		//arrayList.add("Li");
		//System.out.println(arrayList.size());
		//System.out.println(arrayList);
		//arrayList.set(0, "Chen");
		//arrayList.add(1.5);
		String st = arrayList.get(0);
		System.out.println(st);
		System.out.println(arrayList);
		System.out.println(arrayList.contains("long"));
/*		for(int i = 0; i < arrayList.size();i++)	//第一种遍历方法
		{
			System.out.println(arrayList.get(i));
		}*/
		//第二种遍历方法
/*		for(String string:arrayList){
			System.out.println(string);
		}*/
		//第三种：iterator的遍历方法（重点！！！）
		Iterator it = arrayList.iterator();
		int i = 0;
		while(it.hasNext()){
			if(it.next().equals("jun")){			
				it.remove();	
			}
			System.out.println(it.next());
			//System.out.println("i = ");
		}
	}

}
