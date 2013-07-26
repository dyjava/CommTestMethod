package com.method.upAndDown.upLoad;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class AllUpFile {

	public static boolean smartUpFile(ServletConfig confg,HttpServletRequest request,HttpServletResponse response,String path){
		try {
			SmartUpload su = new SmartUpload();

			su.initialize(confg,request,response);
			//设置允许上传文件的文件格式
			su.setTotalMaxFileSize(1024*1024*10); 
			su.setAllowedFilesList("gif,exe,rar,rmvb,rm,txt");
			
			//加载文件
			su.upload();
			su.save(path);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		return true ;
	}
	
	public static List FileUpload(HttpServletRequest request,String path){
		try {
	        DiskFileUpload fu = new DiskFileUpload();
	        // 设置最大文件尺寸，这里是4MB
	        fu.setSizeMax(4*1024*1024);
	        // 设置缓冲区大小，这里是4kb
	        fu.setSizeThreshold(4*1024);
	        // 设置临时目录：
	        fu.setRepositoryPath(path);

	        // 得到所有的文件：
	        List fileItems = fu.parseRequest(request);
	        Iterator i = fileItems.iterator();
	        // 依次处理每一个文件：
	        ArrayList files = new ArrayList();
	        while(i.hasNext()) {
	            FileItem fi = (FileItem)i.next();
	            // 获得文件名，这个文件名包括路径：
	            String fileName = fi.getName();
	            if(fileName!=null && fileName.trim().length()>0) {
	           		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
//	           		fileName = new String(fileName.getBytes("utf-8"),"GBK");
	           		
//	           		产生文件名
	           		String type = fileName.substring(fileName.indexOf("."));
	           		String d = new Date().toLocaleString();
	           		fileName = d.substring(2).replaceAll(" ", "_").replaceAll(":", "-")+"_"+(int)(Math.random()*100);
	            	fileName += type;
	            	
//	            	写入文件，将文件名加入列表
	           		fi.write(new File(path ,fileName));
	           		files.add(fileName);
	            }
	        }
//	        返回文件名列表
	        return files;
	        // 跳转到上传成功提示页面
	    }
	    catch(Exception e) {
			System.out.println("文件上传失败=="+e.getMessage());
	    }
	    return null ;
	}

}
