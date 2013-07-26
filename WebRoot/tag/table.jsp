<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://yicha.cn/tableTag/wap" prefix="table" %>
<jsp:directive.page import="com.method.tag.option.*"/>
<jsp:directive.page import="com.method.tag.*"/>
<html>
  <head>
    <title>My JSP page</title>
  </head>
  <%
  List list = new ArrayList() ;
  for(int i=0;i<10;i++){
  	Option op = new Option() ;
  	op.setKey("a"+i) ;
  	op.setValue("b"+(int)(Math.random()*90)) ;
  	list.add(op) ;
  }
  request.setAttribute("list",list);
  
  List list2 = new ArrayList() ;
  for(int i=0;i<10;i++){
  	TestBean tb = new TestBean() ;
  	tb.setId(i+1) ;
  	tb.setName("a"+i) ;
  	tb.setTitle("b"+i) ;
  	tb.setDate(new Date().toGMTString()) ;
  	tb.setSex("man") ;
  	list2.add(tb) ;
  }
  request.setAttribute("l2",list2) ;
  %>
  <body>
  <table:table name="l2" border="0" bgcolor_title="6699CC">
  </table:table>
    <table:table name="list" align="left">
    	<table:property name="key" title="key-key"></table:property>
    	<table:property name="value" title="value-value"></table:property>
    </table:table>
  </body>
</html>
