package com.method.upAndDown.down;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class DownFile {

	public static boolean smartDownFile(ServletConfig confg,HttpServletRequest request,HttpServletResponse response,String path){
		try {
//			 新建一个SmartUpload对象
			SmartUpload su = new SmartUpload();
//			 初始化
			su.initialize(confg,request,response);
			
//			 设定contentDisposition为null以禁止浏览器自动打开文件，
//			保证点击链接后是下载文件。若不设定，则下载的文件扩展名为
//			doc时，浏览器将自动用word打开它。扩展名为pdf时，
//			浏览器将用acrobat打开。
			su.setContentDisposition(null);
//			 下载文件
//			System.out.println(new String(path.getBytes("GBK"),"utf-8"));
//			System.out.println(new String(path.getBytes("utf-8"),"GBK"));
//			System.out.println(new String(path.getBytes("GBK"),"ISO8859-1"));
//			System.out.println(new String(path.getBytes("ISO8859-1"),"utf-8"));
//			System.out.println(new String(path.getBytes("ISO8859-1"),"GBK"));
			path = new String(path.getBytes("ISO8859-1"),"GBK");
			su.downloadFile(path);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		return true ;
	}
}
