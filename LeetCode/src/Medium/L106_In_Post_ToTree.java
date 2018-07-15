package Medium;

import java.util.Arrays;

public class L106_In_Post_ToTree {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] inorder = { 9, 3, 15, 20, 7 };
		int[] postorder = {9, 15, 7, 20, 3 };
		TreeNode root = buildTree(inorder, postorder);
		Inorder(root);
		System.out.println();
		Postorder(root);
	}

	private static TreeNode buildTree(int[] inorder, int[] postorder) {
		TreeNode root = null;
		root = CreateTree(inorder, postorder);
		return root;
	}

	private static TreeNode CreateTree(int[] inorder, int[] postorder) {
		TreeNode root = null;
		if (postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0) {
			return root;
		}
		int rootVal = postorder[postorder.length - 1];
		//System.out.println("root == "+rootVal);
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
		int[] leftIn = null;
		int[] rightIn = null;
		int[] leftPost = null;
		int[] rightPost = null;
		
		//�ҳ����������������
		leftIn = Arrays.copyOfRange(inorder, 0, rootIndex);
		//�ҳ����������������
		rightIn = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
		//�ҳ��������ĺ������
		leftPost = Arrays.copyOfRange(postorder, 0, leftIn.length);
		//�ҳ��������ĺ������
		rightPost = Arrays.copyOfRange(postorder, leftIn.length, postorder.length - 1);
	
		//�������������õݹ�
		root.left = CreateTree(leftIn, leftPost);
		root.right = CreateTree(rightIn, rightPost);
		return root;
	}

	// �������
	private static void Inorder(TreeNode root) {
		if (root == null) {
			return;
		}		
		Inorder(root.left);
		System.out.print(root.val + " ");
		Inorder(root.right);
	}

	// �������
	private static void Postorder(TreeNode root) {
		if (root == null) {
			return;
		}
		Postorder(root.left);		
		Postorder(root.right);
		System.out.print(root.val + " ");
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

