package com.method.upAndDown.upLoad;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class SmartUp {

	public static boolean smartUpFile(ServletConfig confg,HttpServletRequest request,HttpServletResponse response,String path){
		try {
//			新建一个SmartUpload对象
			SmartUpload su = new SmartUpload();

			su.initialize(confg,request,response);
			//设置允许上传文件的文件格式
			su.setTotalMaxFileSize(1024*1024*10); 
			su.setAllowedFilesList("gif,exe,rar");
			
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
	
	public static boolean smartUpFile(PageContext pageContext){
		try{
//		 新建一个SmartUpload对象
		SmartUpload su = new SmartUpload();
//		 上传初始化 
		su.initialize(pageContext);
//		 设定上传限制
//		 1.限制每个上传文件的最大值 5M。
		 su.setMaxFileSize(5*1024*1024);
//		 2.限制总上传数据的长度。
		 su.setTotalMaxFileSize(20*1024*1024);
//		 3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt,jpg,gif文件。
		 su.setAllowedFilesList("doc,txt,jpg,gif");
//		 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件。
		 su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
//		 上传文件
		su.upload();
//		 将上传文件全部保存到指定目录 
		int count = su.save("/upload");
		System.out.println("共有"+count+"个文件上传成功！");

//		 逐一提取上传文件信息，同时可保存文件。
		for (int i=0;i<su.getFiles().getCount();i++){
			com.jspsmart.upload.File file = su.getFiles().getFile(i);
			
//		 	若文件不存在则继续
			if (file.isMissing()){
				continue;
			}
			FileBean fb = new FileBean();
//		 	显示当前文件信息
			fb.setFormname(file.getFieldName());
			fb.setFilename(file.getFileName());
			fb.setSize(file.getSize());
			fb.setExt(file.getFileExt());
			fb.setPath(file.getFilePathName());
			
//			 将文件另存
//			file.saveAs("/upload/" + myFile.getFileName());
//		 	另存到以WEB应用程序的根目录为文件根目录的目录下
//		 	file.saveAs("/upload/" + myFile.getFileName(),su.SAVE_VIRTUAL);
//		 	另存到操作系统的根目录为文件根目录的目录下
//		 	file.saveAs("c:\temp\" + myFile.getFileName(),su.SAVE_PHYSICAL);
		}
		}catch(Exception e){
			
		}
		return true ;
	}
}
