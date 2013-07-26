package com.method.hostLadle;

import org.apache.log4j.Logger;

import com.method.hostLadle.host.RequestDispatcher;
import com.method.hostLadle.host.StartThread;

/**
 * made by dyong 
 * date 2008-9-24 10:16:07
 **/
public class Const {
	private static final Logger log = Logger.getLogger(Const.class);
	
	private static int SELLEP_TIME = 10;
	private static String HOST = "localhost:80:20,172.16.21.156:8180:20,192.168.0.12:80:20,192.168.0.15:80:20,192.168.0.110:80:20";
	
	public static int getSELLEP_TIME() {
		return SELLEP_TIME;
	}

	public static String getHOST() {
		return HOST ;
	}
	
	/**
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
//		StartThread.startCheckThread();
		while(true){
			log.info(RequestDispatcher.getLink());
			Thread.sleep(1000);
		}
	}
}
