package com.method.file.XML.digester;

import java.io.IOException;
import java.util.List;

import com.method.file.ReadLocalFileMethod;

/** 
 * by dyong 2010-6-9
 */
public class DigesterMain extends XmlDigester{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		DigesterMain d = new DigesterMain() ;
		String content = ReadLocalFileMethod.readLocalFile2("/singer.xml") ;
		String path = "/com/method/file/XML/digester/beanXml.xml" ;
		List<Object> list = d.xmlToObjectList(content, path) ;
		for(Object obj:list){
			Bean bean = (Bean)obj ;
			System.out.println(bean.getId()) ;
		}
	}

}
