package com.method.index.createIndex;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

import com.method.index.Const;

public class IndexFactory {
	private static final Logger log = Logger.getLogger(IndexFactory.class);
//	private static IndexWriter baseOwnerWriter=null;
	private static IndexWriter baseWriter=null;
	private static int baseWriterCount=0;
//	private static int baseOwnerWriterCount=0;
	private static boolean bDeleteIndex = false;
	
	
	public static IndexWriter getIndexWriter(){
		if(baseWriter==null){
			syncInit();
		}
		if(baseWriter!=null){
			baseWriterCount++;
		}
		return baseWriter;
	}
	
	private static synchronized void syncInit() {
		try {
			if(baseWriter==null){
				baseWriter = new IndexWriter(FSDirectory.getDirectory(Const.INDEX_DIR, false),
						new StandardAnalyzer(), false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("dbIndex:"+e.getMessage());
			baseWriter = null;
		}
	}

	
	public static void closeIndexWriter(){
		try {
			baseWriterCount--;
			if(baseWriter!=null&&baseWriterCount<=0){
				baseWriter.optimize();
				baseWriter.close();
				log.info("close baseDbWriter!");
				baseWriter = null;
			}else{
				log.info("baseWriter count plus 1!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("dbIndex:"+e.getMessage());
		}
	}
	
	public static boolean getDeleteHandle(){
		return baseWriterCount<=0?(bDeleteIndex=true):false;
	}
	
	public static void resetDeleteFlag(){
		bDeleteIndex = false;
	}
	
	public static boolean getDeleteFlag(){
		return bDeleteIndex;
	}
	
	public static void main(String args[]){
		boolean bret = IndexFactory.getDeleteFlag();
		System.out.println(bret);
		IndexFactory.resetDeleteFlag();
	}
	
}
