package com.method.index.analyzer.thesaurus;

import java.util.Map;

public class AssociateStream
{

	private TreeNode currNode;
	private int step = 0;
	private TreeNode root = null;
	
	private boolean occurWord = false;
	/**
	 * 
	 */
	public AssociateStream(WordTree tree)
	{
		currNode = tree.getRoot();
		root = tree.getRoot();
	}
	
	public boolean isBegin()
	{
		return this.currNode.equals(root);
	}
	
	public boolean associate(char nextChar) {
		Map<Integer,TreeNode> child = currNode.getChildren();//createChildrenMap();
		step++;
		if(child.containsKey(new Integer(nextChar))) {
			this.currNode = child.get(new Integer(nextChar));
			if(currNode.isWordEnd()){
				this.occurWord = true;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOccurWord() {
		return this.occurWord;
	}
	
	public boolean canContinue()
	{
		return this.currNode.hasChildren();
	}
	
	
	public boolean isWordEnd()
	{
		return this.currNode.isWordEnd();
	}
	
	public void backToLastWordEnd() {
		TreeNode tempNode = currNode;
		while(tempNode!=null) {
			step --;
			if(tempNode.isWordEnd()) {
				currNode = tempNode;
				return;
			}
			tempNode = tempNode.getParent();
		}
	}
	
	public int getSetp()
	{
		return this.step;
	}
	
	public String getWord()
	{
		return this.currNode.getWord();
	}
	
	public void reset()
	{
		this.step = 0;
		this.currNode = this.root;
	}
}
