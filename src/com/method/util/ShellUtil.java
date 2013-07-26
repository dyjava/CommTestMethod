package com.method.util;

import java.io.IOException;

/**
 * made by dyong 
 * date 2010-1-7 03:33:27
 **/
public class ShellUtil {


	public static int exec(String cmd) throws IOException, InterruptedException{
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(cmd);
		return process.waitFor();
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		String shell = "rm -f /cygdrive/e/index/novel/sss.txt" ;
		exec(shell) ;
	}

}
