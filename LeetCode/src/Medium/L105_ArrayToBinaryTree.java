package Medium;

import java.util.*;

public class L105_ArrayToBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] preorder = {3, 9, 20, 15, 7};
		int[] inorder = {9, 3,15,20,7};
		TreeNode root = buildTree(preorder, inorder);
		Preorder(root);
		System.out.println();
		Inorder(root);
	}

    private static TreeNode buildTree(int[] preorder, int[] inorder) {
		TreeNode root = null;
		List<Integer> Pre = new ArrayList<>();
		List<Integer> In = new ArrayList<>();
		for(int i:preorder) {
			Pre.add(i);
		}
		for(int i:inorder) {
			In.add(i);
		}
		root = CreateTree(Pre, In, root);
		return root;
    }
    
    private static TreeNode CreateTree(List<Integer> preorder, List<Integer> inorder, TreeNode root){
    	if(preorder.isEmpty() || inorder.isEmpty()) {
    		return root;
    	}
    	//preoder��һ����root��ֵ
    	int rootVal = preorder.get(0); 
    	root = new TreeNode(rootVal);
    	//����preorder��inorder�ֱ��ҳ�����������������
    	List<Integer> leftPre = new ArrayList<>();	//��������preorder
    	List<Integer> leftIn = new ArrayList<>();	//��������inorder
    	List<Integer> rightPre = new ArrayList<>();	//��������preorder
    	List<Integer> rightIn = new ArrayList<>();	//��������inorder
    	
    	boolean left = true;
    	for(Integer i:inorder) {
    		if(i == rootVal) {
    			left = false;
    			continue;
    		} 
    		if(left) {	//���ҳ���������inorder
    			leftIn.add(i);
    		} else {	//���ҳ���������inorder
    			rightIn.add(i);
    		}
    	}
    	//��������������inorder��root��preorder���ҳ�����������preorder
    	for(Integer i:preorder) {
    		if(leftIn.contains(i)) {
    			leftPre.add(i);
    		} else if(rightIn.contains(i)) {
    			rightPre.add(i);
    		}
    	}
    	//��root�����������õݹ�
    	root.left = CreateTree(leftPre, leftIn, root.left);
    	root.right = CreateTree(rightPre, rightIn, root.right);
    	return root;
    }
    
    //�������
    private static void Preorder(TreeNode root) {
    	if(root == null) {
    		return;
    	}
    	System.out.print(root.val+" ");
    	Preorder(root.left);
    	Preorder(root.right);
    }
    
    //�������
    private static void Inorder(TreeNode root) {
    	if(root == null) {
    		return;
    	}
    	Inorder(root.left);
    	System.out.print(root.val+" ");
    	Inorder(root.right);
    }
}
