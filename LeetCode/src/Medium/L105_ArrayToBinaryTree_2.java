package Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L105_ArrayToBinaryTree_2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] preorder = { 3, 9, 20, 15, 7 };
		int[] inorder = { 9, 3, 15, 20, 7 };
		TreeNode root = buildTree(preorder, inorder);
		Preorder(root);
		System.out.println();
		Inorder(root);
	}

	private static TreeNode buildTree(int[] preorder, int[] inorder) {
		TreeNode root = null;
		root = CreateTree(preorder, inorder);
		return root;
	}

	private static TreeNode CreateTree(int[] preorder, int[] inorder) {
		TreeNode root = null;
		if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
			return root;
		}
		int rootVal = preorder[0];
		root = new TreeNode(rootVal);
		// if(preorder.length == 1)
		// return root;
		int rootIndex = 0;
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == rootVal) {
				rootIndex = i;
				break;
			}
		}
		int[] leftPre = null;
		int[] rightPre = null;
		int[] leftIn = null;
		int[] rightIn = null;
		
		leftIn = Arrays.copyOfRange(inorder, 0, rootIndex);

		rightIn = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);

		leftPre = Arrays.copyOfRange(preorder, 1, leftIn.length + 1);

		rightPre = Arrays.copyOfRange(preorder, leftIn.length + 1, preorder.length);

		root.left = CreateTree(leftPre, leftIn);
		root.right = CreateTree(rightPre, rightIn);
		return root;
	}

	// 先序遍历
	private static void Preorder(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " ");
		Preorder(root.left);
		Preorder(root.right);
	}

	// 中序遍历
	private static void Inorder(TreeNode root) {
		if (root == null) {
			return;
		}
		Inorder(root.left);
		System.out.print(root.val + " ");
		Inorder(root.right);
	}

	private static void printArray(int[] arr) {
		if (arr == null)
			return;
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
