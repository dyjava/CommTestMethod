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
			//���������ϴ��ļ����ļ���ʽ
			su.setTotalMaxFileSize(1024*1024*10); 
			su.setAllowedFilesList("gif,exe,rar,rmvb,rm,txt");
			
			//�����ļ�
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
	        // ��������ļ��ߴ磬������4MB
	        fu.setSizeMax(4*1024*1024);
	        // ���û�������С��������4kb
	        fu.setSizeThreshold(4*1024);
	        // ������ʱĿ¼��
	        fu.setRepositoryPath(path);

	        // �õ����е��ļ���
	        List fileItems = fu.parseRequest(request);
	        Iterator i = fileItems.iterator();
	        // ���δ���ÿһ���ļ���
	        ArrayList files = new ArrayList();
	        while(i.hasNext()) {
	            FileItem fi = (FileItem)i.next();
	            // ����ļ���������ļ�������·����
	            String fileName = fi.getName();
	            if(fileName!=null && fileName.trim().length()>0) {
	           		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
//	           		fileName = new String(fileName.getBytes("utf-8"),"GBK");
	           		
//	           		�����ļ���
	           		String type = fileName.substring(fileName.indexOf("."));
	           		String d = new Date().toLocaleString();
	           		fileName = d.substring(2).replaceAll(" ", "_").replaceAll(":", "-")+"_"+(int)(Math.random()*100);
	            	fileName += type;
	            	
//	            	д���ļ������ļ��������б�
	           		fi.write(new File(path ,fileName));
	           		files.add(fileName);
	            }
	        }
//	        �����ļ����б�
	        return files;
	        // ��ת���ϴ��ɹ���ʾҳ��
	    }
	    catch(Exception e) {
			System.out.println("�ļ��ϴ�ʧ��=="+e.getMessage());
	    }
	    return null ;
	}

}
