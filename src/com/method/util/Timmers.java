package com.method.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * made by dyong 
 * date 2008-9-5 ÏÂÎç05:03:44
 **/
public class Timmers {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Timer tm = new Timer();
		System.out.println("======="+System.currentTimeMillis());
		TimerTask tt = new task();
		tm.schedule(tt,new Date(System.currentTimeMillis()+2000), 1000);
		
//		
//		while(true){
//			System.out.println("sss");
//			Thread.sleep(2000);
//		}
	}

}
class task extends TimerTask{

	public void run() {
		
		System.out.println("======="+System.currentTimeMillis());
	}
	
}
