package com.method.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class CopyFile {
	private static final Logger log = Logger.getLogger(CopyFile.class);
	
	/**
	 * 将指定文件夹下的文件全部拷贝到指定文件夹下。
	 * @param srcFolder
	 * @param goalFolder
	 * @throws IOException
	 */
	public void copy(String srcFolder,String goalFolder){
		try {
			newFolder(goalFolder);
			File goal = new File(goalFolder);
			if(goal.exists()){
				copyFolder(srcFolder,goalFolder) ;
			}
		} catch (IOException e) {
			log.info(e.getMessage()) ;
			e.printStackTrace();
		}
	}
	private void copyFolder(String srcFolder,String goalFolder)
	throws IOException{
		File src = new File(srcFolder);
		File[] fileList = src.listFiles();
		if (fileList == null){
			return;
		}
		
//		去掉文件夹路径中多余/
		if(goalFolder.endsWith("/")){
			goalFolder = goalFolder.substring(0,goalFolder.length()-1);
		}
//		创建指定文件夹
		newFolder(goalFolder);

		for(int i=0; i<fileList.length; i++){
			File file = fileList[i] ;
			String filePath = file.getPath();
			
			if(file.isDirectory()){
//				 如果是目录，继续递归分析
				String folerName = filePath.substring(filePath.lastIndexOf("\\")+1) ;
				String newFolder = goalFolder+"/"+folerName ;
				copyFolder(filePath,newFolder);
			}else{
//				如果是文件，拷贝文件
				copyFile(filePath,goalFolder);
			}
		}
	}
	
	/**
	 * 将文件拷贝到指定文件夹下
	 * @param filepath
	 * @param folderpath
	 * @param filename 拷贝后的文件名
	 * @throws IOException
	 */
	private void copyFile(String filepath,String folderpath,String filename)
	throws IOException{
		InputStream is = new FileInputStream(filepath);
		String outFile = folderpath+"/"+filename;
		log.info("out file path is :"+outFile) ;
		SaveToFile(is, outFile, 5);
	}
	private void copyFile(String filepath,String folderpath)
	throws IOException{
		String filename = null ;
		if(filepath.indexOf("/")>-1){
			filename = filepath.substring(filepath.lastIndexOf("/")+1);
		}else if(filepath.indexOf("\\")>-1){
			filename = filepath.substring(filepath.lastIndexOf("\\")+1);
		}
		copyFile(filepath,folderpath,filename);
	}
	
	/**
	 * 创建文件夹
	 * @throws Exception 
	 */
	private void newFolder(String folderPath){
		try{
		File myFilePath = new File(folderPath);
		if(!myFilePath.exists()){
			myFilePath.mkdir();
		}
		}catch (Exception e){
			log.info("==>"+e.getMessage()) ;
		}
	}

	private boolean SaveToFile(InputStream is,String filePath,int bufferSize)
	throws IOException{
		FileOutputStream os = new FileOutputStream(filePath);
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(os);//用流复制
		boolean b = false ;
		try{
			byte[] b1=new byte[1024*bufferSize];//设一个1024*bufferSize字节的数组 
			int read=0;
			int bytes = 0 ;
			while((read=bis.read(b1))!=-1){
				bos.write(b1,0,read);//从数组里往文件里写数据 
				bytes+= read;
			}
			System.out.println("all bytes is :"+bytes);
			b = true ;
		}catch(IOException e){
			throw e;
		}finally{
			bis.close();
			bos.close();
		}
		return b ;
	}
	
	public static void main(String[] args){
		CopyFile cf = new CopyFile();
		try {
			String srcFloder = "E:/union";
			String globFloder = "E:/newJava" ;
			cf.copy(srcFloder,globFloder);
		} catch (Exception e) {
			log.error("err:"+e.getMessage());
		}
	}

}
