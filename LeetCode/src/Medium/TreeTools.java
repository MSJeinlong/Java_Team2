package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeTools {
	public static TreeNode buildTree(int[] inorder, int[] levelorder) {
		List<Integer> level = new ArrayList<>();
		List<Integer> In = new ArrayList<>();
		for (int i : levelorder) {
			level.add(i);
		}
		for (int i : inorder) {
			In.add(i);
		}
		TreeNode root = CreateTree(In, level);
		return root;
	}

	public static TreeNode CreateTree(List<Integer> inorder, List<Integer> levelorder) {
		TreeNode root = null;
		if (inorder.isEmpty() || levelorder.isEmpty()) {
			return root;
		}
		// levelorder第一个数root的值
		int rootVal = levelorder.get(0);
		root = new TreeNode(rootVal);
		// 根据levelorder和inorder分别找出二叉树的左右子树
		List<Integer> leftLevel = new ArrayList<>(); // 左子树的levelorder
		List<Integer> leftIn = new ArrayList<>(); // 左子树的inorder
		List<Integer> rightLevel = new ArrayList<>(); // 右子树的levelorder
		List<Integer> rightIn = new ArrayList<>(); // 右子树的inorder

		boolean left = true;
		for (Integer i : inorder) {
			if (i == rootVal) {
				left = false;
				continue;
			}
			if (left) { // 先找出左子树的inorder
				leftIn.add(i);
			} else { // 先找出右子树的inorder
				rightIn.add(i);
			}
		}
		// 根据左右子树的inorder在root的preorder中找出左右子树的preorder
		for (Integer i : levelorder) {
			if (leftIn.contains(i)) {
				leftLevel.add(i);
			} else if (rightIn.contains(i)) {
				rightLevel.add(i);
			}
		}
		// 对root的左子树调用递归
		root.left = CreateTree(leftIn, leftLevel);
		root.right = CreateTree(rightIn, rightLevel);
		return root;
	}

	// 层次遍历
	public static void levelOrder(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (root == null)
			return;
		q.add(root);
		TreeNode currNode = null;
		while (!q.isEmpty()) {
			currNode = q.poll();
			System.out.print(currNode.val + " ");
			if (currNode.left != null) {
				q.add(currNode.left);
			}
			if (currNode.right != null) {
				q.add(currNode.right);
			}
		}
		System.out.println();
	}

	// 中序遍历
	public static void Inorder(TreeNode root) {
		if (root == null) {
			return;
		}
		Inorder(root.left);
		System.out.print(root.val + " ");
		Inorder(root.right);
	}

}
