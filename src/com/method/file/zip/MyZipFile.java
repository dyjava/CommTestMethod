package com.method.file.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/** 
 * by dyong 2010-7-27
 */
public class MyZipFile {

	/**
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MyZipFile zip = new MyZipFile() ;
		zip.zip("e:/表", "e:/b.zip") ;
//		zip.zip("e:/klb.txt", "e:/k.zip") ;
		zip.zip("e:/usr", "e:/l.zip") ;
		

		zip.addZip("e:/表", "e:/a.zip") ;
		zip.addZip("e:/klb.txt", "e:/a.zip") ;
		zip.addZip("e:/usr", "e:/a.zip") ;
	}

	/**
	 * 文件压缩
	 * 支持压缩文件夹和单个文件，支持中文。但不支持增加文件。
	 * @param dir 压缩的文件或文件夹
	 * @param zipname 压缩后的zip文件目录
	 * @throws IOException
	 */
	public void zip(String dir,String zippath) throws IOException{
		File file = new File(dir) ;
		FileOutputStream fout = new FileOutputStream(zippath,false) ;
        ZipOutputStream gzout = new ZipOutputStream(fout) ;
        String root = "" ;
        zipFolder(file,root,gzout) ;
        gzout.close() ;
		fout.close() ;
	}
	
	/**
	 * 文件压缩
	 * 向已有zip文件中追加压缩文件。
	 * @param dir
	 * @param zippath
	 * @throws IOException
	 */
	public void addZip(String dir,String zippath) throws IOException{
//		zip 文件不存在，是新增压缩文件
		File zf = new File(zippath) ;
		if(!zf.exists()){
			zip(dir,zippath) ;
			return ;
		}
		
		File tempfile = new File(zippath+"temp.zip") ;
		zf.renameTo(tempfile) ;
		FileOutputStream fout = new FileOutputStream(zippath,false) ;
        ZipOutputStream gzout = new ZipOutputStream(fout) ;
        
//        添加原有文件
        copyZipStream(gzout,tempfile) ;
        tempfile.deleteOnExit() ;
        
        String root = "" ;
        File file = new File(dir) ;
        zipFolder(file,root,gzout) ;
        gzout.close() ;
		fout.close() ;
	}
	
	/**
	 * 压缩文件夹
	 * @param dir 要压缩的文件夹
	 * @param zipDir 压缩文件中的文件夹
	 * @param gzout 压缩文件输出流
	 * @throws IOException
	 */
	private void zipFolder(File dir,String zipDir,ZipOutputStream gzout) throws IOException {
//		files
		File[] files = null ;
		if(dir.isFile()){
			files = new File[]{dir} ;
		} else if(dir.isDirectory()){
			files = dir.listFiles() ;
		}
		
//		zip文件中的文件夹处理
		if(zipDir.length()==0 && dir.isDirectory()){
			zipDir = dir.getName() ;
		}
		if(zipDir.length()>0){
			zipDir = zipDir+File.separator ;
		}
		
		if(files.length>0)
		for(File file:files){
			String filename = file.getName() ;
			if(file.isDirectory()){	//文件夹递归
				zipFolder(file,zipDir+filename,gzout) ;
			} else {	//文件压缩
				FileInputStream fis = new FileInputStream(file) ;
				zipFile(fis,zipDir+filename,gzout) ;
			}
		}
	}
	
	/**
	 * 压缩文件
	 * @param fis 压缩文件流
	 * @param zipPath 压缩文件中的文件路径
	 * @param gzout 压缩文件输出流
	 * @throws IOException
	 */
	private void zipFile(FileInputStream fis,String zipFilePath,ZipOutputStream gzout) throws IOException {
		int number;
		byte[] buf = new byte[1024*10];
		try {
			
			gzout.putNextEntry((ZipEntry) new ZipEntry(zipFilePath));//写新的条目
			gzout.setEncoding("gbk") ;	//指定文件编码
			while ((number = fis.read(buf)) != -1) {
				gzout.write(buf, 0, number);
			}
			gzout.closeEntry();//关闭当前 ZIP 条目并定位流以写入下一个条目。
			fis.close();
		} catch (IOException e) {
			e.printStackTrace() ;
			System.exit(1);
		} finally {
			fis.close() ;
		}
		System.out.println("add zip	"+zipFilePath) ;
    }

	/**
	 * 添加zip文件到新zip文件中
	 * @param gzout
	 * @param zipfile
	 * @throws IOException
	 */
	private void copyZipStream(ZipOutputStream gzout,File zipfile) throws IOException{
		gzout.setEncoding("gbk") ;
		ZipFile zf = new ZipFile(zipfile,"gbk") ;
		Enumeration<?> en = zf.getEntries() ;
		while(en.hasMoreElements()){
			ZipEntry file = (ZipEntry) en.nextElement() ;
			gzout.putNextEntry(file) ;
			
			InputStream is = zf.getInputStream(file);
			int slen ;
			byte[] c = new byte[1024];
			while((slen = is.read(c,0,c.length)) != -1){
				gzout.write(c,0,slen);
			}
		}
		zf.close() ;
		gzout.flush() ;
	}
}
