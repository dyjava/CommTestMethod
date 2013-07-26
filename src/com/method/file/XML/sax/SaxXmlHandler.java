package com.method.file.XML.sax;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * made by dyong 
 * date 2008-9-4 下午05:04:34
 **/
public class SaxXmlHandler extends DefaultHandler {

	private Stack tags=new Stack(); 
	
//	public 
	
	public void startDocument()
	throws SAXException {
		System.out.println("~~~~~~~~~开始解析文档~~~~~~~~~~~~~"); 
		super.startDocument();
	}

	public void startElement(String uri,String localName,String qName,Attributes attributes)
	throws SAXException {
//		System.out.println("-----开始解析"+qName+"----"); 
		tags.push(qName); 
		super.startElement(uri, localName, qName, attributes); 
	}

	public void characters(char[] ch, int start, int length)
	throws SAXException { 
		String value=new String(ch,start,length);
		String tag=(String) tags.peek(); 
		if(tag.equals("ID")) {
			System.out.println("ID="+value);
		} else if(tag.equals("starName")) {
			System.out.println("starName="+value);
		} else if(tag.equals("introduction")) {
			System.out.println("introduction="+value);
		} else if(tag.equals("hasAlbum")) {
			System.out.println("hasAlbum="+value);
		} else {
//			System.out.println(tag+" value="+value); 
		}
		super.characters(ch, start, length);
	}
	
	public void endElement(String uri,String localName,String qName)
	throws SAXException { 
//		System.out.println("-----结束解析"+qName+"----"); 
		tags.pop(); 
		super.endElement(uri, localName, qName); 
	}
	
	public void endDocument()
	throws SAXException { 
		System.out.println("~~~~~~~~~结束解析文档~~~~~~~~~~~~~"); 
		super.endDocument(); 
	} 
}
