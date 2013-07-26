package com.method.index.readIndex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;

import com.method.index.Const;
import com.method.index.data.BaseIndexInterface;

public class ReadIndex {
	private static final Logger log = Logger.getLogger(ReadIndex.class);
	
	private static BaseIndexInterface index ;
	public ReadIndex(){
		if(index==null){
			index = Const.baseIndex ;
		}
	}
	
	public List<Object> queryIndex(String key){
		List<Object> list = new ArrayList<Object>();
		Hits hits = null ;
		Searcher searcher = null ;
		try {
			Sort sort = index.getSort() ;
			searcher = SearchIdx.getIndexSearcher() ;
			Query query = SearchIdx.getQuery(key);
			log.info("Query:"+query.toString());
			if(sort!=null){
				hits = searcher.search(query,sort);
			} else {
				hits = searcher.search(query);
			}
			
			// 提取结果并封装
		    for (int i = 0; i < hits.length(); i++) {
		    	Document doc = hits.doc(i);
		    	list.add(index.DocumentToObject(doc)) ;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(searcher != null){
				try {
					searcher.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list ;
	}
	

}
