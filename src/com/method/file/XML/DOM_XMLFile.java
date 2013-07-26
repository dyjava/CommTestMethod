package com.method.file.XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class DOM_XMLFile {
	
	/**
	 * DOM ·½·¨
	 * @param filepath
	 * @throws Exception
	 */
	public static void domXML(String filepath)
		throws Exception{
		try{
			String path = DOM_XMLFile.class.getResource(filepath).getPath() ;
			Document doc = domXMLsrc(path) ;
			NodeList nl = doc.getElementsByTagName("record");
			for (int i=0;i<nl.getLength();i++){
				System.out.print("£½£½£½:" + doc.getElementsByTagName("ID").item(i).getFirstChild().getNodeValue());
				System.out.println("£½£½£½:" + doc.getElementsByTagName("starName").item(i).getLastChild().getNodeValue());
//				System.out.println("£½£½£½:" + doc.getElementsByTagName("starName").item(i).getFirstChild().getNodeValue());
			}
			}catch(Exception e){
				throw new Exception("domXML err:"+e.getMessage()) ;
			}
	}
	
	private static Document domXMLsrc(String filepath)
		throws Exception{
		try{
		File f = new File(filepath);
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc = builder.parse(f);
//		builder.parse(arg0);
		return doc;
		}catch(Exception e){
			throw new Exception("domXMLsrc err:"+e.getMessage()) ;
		}
	}
	
	public static void main(String[] args) {

		try {
			domXML("singer.xml") ;
		} catch (Exception e) {
			System.out.println(e.getMessage()) ;
		}

	}

}
