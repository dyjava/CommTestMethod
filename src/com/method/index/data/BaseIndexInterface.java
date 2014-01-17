package com.method.index.data;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Sort;

/** 
 * by dyong 2010-7-8
 */
public interface BaseIndexInterface {

//	取数据
	public List<Object> getDataList() ;
	
//	object to Document
	public Document getDocument(Object obj) ;
	
//	删除
	public Term getDeleteTerm(Object obj) ;
	
//	Document to object
	public Object DocumentToObject(Document doc) ;
	
//	查询排序
	public Sort getSort() ;
}
