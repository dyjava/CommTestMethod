<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ page import="com.method.mail.get.GetMail" %>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="com.method.mail.send.MailBean"/>
<%
	List list = GetMail.get(10);
for(int i=0;i<list.size();i++){
	MailBean mb = (MailBean)list.get(i);
	out.print(new String(mb.getFrom().getBytes("ISO8859-1"),"GBK")+"<br/>");
	out.print(mb.getTo()+"<br/>");
	out.print(mb.getFile()+"<br/>");
	out.print(new String(mb.getMailTitle().getBytes("ISO8859-1"),"GBK")+"<br/>");
	out.print(new String(mb.getMailBody().getBytes("ISO8859-1"),"utf-8")+"<br/>");
	out.print("==============================<br/>");
}
%>
