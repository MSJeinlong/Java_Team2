package Easy;

import java.util.HashMap;

public class L205 {

    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
    	if(s.length() != t.length())
        	return false;
        if( s.equals(t))
        	return true;
        for(int i = 0; i < s.length(); i++) {
        	char key = s.charAt(i);
        	char v = t.charAt(i);
        	if(!map.containsKey(key)) {//判断是否key是否存在于map
        		if(map.containsValue(v))//判断value是否存在
        			return false;
        		map.put(key, v);
        	}
        	else if(!map.get(key).equals(v))
        		return false;
        }
        	
        return true;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isIsomorphic("ab", "aa"));
	}

}
