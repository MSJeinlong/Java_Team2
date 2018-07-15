package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L95_UniqueBinarySearchTrees_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<TreeNode> list = generateTrees(3);
		System.out.println(list.size());
	}

	private static List<TreeNode> generateTrees(int n) {
		
		if(n <= 0) {
			return new ArrayList<TreeNode>();
		}
		return DP(1, n);
	}

	private static List<TreeNode> DP(int start, int end) {
		List<TreeNode> res = new ArrayList<>();
		if(start > end) {
			res.add(null);
			return res;
		}
		for(int i = start; i <= end; i++) {
			List<TreeNode> leftChild = DP(start, i - 1);
			List<TreeNode> rightChild = DP(i + 1, end);
			for(TreeNode left:leftChild) {
				for(TreeNode right:rightChild) {
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					res.add(root);
				}
			}
		}
		return res;
	}

	private static void preOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		preOrder(root.left);
		System.out.print(root.val+" ");
		preOrder(root.right);
	}
}
