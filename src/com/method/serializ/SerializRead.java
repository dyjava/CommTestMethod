package com.method.serializ;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/** 
 * by dyong 2010-8-3
 * 读取序列化对象
 */
public class SerializRead {

	public static Object readObject(String path) throws IOException, ClassNotFoundException{
		FileInputStream is = new FileInputStream(path);
        ObjectInputStream q = new ObjectInputStream(is);
		try {
			return q.readUnshared();
		} catch (IOException e) {
			throw e ;
		} catch (ClassNotFoundException e) {
			throw e ;
		} finally {
			q.close() ;
			is.close() ;
		}
	}
	
	@SuppressWarnings("finally")
	public static Object[] readObjects(String path) throws IOException{
		FileInputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        
		ArrayList<Object> list = new ArrayList<Object>() ;
		try {
			byte[] skip = new byte[4] ;
			while(true){
				try{
				list.add(ois.readObject()) ;
				} catch (Exception ee){
					try{
					ois.read(skip) ;
					} catch (Exception te){
						break ;
					}
				}
			}
		} finally {
			ois.close() ;
			is.close() ;
			return list.toArray() ;
		}
	}
}
