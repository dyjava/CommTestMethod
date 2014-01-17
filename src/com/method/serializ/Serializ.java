package com.method.serializ;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/** 
 * 对象序列化
 * 单对象序列化和对象数组序列化
 * by dyong 2010-8-2
 */
public class Serializ {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String p = "e:/tmp/c.ser";
		
		ArrayList<CityBean> list = new ArrayList<CityBean>() ;
		for(int i=0;i<10;i++){
			CityBean c = new CityBean() ;
			c.setNo(i+1) ;
			c.setName("beijing"+i) ;
			c.setParentId(100) ;
			list.add(c) ;
		}
		writeObject(p,list) ;
		
		list = (ArrayList<CityBean>) readObject(p) ;
		for(CityBean b:list){
			System.out.println(b.getName()) ;
		}
		
//		list
		p="e:/tmp/cl.ser" ;
		List<Object> ol = new ArrayList<Object>() ;
		ol.add("bean start") ;
		for(int i=0;i<10;i++){
			CityBean c = new CityBean() ;
			c.setNo(i+1) ;
			c.setName("beijing"+i) ;
			c.setParentId(100) ;
			ol.add(c) ;
		}
		ol.add("bean end") ;
		writeObjectList(p,ol) ;
		
		ol = readObjectList(p) ;
		for(Object b:ol){
			if(b instanceof CityBean){
				System.out.println(((CityBean) b).getName()) ;
			} else {
				System.out.println(b.toString()) ;
			}
		}
	}

	/**
	 * 将序列化对象写入文件
	 * @param path
	 * @param obj
	 * @throws IOException
	 */
	public static void writeObject(String path,Object obj) throws IOException{
		FileOutputStream outstream = new FileOutputStream(path);
		ObjectOutputStream out = new ObjectOutputStream(outstream);
		out.writeObject(obj);
		out.close();
	}
	
	/**
	 * 从文件中读取序列化对象
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readObject(String path) throws IOException, ClassNotFoundException{
		FileInputStream is = new FileInputStream(path);
        ObjectInputStream q = new ObjectInputStream(is);
        return q.readObject() ; 
	}
	

	/**
	 * 将序列化对象写入文件
	 * @param path
	 * @param obj
	 * @throws IOException
	 */
	public static void writeObjectList(String path,List<Object> obj) throws IOException{
		FileOutputStream outstream = new FileOutputStream(path);
		ObjectOutputStream out = new ObjectOutputStream(outstream);
		for(Object o:obj){
			out.writeObject(o);
		}
		out.close();
	}
	
	/**
	 * 从文件中读取序列化对象
	 * @param path
	 * @return
	 * @throws IOException 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("finally")
	public static ArrayList<Object> readObjectList(String path) throws IOException{
		ArrayList<Object> list = new ArrayList<Object>() ;
		FileInputStream is = new FileInputStream(path);
        ObjectInputStream q = new ObjectInputStream(is);
        Object obj ;
        try{
	        while( (obj=q.readObject())!=null){
	        	list.add(obj) ; 
	        }
        } catch (Exception e){
        	e.printStackTrace() ;
        } finally {
        	return list ;
        }
	}
}
