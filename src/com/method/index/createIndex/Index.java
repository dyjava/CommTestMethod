package com.method.index.createIndex;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;

import com.method.index.Const;
import com.method.index.data.BaseIndexInterface;

public class Index {
	private static final Logger log = Logger.getLogger(Index.class);
	
	private static BaseIndexInterface bd ;
	public Index(){
		if(bd==null){
			Index.bd = Const.baseIndex ;
		}
	}
	
	public void addData(IndexWriter writer)
	throws Exception{
		//step1,取得数据
		List<Object> list = bd.getDataList() ;
		//step2,创建
		for(int i=0; i<list.size();i++){
			Object obj = list.get(i);
			Document doc = bd.getDocument(obj);
			writer.addDocument(doc);
			log.info("add data ("+obj.toString()+") to Index success!");
		}
	}
	
	/**
	 * 
	 * @param reader
	 * @param list
	 * @throws Exception
	 */
	public void deleteData(IndexReader reader)
	throws Exception{
		List<Object> list = bd.getDataList() ;
		for(int i=0;i<list.size();i++){
			Object obj = list.get(i);
			reader.deleteDocuments(bd.getDeleteTerm(obj));
			log.info("delete data ("+obj+") from Index success!");
		}
	}

	
}
