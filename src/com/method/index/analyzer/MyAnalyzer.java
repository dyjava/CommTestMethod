package com.method.index.analyzer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;

import com.method.index.Const;
import com.method.index.analyzer.lucene.ThesaurusAnalyzer;
import com.method.index.analyzer.thesaurus.WordTreeFactory;

/** 
 * by dyong 2010-7-30
 */
public class MyAnalyzer {
	
	private static Analyzer[] analyzers = { new ThesaurusAnalyzer(),
			new StandardAnalyzer()};

	public static void analyzerTest(String s) {
		try {
			for (Analyzer analyzer : analyzers){
				Token[] tokens = AnalyzerUtils.tokensFromAnalysis(analyzer,s);
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < tokens.length; i++) {
					buffer.append("[");
					buffer.append(tokens[i].termText());
					buffer.append("] ");
				}
				String name = analyzer.getClass().getSimpleName();
				
				System.out.println(name+"	"+buffer.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		String[] path = {"/com/method/index/analyzer/w.txt"} ;
		WordTreeFactory.addChineseWordTree(path) ;
		
		long a = System.currentTimeMillis() ;
		String str = "2009年钟山职业技术学院怎么样?" ;
		str = "北京天安到广东的火车。中华人民共和国。阿克塞哈萨克族自治县，阿图什市。明星分手戏堪比肥皂剧出轨炒作上法庭一个不少 。2010-08-02,蔡依林批评狗仔骚扰奶奶称孝顺不需要别人, 盘点好莱坞女星的个人香水：不可抗拒的明星味" ;
//		str = "类别:国内机票描述:杨浦订票电话 杨浦区上海航空特价机票预定订杨浦机票电话.." ;
//		str = "北京科技大学学生劫持人质抢劫银行获刑10年" ;
//		analyzerTest(str);
		long b = System.currentTimeMillis() ;
		System.out.println(a-b) ;
		
		Query q = QueryParser.parse(str, "key",new ThesaurusAnalyzer());
		System.out.println(q.toString()) ;
		long c = System.currentTimeMillis() ;
		System.out.println(c-b) ;
	}

}
