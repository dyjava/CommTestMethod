<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>page list</title>
   </head>
  <body>
  <a href="getmail.jsp">收邮件</a>
  <form action="/test/MailServ" name="" method="post">
  <table border="0" align="center">
        <tr>
          <td>邮件发送功能</td>
        </tr>
        <tr>
          <td>收件人<input type="text" name="to"/></td>
        </tr>
        <tr>
          <td>抄送<input type="text" name="cp"/></td>
        </tr>
        <tr>
          <td>发件人<input type="text" name="from"/></td>
        </tr>
        <tr>
          <td>标题:<input type="text" name="title"/></td>
        </tr>
        <tr>
          <td>内容:<input type="text" name="text"/></td>
        </tr>
        <tr>
          <td><input type="submit"/></td>
        </tr>
	</table>
	</form>
  </body>
</html>
