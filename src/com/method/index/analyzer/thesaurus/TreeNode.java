package com.method.index.analyzer.thesaurus;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class TreeNode implements Serializable {
	private static final long serialVersionUID = -7717005260297485725L;

	// 保存一个字
	private char chineseChar;

	// 该节点是否为一个词的结尾
	private boolean isWordEnd = false;

//	在词的结尾标明词的类型，动词、名称、形容词、副词
	private int type = 0 ;
	// 该节点的父节点
	private TreeNode parent = null;

	private Map<Integer, TreeNode> children = new HashMap<Integer, TreeNode>();

	/**
	 * 
	 */
	public TreeNode() {	}

	/**
	 * @param chineseCharCode
	 * @param isWordEnd
	 * @param parent
	 */
	public TreeNode(char chineseChar, boolean isWordEnd, TreeNode parent) {
		this.chineseChar = chineseChar;
		this.isWordEnd = isWordEnd;
		this.parent = parent;
	}

	/**
	 * @return the chineseChar
	 */
	public char getChineseChar() {
		return chineseChar;
	}

	/**
	 * @param chineseChar
	 *            the chineseCharCode to set
	 */
	public void setChineseChar(char chineseCharCode) {
		this.chineseChar = chineseCharCode;
	}

	/**
	 * @return the isWordEnd
	 */
	public boolean isWordEnd() {
		return isWordEnd;
	}

	/**
	 * @param isWordEnd
	 *            the isWordEnd to set
	 */
	public void setWordEnd(boolean isWordEnd) {
		this.isWordEnd = isWordEnd;
	}

	/**
	 * @return the parent
	 */
	public TreeNode getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public void addChild(TreeNode child) {
		child.parent = this;
		this.children.put(child.hashCode(), child);
	}

	public boolean hasChildren() {
		return this.children.size() == 0 ? false : true;
	}

	public TreeNode getByChar(char chineseChar) {

		return this.children.get(new Integer(chineseChar));
	}

	public boolean containsChild(TreeNode child) {
		return this.children.containsKey(child.hashCode());
	}

	public TreeNode removeChild(TreeNode child) {
		return this.children.remove(child);
	}

	public void removeChildren() {
		this.children.clear();
	}

	public String getWord() {
		String word = "";
		TreeNode tempNode = this;
		if (this.isWordEnd) {
			while (tempNode.parent != null) {
				word = tempNode.toString() + word;
				tempNode = tempNode.parent;
			}
			word = word +String.valueOf(type) ;
		} else {
			word = null;
		}
		return word;
	}

	public int count() {
		return this.children.size();
	}

	public Map<Integer, TreeNode> getChildren() {
		return children;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (obj instanceof TreeNode) {
			TreeNode another = (TreeNode) obj;
			// 如果一个节点包含的字与本节点相等,则认为这两个节点是同一个节点
			if (another.chineseChar == this.chineseChar)
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.chineseChar;
	}

	@Override
	public String toString() {
		return Character.toString(this.chineseChar);
	}

	public void setType(int type) {
		this.type = type;
	}
}
