package com.method.serializ;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/** 
 * by dyong 2010-8-3
 * 将序列化对象写入文件。
 */
public class SerializWrite {

	public static void writeObject(String path,Object obj) throws IOException{
		FileOutputStream outstream = new FileOutputStream(path);
		ObjectOutputStream out = new ObjectOutputStream(outstream);
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			throw e ;
		} finally {
			out.close();
			outstream.close() ;
		}
	}
	
	public static void writeObject(String path,Object[] objs) throws IOException{
		FileOutputStream outstream = new FileOutputStream(path);
		ObjectOutputStream out = new ObjectOutputStream(outstream);
		try {
			for(int i=0;i<objs.length;i++){
				out.writeObject(objs[i]);
			}
		} catch (IOException e) {
			throw e ;
		} finally {
			out.close();
			outstream.close() ;
		}
	}
	
	public static void writeObjectAdd(String path,Object obj) throws IOException{
		FileOutputStream outstream = new FileOutputStream(path,true);
		ObjectOutputStream out = new ObjectOutputStream(outstream);
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			throw e ;
		} finally {
			out.close();
			outstream.close() ;
		}
	}
}
