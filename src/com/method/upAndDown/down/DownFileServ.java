package com.method.upAndDown.down;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DownFileServ extends HttpServlet {
	private static final Logger log = Logger.getLogger(DownFileServ.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		String path = request.getParameter("path");
		if(true){
			DownFile.smartDownFile(this.getServletConfig(), request, response, path);
		}else{
//		log.info("filepath:================================");
////		ȡ�ò��������ط��������ļ�����·��
//		log.info("filepath:="+filepath);
////		ȡ�������ļ���
//		File file = new File(filepath);
//		String showFilename = file.getName();
//		log.info("filename:="+showFilename);
////		����response����
//		response.reset();
//		response.setHeader("Pragma", "No-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", 0);
//		response.setContentType("application/octet-stream;charset=utf-8");
//		response.setHeader("Content-disposition",
//		"attachment;filename=\"" + showFilename + "\"");
//		
////		�����ļ�
//		FileInputStream fis = new FileInputStream(filepath);
//		ServletOutputStream fout = response.getOutputStream();
//		try{
//			int read=0;
//			while((read = fis.read())!=-1){
//				fout.write(read);
//			}
//			log.info("file size :"+read);
//		}catch(IOException e){
//			log.error("err:"+e.getMessage());
//		}finally{
//			fis.close();
//			fout.flush();
//		}
		}
	}

}
