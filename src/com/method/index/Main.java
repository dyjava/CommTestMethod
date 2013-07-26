package com.method.index;

import java.util.List;

import my.common.database.ibatis.config.SqlMapConfig;


import com.method.index.createIndex.CreateIndex;
import com.method.index.data.ChinaCityImpl;
import com.method.index.data.PostcodeImpl;
import com.method.index.readIndex.ReadIndex;
import com.method.index.readIndex.SearchIdx;

/** 
 * by dyong 2010-7-8
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		postcode() ;
		
		chinaCity() ;
	}
	
	private static void chinaCity() {
		// TODO Auto-generated method stub
		Const.baseIndex = ChinaCityImpl.getInstance() ;
		Const.INDEX_DIR = "E:/data/chinaCity" ;
//		CreateIndex.deleteIndex();
//		CreateIndex.getIndex();
		
		Const.INDEX_SEARCH_KEY="parent_id" ;
		Const.analyzer = new org.apache.lucene.analysis.KeywordAnalyzer() ;
//		SearchIdx.indexMemoryAll() ;
		List list = new ReadIndex().queryIndex("10000000") ;
//		
		list.get(0) ;
	}

	private static void postcode(){

		Const.baseIndex = new PostcodeImpl(SqlMapConfig.getSqlMapClient()) ;
		Const.INDEX_DIR = "E:/index/test2" ;
//		CreateIndex.deleteIndex();
//		CreateIndex.getIndex();
		
		Const.INDEX_SEARCH_KEY="key" ;
//		SearchIdx.indexMemoryAll() ;
		List list = new ReadIndex().queryIndex("朝阳区 大柳树") ;
		
		list.get(0) ;
	}

}
