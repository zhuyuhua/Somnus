/**
 * 系统项目名称
 * com.somnus.elearning.java8
 * Solution.java
 * 2015年6月16日-下午11:45:56
 * 2015-程序员无限公司-版权所有
 * 
 */
package com.somnus.elearning.java8;

/**
 * 
 * Solution Administrator 2015年6月16日-下午11:45:56
 * 
 * @version 1.0.0
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class Solution {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);

		TreeNode oneLeft = new TreeNode(2);
		TreeNode oneRight = new TreeNode(7);

		root.left = oneLeft;
		root.right = oneRight;

		oneLeft.left = new TreeNode(1);
		oneLeft.right = new TreeNode(3);
		oneRight.left = new TreeNode(6);
		oneRight.right = new TreeNode(9);

		System.out.println(root);
		Solution solution = new Solution();

		TreeNode after = solution.invertTree(root);

		System.out.println(after);
	}

	public TreeNode invertTree(TreeNode root) {

		if (root == null)
			return null; // line 1

		if (root.left != null) { // line 2
			TreeNode leftchild = invertTree(root.left); // line 3
			leftchild.right = root; // line 4
		}

		if (root.right != null) { // line 5
			TreeNode rightchild = invertTree(root.right); // line 6
			rightchild.left = root; // line 7
		}

		root.left = null; // line 8
		root.right = null; // line 9

		return root; // line 10

	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "TreeNode [val=" + val + ", left=" + left + ", right=" + right
				+ "]";
	}

}