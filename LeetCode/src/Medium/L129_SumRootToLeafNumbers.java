package Medium;

import java.util.*;

public class L129_SumRootToLeafNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] levelorder = { 1, 0 };
		int[] inorder = { 0, 1 };
		TreeNode root = TreeTools.buildTree(inorder, levelorder);
		/*
		 * TreeTools.levelOrder(root); TreeTools.Inorder(root);
		 */
		System.out.println(sumNumbers(root));
	}

	private static int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return getSum(root, 0, "");
	}

	private static int getSum(TreeNode root, int sum, String s) {
		s += root.val;
		if (root.left == null && root.right == null) {
			sum += Integer.parseInt(s);
			return sum;
		}
		if (root.left != null) {
			sum = getSum(root.left, sum, s);
		}
		if (root.right != null) {
			sum = getSum(root.right, sum, s);
		}
		return sum;
	}
}
