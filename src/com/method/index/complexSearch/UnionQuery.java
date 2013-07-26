package com.method.index.complexSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.PhrasePrefixQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RangeQuery;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;

import com.method.index.data.Postcode;

/** 
 * by dyong 2010-8-11
 */
public class UnionQuery {
	private static final Logger log = Logger.getLogger(UnionQuery.class);
	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		String key = "北京市 昌平区回龙观" ;
		
//		模糊查询，二分法查询	>>key:"北 京 市" key:"昌 平 区 回 龙 观"
		Query query = QueryParser.parse(key,"key",new StandardAnalyzer());
		log.error("==>>"+query) ;
		
//		不分词查询，查询ID	>>id:656897
		query = new TermQuery(new Term("id","656897"));
		log.error("==>>"+query) ;
		
//		前缀查询	>>id:65689*
		query = new PrefixQuery(new Term("id","65689")) ;
		log.error("==>>"+query) ;
		
//		范围查询	>>id:{656797 TO 656897} id:[656797 TO 656897]
		query = new RangeQuery(new Term("id","656797"),new Term("id","656897"),true);
		log.error("==>>"+query) ;
		
//		通配符查询
		query = new WildcardQuery(new Term("id","656*94")) ;
		log.error("==>>"+query) ;
		
//		复合查询
		Query query1 = QueryParser.parse(key,"key",new StandardAnalyzer());
		PrefixQuery query2 = new PrefixQuery(new Term("id","65689"));
		BooleanQuery bquery = new BooleanQuery();
		bquery.add(query1,BooleanClause.Occur.MUST);
		bquery.add(query2,BooleanClause.Occur.MUST_NOT);
		query = bquery ;
		log.error("==>>"+query) ;
		
		Query q1 = QueryParser.parse("北京","key",new StandardAnalyzer());
		Query q2 = QueryParser.parse("昌平","key",new StandardAnalyzer());
		Query q3 = new TermQuery(new Term("id","2197926"));
		Query q4 = QueryParser.parse("上海","key",new StandardAnalyzer());
		q4.setBoost(1) ;
		q1.setBoost(10) ;
		bquery = new BooleanQuery();
		bquery.add(q1,BooleanClause.Occur.SHOULD);
//		bquery.add(q2,BooleanClause.Occur.MUST_NOT);
//		bquery.add(q3,BooleanClause.Occur.MUST);
		bquery.add(q4,BooleanClause.Occur.SHOULD);
		query = bquery ;
		log.error("==>>"+query) ;
		
//		
//		MultiPhraseQuery mquery = new MultiPhraseQuery() ;
//		mquery.add(new Term("key","北京")) ;
//		mquery.add(new Term("key","昌平")) ;
//		mquery.add(new Term("key","回龙观")) ;
//		query = mquery ;
//		log.error("==>>"+query) ;
//		
//		PhraseQuery pquery = new PhraseQuery();
//		pquery.setSlop(1);
//		//Add terms of the phrases.
//		pquery.add(new Term("key","北京市"));
//		pquery.add(new Term("key","海淀区"));
//		pquery.add(new Term("key","1004854"));
//		query = pquery ;
//		log.error("==>>"+query) ;
		
//		query = new FuzzyQuery(new Term("id","656897"),(float) 0.1);
//		log.error("==>>"+query) ;
		
		List<Postcode> list = queryIndex(query) ;
		for(Postcode p:list){
			log.error(p.getId()+p.getCity()+p.getArea()+p.getAddress()) ;
		}
	}

	public static List<Postcode> queryIndex(Query query) throws IOException{
		List<Postcode> list = new ArrayList<Postcode>();
		Hits hits = null ;
		Searcher searcher = IndexFactory.getInstence().getSearcher() ;
		hits = searcher.search(query);
		for (int i = 0; i < hits.length(); i++) {
		    Document doc = hits.doc(i);
		    list.add(PostcodeCommon.documentToObject(doc)) ;
		}
		return list ;
	}
	
}
