package com.method.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * made by dyong 
 * date 2009-4-8 ионГ11:17:56
 **/
public class Log {

	private static Logger logger = Logger.getLogger(Log.class);
	public static SimpleDateFormat LOG_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void log(String msg){
		logger.info(LOG_DATE_FORMATTER.format(new Date())+"=="+msg) ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Log.log("error") ;
	}

}
