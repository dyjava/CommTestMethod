<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ page import="com.jspsmart.upload.*" %>
<jsp:directive.page import="com.method.upAndDown.upLoad.AllUpFile"/>
<html>
<head>
<title>Untitled Document</title>
</head>
<body>
<%
String tmppath = request.getRealPath("/data/");
String path = tmppath;

AllUpFile.smartUpFile(this.getServletConfig(),request,response,path);
%>
</body>
</html>