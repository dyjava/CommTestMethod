package com.method.serializ;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/** 
 * 对象序列化成字符流
 * by dyong 2010-8-12
 */
public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		CityBean c = new CityBean() ;
		c.setName("北京") ;
		c.setNo(1) ;
		byte[] bs = objectToByte(c) ;
		
		CityBean c2 = (CityBean) byteToObject(bs) ;
		System.out.println(c2.getName()+c.getNo()) ;
		
//		List
		ArrayList<CityBean> list = new ArrayList<CityBean>() ;
		for(int i=0;i<10;i++){
			CityBean cb = new CityBean() ;
			cb.setNo(i+1) ;
			cb.setName("上海"+i) ;
			cb.setParentId(100) ;
			list.add(cb) ;
		}
		bs = objectToByte(list) ;
		
		ArrayList<CityBean> list2 = (ArrayList<CityBean>) byteToObject(bs) ;
		for(CityBean cc:list2){
			System.out.println(cc.getName()) ;
		}
		
	}
	
	private static byte[] objectToByte(Object obj) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream() ;
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(obj);
		byte[] bs = os.toByteArray() ;
		return bs ;
	}
	
	private static Object byteToObject(byte[] b) throws IOException, ClassNotFoundException{
		ByteArrayInputStream is = new ByteArrayInputStream(b) ;
		ObjectInputStream ois = new ObjectInputStream(is);
		return ois.readObject() ;
	}
}
