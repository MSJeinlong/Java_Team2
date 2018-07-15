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
		// levelorder��һ����root��ֵ
		int rootVal = levelorder.get(0);
		root = new TreeNode(rootVal);
		// ����levelorder��inorder�ֱ��ҳ�����������������
		List<Integer> leftLevel = new ArrayList<>(); // ��������levelorder
		List<Integer> leftIn = new ArrayList<>(); // ��������inorder
		List<Integer> rightLevel = new ArrayList<>(); // ��������levelorder
		List<Integer> rightIn = new ArrayList<>(); // ��������inorder

		boolean left = true;
		for (Integer i : inorder) {
			if (i == rootVal) {
				left = false;
				continue;
			}
			if (left) { // ���ҳ���������inorder
				leftIn.add(i);
			} else { // ���ҳ���������inorder
				rightIn.add(i);
			}
		}
		// ��������������inorder��root��preorder���ҳ�����������preorder
		for (Integer i : levelorder) {
			if (leftIn.contains(i)) {
				leftLevel.add(i);
			} else if (rightIn.contains(i)) {
				rightLevel.add(i);
			}
		}
		// ��root�����������õݹ�
		root.left = CreateTree(leftIn, leftLevel);
		root.right = CreateTree(rightIn, rightLevel);
		return root;
	}

	// ��α���
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

	// �������
	public static void Inorder(TreeNode root) {
		if (root == null) {
			return;
		}
		Inorder(root.left);
		System.out.print(root.val + " ");
		Inorder(root.right);
	}

}
