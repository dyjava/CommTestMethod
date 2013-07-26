package com.method.serializ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/** 
 * list对象序列化
 * by dyong 2010-8-2
 */
public class Serializ2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String p = "e:/tmp/c.ser";
		
		Hashtable<String, CityBean> table = new Hashtable<String, CityBean>() ;
		ArrayList<CityBean> list = new ArrayList<CityBean>() ;
		for(int i=0;i<5;i++){
			CityBean c = new CityBean() ;
			c.setNo(i+1) ;
			c.setName("beijing"+i) ;
			c.setParentId(100) ;
			c.setRank(i) ;
			
			table.put(i+1+"bj",c) ;
			list.add(c) ;
		}
//		SerializWrite.writeObject(p,table) ;
		SerializWrite.writeObject(p,list.toArray(new CityBean[0])) ;
		
		CityBean c = new CityBean() ;
		c.setNo(999) ;
		c.setName("shanghai") ;
		c.setParentId(100) ;
		SerializWrite.writeObjectAdd(p, c) ;
		
//		read
		Object[] objs = SerializRead.readObjects(p) ;
		for(Object o:objs){
			CityBean cc = (CityBean) o;
			System.out.println(cc.getName()+"	"+cc.getRank()) ;
		}
	}

	
}
