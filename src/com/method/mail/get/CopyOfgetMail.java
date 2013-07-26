package com.method.mail.get;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

public class CopyOfgetMail {
	private static final Logger log = Logger.getLogger(CopyOfgetMail.class);

	private static String getDisplayAddress(Address a){ //ת��RFC822ΪUnicode
		String pers = null;
		String addr = null;
		if(a instanceof InternetAddress &&((pers=((InternetAddress)a).getPersonal())!=null)){
			addr=pers+" "+"<"+((InternetAddress)a).getAddress()+">";
		} else {
			addr = a.toString();
		}
		return addr;
	}

	private static Store getConnect(){
		String protocol="pop3";//protocolΪ����Э�飬IMAP����POP
		String mailhost="pop.163.com";//mailhost����userΪ�û���passwdΪ����
		String user="dyong525";
		String passwd="dyong525";
		Store store = null;
		
		Session mailsession = Session.getInstance(System.getProperties(),null);
		mailsession.setDebug(false);
		try{
			store = mailsession.getStore(protocol);
			log.info("create store is OK.");
			store.connect(mailhost,-1,user,passwd);
			return store ;
		}catch(MessagingException e){
			log.error("����Э����?"+e.getMessage());
		}
		return null ;
	}
	
	public static void get(){
		int pid = 10;
		
		Store store = getConnect();
		log.info("store is ok.");
//		Session mailsession = Session.getInstance(System.getProperties(),null);
//		mailsession.setDebug(false);
//		Store store = null;//protocolΪ����Э�飬IMAP����POP
//		try {
//			store = mailsession.getStore(protocol);
//			store.connect(mailhost,-1,user,passwd);
//		} catch (MessagingException e) {
//			log.error("����Э����?"+e.getMessage());
//		}
		
		Folder folder = null;
		try {
			folder = store.getFolder("INBOX");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try{
			folder.open(Folder.READ_WRITE);
		}catch (MessagingException ex){
			try {
				folder.open(Folder.READ_ONLY);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		Message message[] = null ;
		try {
			message = folder.getMessages();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		FetchProfile fp=new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add(FetchProfile.Item.FLAGS);
		fp.add("X-Mailer");
		try {
			folder.fetch(message,fp);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		StringBuffer sb = new StringBuffer();
		int id=pid,j=0;
		for(j=0;j<id;j++);
		try {
			message[j].setFlag(Flags.Flag.SEEN,true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		sb.append("<table width='550' border='1' cellpadding='0' cellspacing='0' borderColorDark='#eaf0ff' borderColorLight='#000000' align='center'>");
		sb.append("<tr>");
		try {
			sb.append("<td width=60>���⣺</td><td width=490> "+message[j].getSubject()+" </td></tr>");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		String from=new String();
		Address[] fr = null;
		try {
			fr = message[j].getFrom();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		if(fr!=null){
			boolean tf=true;
			for(int i=0;i<fr.length;i++){
				from = from + getDisplayAddress(fr[i]);
			}
		}
		sb.append("<tr><td width=60>���ԣ�</td><td width=490> "+from+" </td></tr>");
		sb.append("<tr><td colspan='2'> <div align='center'><b>����</b></div><br>");
		
		Object o = null;
		try {
			o = message[j].getContent();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			if (message[j].isMimeType("text/plain")){ //����TypeΪtex/plain�Ϳ�ֱ�Ӷ����ˡ�
				sb.append((String)o+"</td>");
			}else if(message[j].isMimeType("multipart/*")){
				Multipart mp=(Multipart)o;
				Part part=mp.getBodyPart(0);
				String msg=(String)part.getContent();
				StringBuffer buf=new StringBuffer(msg.length()+6);
				char ch=' ';
				for(int i=0;i<msg.length();i++){//���������о�תΪ<br>
					ch=msg.charAt(i);
					if(ch==' '){
						buf.append("<br>");
					}else{
						buf.append(ch);
					}
				}
				sb.append(buf.toString());
			}else{
				sb.append("���ڻ��޷���ʾ��"+message[j].getContentType());
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sb.append("</tr>");
		sb.append("<table>");
		try {
			folder.close(true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws MessagingException, IOException {
		get();

	}

}
