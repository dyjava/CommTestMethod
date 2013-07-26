package com.method.file.XML;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * made by dyong 
 * date 2009-4-7 ÏÂÎç03:55:54
 **/
public class Dom4j {

	public void dom(String path) 
		throws DocumentException{
		File f = new File(path) ;
		Element ele = this.DOM4J(f) ;
		Iterator ite = ele.elementIterator("item") ;
		while(ite.hasNext()){
			ele = (Element)ite.next();
			
			Attribute ab = ele.attribute("singer") ;
			if(ab!=null){
				System.out.println(ab.getData()) ;
			}
			
			Iterator iter = ele.elementIterator("album_msg") ;
			while(iter.hasNext()){
				Element ele2 = (Element)iter.next();
				
				System.out.println("	"+ele2.elementText("album_name")) ;
				System.out.println("	"+ele2.elementText("album_id")) ;
			}
		}
	}
	public void dom2(String path) 
	throws DocumentException, MalformedURLException{
		URL url = new URL(path) ;
		Element ele = this.DOM4J(url) ;
		Iterator ite = ele.elementIterator("sid") ;
		while(ite.hasNext()){
			ele = (Element)ite.next();
			System.out.println( ) ;
			System.out.println(ele.elementText("site")) ;
			System.out.println(ele.elementText("description")) ;
			System.out.println(ele.elementText("product")) ;
			System.out.println(ele.elementText("title")) ;
			System.out.println(ele.elementText("middleShow")) ;
			System.out.println(ele.elementText("returnLink ")) ;
			System.out.println(ele.elementText("bottom ")) ;
		}
	}

	private Element DOM4J(File f)
		throws DocumentException{
		SAXReader reader = new SAXReader(); 
		Document doc = reader.read(f); 
		return doc.getRootElement();
	}
	private Element DOM4J(URL url)
		throws DocumentException{
		SAXReader reader = new SAXReader(); 
		Document doc = reader.read(url); 
		return doc.getRootElement();
	}
	
	/**
	 * @param args
	 * @throws DocumentException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws DocumentException, MalformedURLException {
		String filepath = "e:\\album.xml" ;
		Dom4j dom4j = new Dom4j() ;
		dom4j.dom(filepath) ;
		
//		
//		String url = "http://sp.yicha.cn/CMS/data/cringSP.jsp" ;
//		url = "http://sp.yicha.cn/CMS/data/unionSite.xml" ;
//		dom4j.dom2(url) ;
	}

}
