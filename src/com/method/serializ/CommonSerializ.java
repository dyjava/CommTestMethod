package com.method.serializ;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/** 
 * 对象序列化,传递类型
 * by dyong 2010-8-2
 */
public class CommonSerializ<C>{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String p = "e:/tmp/city.ser";
		CommonSerializ<List<CityBean>> cs = new CommonSerializ<List<CityBean>>() ;
		List<CityBean> list = new ArrayList<CityBean>() ;
		for(int i=0;i<10;i++){
			CityBean c = new CityBean() ;
			c.setNo(i+1) ;
			c.setName("beijing"+i) ;
			c.setParentId(100) ;
			list.add(c) ;
		}
		cs.writeObject(p,list) ;
		
		List<CityBean> l = cs.readObject(p) ;
		for(CityBean b:l){
			System.out.println( b.getName()) ;
		}
	}

	/**
	 * 将序列化对象写入文件
	 * @param path
	 * @param obj
	 * @throws IOException
	 */
	public void writeObject(String path,C obj) throws IOException{
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
	@SuppressWarnings("unchecked")
	public C readObject(String path) throws IOException, ClassNotFoundException{
		FileInputStream is = new FileInputStream(path);
        ObjectInputStream q = new ObjectInputStream(is);
        return (C) q.readObject() ;
	}
	
}
