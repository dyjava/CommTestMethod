package com.method.index.complexSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;

import com.method.index.data.Postcode;

/** 
 * 读写索引同时进行
 * by dyong 2010-8-10
 */
public class AddIndexAndReadIndex {
	private static final Logger log = Logger.getLogger(AddIndexAndReadIndex.class);

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddIndexAndReadIndex main = new AddIndexAndReadIndex() ;
		main.new AddIndex().start() ;
		main.new ReadIndex().start() ;
		
	}
	

	class ReadIndex extends Thread{
		
		public void run(){
			while(true){
				List<Postcode> list = queryIndex("子位一村");
				for(Postcode p:list){
					log.error(p.getCity()+p.getArea()+p.getAddress()) ;
				}
				
				log.error("===========read==============") ;
				try {
					Thread.sleep(1000) ;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public List<Postcode> queryIndex(String key){
			List<Postcode> list = new ArrayList<Postcode>();
			Hits hits = null ;
			Searcher searcher = null ;
			try {
				searcher = IndexFactory.getInstence().getSearcher() ;
				Query query = QueryParser.parse(key,"key",new StandardAnalyzer());
				log.info("Query:"+query.toString());
				hits = searcher.search(query);
				
				// 提取结果并封装
			    for (int i = 0; i < hits.length(); i++) {
			    	Document doc = hits.doc(i);
			    	list.add(PostcodeCommon.documentToObject(doc)) ;
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list ;
		}
	}
	
	private static int n =0 ;
	class AddIndex extends Thread{
		public void run(){
			try {
				while(true){
					addRAM() ;
					log.error("===========add==============") ;
					Thread.sleep(1000) ;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void addRAM() throws IOException{
//			get data
			ArrayList<Postcode> list = new ArrayList<Postcode>() ;
			for(int i=0;i<3;i++){
				Postcode p = new Postcode() ;
				p.setId(""+i) ;
				p.setProvince("") ;
				p.setCity("") ;
				p.setArea("子位镇"+(n+i)) ;
				p.setAddress("子位一村"+(n+i)) ;
				p.setDnumber("") ;
				p.setPostcode("") ;
				list.add(p) ;
			}
			n++ ;
			
//			add index
			ArrayList<Document> l = new ArrayList<Document>() ;
			for(Postcode p:list){
				Document doc = PostcodeCommon.getDocument(p) ;
				l.add(doc) ;
			}
			IndexFactory.getInstence().addRAMIndex(l) ;
		}
		
	}

}
