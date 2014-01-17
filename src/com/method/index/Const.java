package com.method.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import com.method.index.data.BaseIndexInterface;

public class Const {
	public static String INDEX_DIR = "D:/index/test";		//索引存放的位置
	public static BaseIndexInterface baseIndex ;			//索引数据来源
	
	public static String srcPath = "D:/index/test/t.txt";	//建索引的数据文件
	
	public static String outFile = "";		//备份文件
	
	public static boolean INDEX_IN_MEMORY = false ;

	public static String INDEX_SEARCH_KEY = "key";
	
	public static Analyzer analyzer = new StandardAnalyzer() ;
}
