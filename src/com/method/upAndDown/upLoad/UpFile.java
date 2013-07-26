package com.method.upAndDown.upLoad;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class UpFile extends HttpServlet {
	private static final Logger log = Logger.getLogger(UpFile.class);
	private static String path ="";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		path = request.getRealPath("/")+"data";
		log.error(path);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		List list = AllUpFile.FileUpload(request, path);
		for(int i=0;i<list.size();i++){
			out.println(list.get(i)+"<br/>");
		}

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
