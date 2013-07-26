package com.method.regex;

import java.io.IOException;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.method.file.ReadWebFileMethod;

/**
 * made by dyong 
 * date 2009-9-7 01:55:46
 **/
public class XPath {
	public static HtmlCleaner cleaner = new HtmlCleaner();
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws XPatherException 
	 */
	public static void main(String[] args) throws IOException, XPatherException {
		String url = "http://novel.hongxiu.com/a/108132/1484163.shtml" ;
		String content = ReadWebFileMethod.readUrlContent(url,"gb2312") ;
			
		String s = xpath(content) ;
		System.out.println(s) ;
	}

	public static String xpath(String content) throws IOException, XPatherException{
		TagNode rootNode = cleaner.clean(content);
//		String ss = cleaner.getInnerHtml((TagNode)rootNode.evaluateXPath("//div[@id='htmlContent']")[0]) ;
//		return ss ;
		Object[] tns = rootNode.evaluateXPath("//div[@id='htmlContent']") ;
		String msg = cleaner.getInnerHtml((TagNode)tns[0]);
		return msg;
		
	}
}
