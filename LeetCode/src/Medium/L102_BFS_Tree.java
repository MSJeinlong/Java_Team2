package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L102_BFS_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null)
			return null;
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		TreeNode currNode = null;
		// BFS
		while (!q.isEmpty()) {
			int toPop = q.size();
			List<Integer> list = new ArrayList<>();
			while(toPop > 0) {
				 currNode = q.poll();
				 list.add(currNode.val);
				 if(currNode.left != null) {
					 q.add(currNode.left);
				 }
				 if(currNode.right != null) {
					 q.add(currNode.right);
				 }
				 toPop--;
			}
			res.add(list);
		}
		return res;
	}
}

