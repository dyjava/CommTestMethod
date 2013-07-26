package com.method.serializ;

import java.io.IOException;
import java.io.Serializable;

/** 
 * by dyong 2010-8-2
 */
public class CityBean implements Serializable {

	private static final long serialVersionUID = 1739364076022419215L;
	private int no;
	private String name ;
	private int parentId ;
	private int rank ;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		rank = rank<<2 ;
		stream.defaultWriteObject();
	}
	
	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException{
		stream.defaultReadObject();  
		rank=rank>>1;
	}  
}
