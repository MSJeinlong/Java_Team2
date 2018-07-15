package Medium;

import java.util.*;

public class L105_LevelOrderToBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] levelorder = { 4, 9, 0, 5, 1 };
		int[] inorder = { 5, 9, 1, 4, 0 };
		TreeNode root = TreeTools.buildTree(inorder, levelorder);
		TreeTools.levelOrder(root);
		TreeTools.Inorder(root);
	}


}

