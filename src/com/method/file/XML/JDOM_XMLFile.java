package com.method.file.XML;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;

public class JDOM_XMLFile {
	
	public static void jdomXml(String filepath)
	{
		try {
			String path = DOM_XMLFile.class.getResource(filepath).getPath() ;
			List allChildren = jdomXMLsrc(path); 
			for(int i=0;i<allChildren.size();i++){
			System.out.print("==:" + ((Element)allChildren.get(i)).getChild("starName").getText()); 
			System.out.println("---:" + ((Element)allChildren.get(i)).getChild("ID").getText()); 
			}
		}catch (Exception e){
			e.printStackTrace() ;
		}

	}
	
	private static List jdomXMLsrc(String filepath)
		throws JDOMException, IOException{
			SAXBuilder builder = new SAXBuilder(); 
			Document doc = builder.build(new File(filepath));
			Element foo = doc.getRootElement(); 
			return  foo.getChildren(); 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		jdomXml("/singer.xml");

	}

}
