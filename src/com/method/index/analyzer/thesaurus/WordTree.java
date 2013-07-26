package com.method.index.analyzer.thesaurus;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WordTree implements Serializable {
	private static final long serialVersionUID = -1367429574096837611L;

	private int wordCount = 0;

	private TreeNode root = null;

	private HashMap<Integer, Character> chineseCharMap = new HashMap<Integer, Character>();

	private WordTree() {
		root = new TreeNode();
		root.setChineseChar(' ');
	}

	protected static WordTree getInstance() {
		return new WordTree();
	}

	public TreeNode getRoot() {
		return this.root;
	}

	public void addChineseChar(Character chineseChar) {
		this.chineseCharMap.put(chineseChar.hashCode(), chineseChar);
	}

	public boolean containsChineseChar(int key) {
		return this.chineseCharMap.containsKey(key);
	}

	public char removeChineseChar(int key) {
		return this.chineseCharMap.remove(key);
	}

	public void addChineseWord(String word) {
		wordCount++;
		TreeNode tempNode = this.root;
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			TreeNode node = new TreeNode();
			node.setChineseChar(charArray[i]);
			if (!tempNode.containsChild(node)) {
				tempNode.addChild(node);
				tempNode = node;
			} else {
				tempNode = tempNode.getByChar(node.getChineseChar());
			}
		}
		tempNode.setWordEnd(true);
	}

	public int getWordCount() {
		return this.wordCount;
	}

	public boolean containsWord(String word) {
		TreeNode tempNode = this.root;
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			TreeNode node = new TreeNode();
			node.setChineseChar(charArray[i]);
			if (!tempNode.containsChild(node))
				return false;
			else
				tempNode = tempNode.getByChar(node.getChineseChar());
		}
		if (tempNode.isWordEnd())
			return true;
		else
			return false;

	}

	public void printTree(Writer out) throws IOException {
		String indent = "";
		out.write(this.root.toString());
		out.write(System.getProperty("line.separator"));
		printChildren(out, this.root, indent);
	}

	private void printChildren(Writer out, TreeNode parent, String indent)
			throws IOException {
		Map<Integer, TreeNode> children = parent.getChildren();
		Set<Integer> keySet = children.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		indent += "      ";
		while (iterator.hasNext()) {
			TreeNode child = children.get(iterator.next());
			out.write(indent);
			out.write(child.toString());
			if (child.isWordEnd())
				out.write('^');
			out.write(System.getProperty("line.separator"));
			if (child.hasChildren())
				printChildren(out, child, indent);
		}
	}

	public int countChar() {
		int count = this.root.count();
		count += countChildren(root);
		return count;
	}

	private int countChildren(TreeNode parent) {
		int count = 0;
		Map<Integer, TreeNode> children = parent.getChildren();
		Set<Integer> keySet = children.keySet();
		Iterator<Integer> iterator = keySet.iterator();

		while (iterator.hasNext()) {
			TreeNode child = children.get(iterator.next());// iterator.next();
			count += child.count();
			if (child.hasChildren())
				count += countChildren(child);
		}
		return count;
	}

}
