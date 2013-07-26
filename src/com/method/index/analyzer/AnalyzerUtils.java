package com.method.index.analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.lucene.analysis.*;

/**
 * @author Jolestar
 */
public class AnalyzerUtils
{
	public static Token[] tokensFromAnalysis(Analyzer analyzer, String text)
			throws IOException {
		TokenStream stream = analyzer.tokenStream("contents", new StringReader(
				text));
		ArrayList<Token> tokenList = new ArrayList<Token>();
		while (true) {
			Token token = stream.next();
			if (token == null)
				break;
			tokenList.add(token);
		}
		return (Token[]) tokenList.toArray(new Token[0]);
	}
	public static Token[] tokensFromAnalysis2(Analyzer analyzer, String text)
	throws IOException {
		ArrayList<Token> tokenList = tokens(analyzer,text,true) ;
		return (Token[]) tokenList.toArray(new Token[0]);
	}
	
	private static ArrayList<Token> tokens(Analyzer analyzer, String text,boolean f)throws IOException{
		TokenStream stream = analyzer.tokenStream("contents2", new StringReader(text));
		ArrayList<Token> tokenList = new ArrayList<Token>();
		while (true) {
			Token token = stream.next();
			if (token == null){
				break;
			}
			String s = token.termText() ;
			if(f){
				tokenList.add(token);
			} else if(text.length()>s.length() && s.length()>1){
				tokenList.add(token);
			}
			if(s.length()>2){
				tokenList.addAll(tokens(analyzer,s.substring(0,s.length()-1),false)) ;
				tokenList.addAll(tokens(analyzer,s.substring(1),false)) ;
			}
		}
		return tokenList ;
	}
	
}
