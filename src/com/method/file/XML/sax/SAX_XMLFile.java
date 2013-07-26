package com.method.file.XML.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class SAX_XMLFile extends DefaultHandler{
	static java.util.Stack tags = new java.util.Stack(); 

	public static void SAX_XML(){
		long lasting = System.currentTimeMillis();
		try {
			String path = SAX_XMLFile.class.getResource("/singer.xml").getPath() ;
			File xmlfile = new File(path); 
			SAXParserFactory factory = SAXParserFactory.newInstance(); 
			factory.setValidating(false); 
			SAXParser saxParse = factory.newSAXParser(); 
			saxParse.parse(xmlfile, new SaxXmlHandler()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("运行时间：" + (System.currentTimeMillis() - lasting) + "毫秒");
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SAX_XML() ;
	}

}


