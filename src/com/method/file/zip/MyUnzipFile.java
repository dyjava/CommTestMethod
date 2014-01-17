package com.method.file.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/** 
 * by dyong 2010-7-27
 */
public class MyUnzipFile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MyUnzipFile uzip = new MyUnzipFile() ;
		String zipfile = "e:/b.zip" ;
		String unzippath = "e:/t/" ;
		uzip.unzip(zipfile,unzippath) ;
	}
	public void unzip(String zipfile, String unzippath) throws IOException{
		File zf = new File(zipfile) ;
		unZipFile(zf,unzippath) ;
	}

	private void unZipFile(File infile,String dirname) throws IOException {
		if(!checkZip(infile)){
			throw new IOException("file:"+infile+" is not a zip file.") ;
		}
		
//		指定zip文件编码，否则解压的文件会乱码
		ZipFile zf = new ZipFile(infile,"gbk") ;
		Enumeration<?> en = zf.getEntries() ;
		while(en.hasMoreElements()){
			ZipEntry file = (ZipEntry) en.nextElement() ;
			String filename = file.getName() ;

//			String encode = zf.getEncoding() ;
//			System.out.println(encode) ;
			
//			   创建文件夹
			File dirs = new File(dirname+File.separator+filename );
			if(!dirs.getParentFile().exists()){
				makeParentDir(dirs.getParentFile()) ;
			}
			
			if (!file.isDirectory()){
				FileOutputStream out = new FileOutputStream(dirs);
				
				InputStream is = zf.getInputStream(file);
				int slen ;
				byte[] c = new byte[1024];
				while((slen = is.read(c,0,c.length)) != -1){
					out.write(c,0,slen);
				}
				out.close();
				System.out.println("out:"+filename) ;
			}
		}
	}
	
//	创建父级文件夹目录
	private void makeParentDir(File file) throws IOException{
		if(file.isFile()){
			makeParentDir(file.getParentFile()) ;
			return ;
		}
		if(file.getParentFile().exists() && !file.exists()){
			file.mkdir() ;
			return ;
		}
		if(!file.getParentFile().exists()){
			makeParentDir(file.getParentFile()) ;
			file.mkdir() ;
		}
	}
	
//	检查是否是ZIP文件
	private boolean checkZip(File f){
		try {
			ZipFile zip = new ZipFile(f);
			zip.close();
			return true ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false ;
		}
	}
	
}
