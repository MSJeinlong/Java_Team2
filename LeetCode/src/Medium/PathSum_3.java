package Medium;

import java.util.LinkedList;
import java.util.Queue;

public class PathSum_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static int count = 0;

	public static int pathSum(TreeNode root, int sum) {
		if (root == null)
			return 0;

		count = 0;

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		TreeNode currNode;
		// BFS
		while (!q.isEmpty()) {
			currNode = q.poll();
			DFS(currNode, sum);
			if (currNode.left != null) {
				q.add(currNode.left);
			}

			if (currNode.right != null) {
				q.add(currNode.right);
			}
		}
		return count;
	}

	private static void DFS(TreeNode root, int sum) {
		if (root == null)
			return;
		if (root.val == sum)
			count++;
		if (root.left != null) {
			DFS(root.left, sum - root.val);
		}

		if (root.right != null) {
			DFS(root.right, sum - root.val);
		}
	}
}

