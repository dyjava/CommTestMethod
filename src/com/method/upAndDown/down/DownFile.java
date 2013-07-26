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
//			 �½�һ��SmartUpload����
			SmartUpload su = new SmartUpload();
//			 ��ʼ��
			su.initialize(confg,request,response);
			
//			 �趨contentDispositionΪnull�Խ�ֹ������Զ����ļ���
//			��֤������Ӻ��������ļ��������趨�������ص��ļ���չ��Ϊ
//			docʱ����������Զ���word��������չ��Ϊpdfʱ��
//			���������acrobat�򿪡�
			su.setContentDisposition(null);
//			 �����ļ�
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
