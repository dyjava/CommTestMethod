package com.method.index.analyzer.thesaurus;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class WordTreeFactory{
	private static WordTree instance = null;
	
	public static WordTree getInstance() throws IOException
	{
		if(instance == null)
		{
			System.out.println("词库尚未被初始化，开始初始化词库.");
			long begin = System.currentTimeMillis();
			instance = buildChineseWordTree();
			long end = System.currentTimeMillis();
			System.out.println("初始化词库结束。用时:"+(end - begin)+"毫秒;");
			System.out.println("共添加"+instance.getWordCount()+"个词语。");
		}
		return instance;
	}
	
	private static WordTree buildChineseWordTree() throws IOException
	{
		WordTree tree = WordTree.getInstance();
		
		String[] list = {"main.dic","dic/china_city.dic","dic/fly.dic","dic/idiom.dic","dic/star.dic"} ;
		for(int i=0;i<list.length;i++){
			String wordlist = list[i] ;
			LineNumberReader reader = new LineNumberReader(new InputStreamReader(WordTreeFactory.class.getResourceAsStream(wordlist),"UTF-8"));
			try {
				String line = null;
				while((line = reader.readLine())!=null) {
					if(line.indexOf("#")<0) {
						tree.addChineseWord(line);
					}
				}
			} finally {
				if(reader!=null){
					reader.close();
				}
			}
		}
		return tree;
	}
	
	public static void addChineseWordTree(String[] path) throws IOException {
		getInstance() ;
		
		if(path!=null && path.length>0)
		for(int i=0;i<path.length;i++){
			LineNumberReader reader = new LineNumberReader(new InputStreamReader(
					WordTreeFactory.class.getResourceAsStream(path[i]),"UTF-8"));
			try {
				String line = null;
				while((line = reader.readLine())!=null) {
					if(line.indexOf("#")<0 ) {
						instance.addChineseWord(line);
					}
				}
			} finally {
				if(reader!=null){
					reader.close();
				}
			}
		}
	}
}
