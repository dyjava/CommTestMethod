package com.method.file.XML;

import java.io.File;
import java.net.URL;
import java.util.*;

import org.dom4j.*; 
import org.dom4j.io.*; 

/**
 * DOM4J
 * @author dyong
 *
 */
public class DOM4J_XMLFile {
	
	public static void XMLFile(String filepath){
		try{
			String path = DOM4J_XMLFile.class.getResource(filepath).getPath() ;
			File f = new File(path); 
			Element root = DOM4J(f) ;

			Iterator iter=root.elementIterator("record") ;
			while(iter.hasNext()){
				Element foo = (Element)iter.next();
				System.out.print("===:" + foo.elementText("ID"));
				System.out.println("====:" + foo.elementText("hasAlbum"));
			}
		}catch (Exception e){
			System.out.println("DOM4J err:"+e.getMessage()) ;
		}
	}
	
	public static Element DOM4J(File f)
		throws DocumentException{
		SAXReader reader = new SAXReader(); 
		Document doc = reader.read(f); 
		return doc.getRootElement();
	}
	public static Element DOM4J(URL url)
	throws DocumentException{
	SAXReader reader = new SAXReader(); 
	Document doc = reader.read(url); 
	return doc.getRootElement();
}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLFile("/singer.xml") ;
//		XMLFile("http://u.yicha.cn/psearch/qqxml/pageXML.jsp?key=qq&sid=378563&pno=0&t=all") ;
	}

}
