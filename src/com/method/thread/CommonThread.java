package com.method.thread;

/** 
 * by dyong 2010-6-11
 */
public class CommonThread implements Runnable {

	private int p = 1 ;
	
	public CommonThread(){ }
	public CommonThread(int p){
		this.p = p ;
	}
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<p;i++){
			System.out.println("test"+i) ;
		}
	}

}
