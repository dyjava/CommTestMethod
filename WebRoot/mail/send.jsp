<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.method.mail.send.MailBean" %>
<%@ page import="com.method.mail.send.SendMail" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	MailBean mb = new MailBean();
String from = request.getParameter("from");
mb.setFrom(from);
mb.setTo(request.getParameter("to"));

mb.setFile("D:/ss.txt");
mb.setMailBody(request.getParameter("text"));
mb.setMailTitle(request.getParameter("title"));

mb.setMailServ("smtp.163.com");
mb.setUsr("dyong525");
mb.setPwd("dyong525");

try{
	//sm.sendMail(mb);
System.out.println("=====000=========");
		SendMail themail = new SendMail("smtp.163.com");
		System.out.println("=====123=========");
		themail.setNeedAuth(mb.isNeedAuth());
System.out.println("=====111=========");
		themail.setSubject(mb.getMailTitle());
		themail.setBody(mb.getMailBody());
System.out.println("=====222=========");
		if(mb.getFile().trim().length()>0){
	themail.addFileAffix(mb.getFile());
		}
System.out.println("=====333=========");
		themail.setFrom(mb.getFrom());
		themail.setTo(mb.getTo());
//		themail.setCopyTo("tianjun@yicha.cn");
		themail.setNamePass(mb.getUsr(),mb.getPwd());
System.out.println("=====444=========");
		themail.sendout();

}catch(Exception e){
e.printStackTrace();
}
System.out.println("=====202=========");
%>
<html>
  <head>
    <title>page list</title>
   </head>
  <body>
  <form action="send.jsp" name="" method="post">
  <table border="1" align="center">
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
          <td>内容:<input type="text" name="text"/></td>
        </tr>
	</table>
	</form>
  </body>
</html>
