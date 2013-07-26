<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="com.method.upAndDown.down.DownFile" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
if(true){
	String path = request.getParameter("path");
	DownFile.smartDownFile(this.getServletConfig(),request,response,path);
}

%>
