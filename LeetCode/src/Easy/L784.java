package Easy;

import java.util.ArrayList;
import java.util.List;

public class L784 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> res = letterCasePermutation("3z4cD");
		for (String str : res) {
			System.out.println(str);
		}
	}

	public static List<String> letterCasePermutation(String S) {
		List<String> res = new ArrayList<String>();
		if (S == null && S.equals("")) {
			return res;
		}
		DFS(res, S, 0, new StringBuffer());
		return res;

	}

	public static void DFS(List<String> res, String S, int i, StringBuffer str) {
		if (str.length() == S.length()) {
			res.add(str.toString());
			return;
		}
		char ch = S.charAt(i);
		str.append(ch);
		DFS(res, S, i + 1, str);
		str.deleteCharAt(i);
		if (ch >= '9') {
			if (ch >= 'A' && ch <= 'Z') {
				ch += 32;
			} else if (ch >= 'a' && ch <= 'z') {
				ch -= 32;
			}
			str.append(ch);
			DFS(res, S, i + 1, str);
			str.deleteCharAt(i);
		}
	}
}
