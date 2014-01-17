package com.method.mail;

import com.method.mail.send.MailBean;
import com.method.mail.send.SendMail;

/** 
 * by dyong 2010-6-9
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MailBean mb = new MailBean();
//		
//		匿名发送
		mb.setNeedAuth(false) ;
		mb.setTo("diyong@umessage.com.cn") ;
		mb.setFrom("system") ;
		mb.setMailServ("mail.umessage.com.cn") ;
		String mailbody = "<meta http-equiv=Content-Type content=text/html; charset=gb2312>"+
		"<div align=left><a href='http://m.12580.com'> 12580 </a></div>";
		mb.setMailBody(mailbody);
		SendMail.sendMail(mb);
		
//		登陆发送
//		mb.setNeedAuth(true) ;
//		mb.setTo("dyong525@163.com,dyong525@gmail.com") ;
//		mb.setCopyto("diyong@umessage.com.cn") ;
//		mb.setFrom("dyong525@163.com") ;
//		mb.setUsr("dyong525") ;
//		mb.setPwd("dyong525") ;
//		mb.setMailServ("smtp.163.com") ;
//		String mailbody2 = "<meta http-equiv=Content-Type content=text/html; charset=gb2312>"+
//		"<div align=left><a href='http://m.12580.com'> 12580 test </a></div>";
//		mb.setMailBody(mailbody2);
//		
//		SendMail.sendMail(mb);
		
	}

}
