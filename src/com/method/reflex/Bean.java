package com.method.reflex;

/**
 * made by dyong 
 * date 2009-4-24 04:35:49
 **/
public class Bean {

	private String str ;
	public int len ;
	
	Bean(){
		
	}
	Bean(int len,String str){
		this.len = len ;
		this.str = str ;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	
	public String add(String a,int b,boolean c){
		return a+b+c ;
	}
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
}
