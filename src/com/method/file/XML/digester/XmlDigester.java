package com.method.file.XML.digester;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

/** 
 * by dyong 2010-4-16
 */
public class XmlDigester {

	protected Object xmlToObject(String content,String path){
		return xmlToObject(content,path,"utf-8") ;
	}
	
	protected Object xmlToObject(String content,String path,String decode){
		List<Object> list = xmlToObjectList(content,path,decode) ;
		if(list!=null && list.size()>0){
			return list.get(0) ;
		}
		return null ;
	}
	
	protected List<Object> xmlToObjectList(String content,String path){
		return xmlToObjectList(content, path,"utf-8");
	}
	protected List<Object> xmlToObjectList(String content,String path,String decode){

		List<Object> list = new ArrayList<Object>();

		URL rules = getClass().getResource(path);

		Digester digesterObj = DigesterLoader.createDigester(rules);

		digesterObj.push(list);

		InputStream inputObject;

		try {
			inputObject =new ByteArrayInputStream(content.getBytes(decode));
			
			digesterObj.parse(inputObject);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list ;
	}
}
