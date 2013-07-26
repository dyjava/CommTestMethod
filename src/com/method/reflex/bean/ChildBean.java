package com.method.reflex.bean;

public class ChildBean extends Father {

	private String add ;
	private ChildBean child ;
	private boolean hasSon ;
	
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public ChildBean getChild() {
		return child;
	}
	public void setChild(ChildBean child) {
		this.child = child;
	}
	public boolean isHasSon() {
		return hasSon;
	}
	public void setHasSon(boolean hasSon) {
		this.hasSon = hasSon;
	}
	
}
