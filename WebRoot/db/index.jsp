<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="my.common.database.db.conn.QuerySQL"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>index.jsp</title>
  </head>
  <body>
 <%
 String sql = "select * from data";
	java.sql.ResultSet rs = QuerySQL.exeQuery(sql);
	int i=0;
	while(rs.next() && i<20 ){
		i++;
		out.println(rs.getString("showKey")+"<br/>");
	}
 %>
  </body>
</html>
