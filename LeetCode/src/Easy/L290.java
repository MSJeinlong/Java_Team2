package Easy;

import java.util.HashMap;

public class L290 {

	    public  static boolean wordPattern(String pattern, String str) {
	    	if((pattern == null && str != null) || (pattern != null && str == null))
	    	{
	    		return false;
	    	}
	        String str1[] = str.split(" ");
	        if(str1.length != pattern.length())
	        	return false;
	        HashMap<Character, String> map = new HashMap<>();
	        for(int i = 0; i < pattern.length(); i++)
	        {
	        	char ch = pattern.charAt(i);
	        	if(map.get(ch) == null)
	        	{
	        		 if(map.containsValue(str1[i]))
	        		 {
	        			 return false;
	        		 }
	        		map.put(ch, str1[i]);
	        	}
	        	else
	        	{
	        		if(!map.get(ch).equals(str1[i]))
	        			return false;
	        	}
	        }
	        return true;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
