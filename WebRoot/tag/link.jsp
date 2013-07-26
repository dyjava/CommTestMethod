<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://yicha.cn/pageTag/wap" prefix="page" %>
<jsp:directive.page import="com.method.tag.page.*"/>
<html>
  <head>
    <title>My JSP 'idx.jsp' starting page</title>
  </head>
  <%
  String p = request.getParameter("pno") ;
  if(p==null || p.equals("")){
   p="0" ;
  }

  TagParam tp = new TagParam() ;
  tp.setAllNo(12) ;
  tp.setPageNo(Integer.parseInt(p)) ;
  tp.setPageNoName("pno") ;
  tp.setUrl("link.jsp") ;
  
  Map map = new HashMap() ;
  map.put("ua","123") ;
  map.put("key","mm") ;
  map.put("key2","gg") ;
  tp.setParams(map) ;
  
  request.setAttribute("root",tp);
  %>
  <body>
    This is my JSP page. <br>
    <page:pageTag root="root" method="link">
    </page:pageTag>
  </body>
</html>
