package com.method.thread;

import java.util.HashMap;
import java.util.concurrent.Callable;

/** 
 * by dyong 2010-6-11
 */
public class CommonMethod implements Callable<HashMap<String, Object>>{

	int a,b ;
	
	public CommonMethod(int a,int b){
		this.a = a ;
		this.b = b ;
	}
	
	public HashMap<String, Object> call() throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String,Object>() ;
		map.put("a", a) ;
		map.put("b", b) ;
		map.put("result", a+b) ;
		return map;
	}

}
