<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.method.file.ReadLocalFileMethod"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>index.jsp</title>
  </head>
  <body>
<%
String path = "/test.xml" ;
String test = ReadLocalFileMethod.readLocalFile2(path) ;
out.print(test) ;
%>
  </body>
</html>
