package Medium;

import java.util.LinkedList;

public class L71_Simplify_Path {

	public static String simplifyPath(String path) {
		LinkedList<String> stack = new LinkedList<>();
		stack.push("/");
		String[] Arr = path.split("/");
		String res = "";
		for (String s : Arr) {

			if (s == null || s.equals(""))
				continue;

			if (s.equals("..") && !stack.isEmpty()) {
				if (!stack.peek().equals("/")) {
					stack.pop();
					stack.pop();
				}

			} else if (!s.equals(".")) {
				String s1 = stack.peek();
				// System.out.println(s1);
				if ( !s1.equals("/")) {
					// System.out.println("1");
					stack.push("/");
				}
				stack.push(s);
			}
			
			if(stack.isEmpty()) {
				stack.push("/");
			}
		}
		while (!stack.isEmpty()) {
			res += stack.removeLast();
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(simplifyPath("/a/./b/../../c/"));
	}

}
