package com.method.file.XML;

import java.util.List;
import java.util.ArrayList;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

import com.method.file.ReadLocalFileMethod;


/**
 * @author dyong
 * XML ����������
 */
public class CommResolMethod{

	/**
	 * ƥ��������ʽ������ƥ����б?
	 * @param file	Դ�ַ�
	 * @param math	������ʽ
	 * @param i	����list���� 0 ���ذ���ͨ���ޣ�1���ز�����ͨ����
	 * @return
	 * @throws Exception
	 */
	public static List resolXML(String file,String math,int i)
		throws Exception{
		List list = new ArrayList() ;
		
		PatternMatcher match = new Perl5Matcher();
		PatternMatcherInput input = new PatternMatcherInput(file);
		Pattern pattern = null ;
		try {
			pattern = ((PatternCompiler)new Perl5Compiler()).compile(math);
		} catch (MalformedPatternException e) {
			throw new Exception("err:"+e.getMessage()) ;
		}
		while(match.contains(input,pattern)){
			MatchResult mr = match.getMatch();
			list.add(mr.group(i)) ;
		}
		return list ;
	}
	
	public static List resolXML(String file,String math)
		throws Exception{
		return resolXML(file,math,1) ;
	}
	
	/**
	 * ��һ�ֽ���XML�ļ��ķ�����
	 * @param content
	 * @param tag
	 * @return parseTag0���ؽ������ĵ�һ��Ԫ�أ�parseTag���ؽ��������顣
	 */
	public static String parseTag0(String content, String tag)
	{
		return parseTag(content,tag)[0] ;
	}
	
	public static String[] parseTag(String content, String tag)
	{
		final String _TAG_START = "<" + tag + ">";
		final String _TAG_END = "</" + tag + ">";

		ArrayList results = new ArrayList();

		int start = content.indexOf(_TAG_START);
		int end = 0;
		while (start >= 0) {
			end = content.indexOf(_TAG_END, start);
			if (end < start) {
				break;
			}
			String item = content.substring(start + _TAG_START.length(), end);
			start = content.indexOf(_TAG_START, end);

			results.add(item);
		}
		return (String[]) results.toArray(new String[0]);
	}
	
	public static void main(String[] args){
		try{
			String file = ReadLocalFileMethod.readLocalFile2("/com/method/file/XML/singer.xml") ;
//			System.out.println(file) ;
			
//			String match = "<record>(.*?)</record>" ;
//			List list = resolXML(file,match) ;
//			for(int i=0;i<list.size();i++){
//				System.out.println(list.get(i)) ;
//			}
			
			String[] items = parseTag(file,"record") ;
			for(int i=0;i<items.length;i++){
				System.out.println(items[i]) ;
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage()) ;
		}
	}

}
