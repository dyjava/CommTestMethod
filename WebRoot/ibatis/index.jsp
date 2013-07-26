<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="my.common.database.ibatis.*"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>index.jsp</title>
  </head>
  <body>
 <%
	User user = new User() ;
	user.setId(3) ;
	user.setName("l") ;
	
	UserService us = new UserService() ;
	us.save(user) ;

 %>
  </body>
</html>
