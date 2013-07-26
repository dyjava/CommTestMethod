package com.method.index.data;

import java.sql.SQLException;
import java.util.List;

import my.common.database.ibatis.city.City;
import my.common.database.ibatis.config.SqlMapConfig;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Sort;

import com.ibatis.sqlmap.client.SqlMapClient;


/** 
 * by dyong 2010-7-23
 */
public class ChinaCityImpl implements BaseIndexInterface {

	private static SqlMapClient sqlmapclient ;
	
	private static ChinaCityImpl china_city ;
	private ChinaCityImpl(){
		sqlmapclient = SqlMapConfig.getSqlMapClient() ;
	}
	public static ChinaCityImpl getInstance(){
		if(china_city==null){
			china_city = new ChinaCityImpl() ;
		}
		return china_city ;
	}
	public Object DocumentToObject(Document doc) {
		// TODO Auto-generated method stub
		City c = new City() ;
		c.setId(doc.get("id")) ;
		c.setCname(doc.get("cname")) ;
		c.setAlias(doc.get("alias")) ;
		c.setS_spell(doc.get("s_spell")) ;
		c.setA_spell(doc.get("a_spell")) ;
		c.setParent_id(doc.get("parent_id")) ;
		
		c.setInstact_name(doc.get("instact_name")) ;
		c.setInstact_spell(doc.get("instact_spell")) ;
		return c;
	}

	public List<Object> getDataList() {
		// TODO Auto-generated method stub
		try {
			return sqlmapclient.queryForList("selectAllChinaCity") ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Term getDeleteTerm(Object obj) {
		// TODO Auto-generated method stub
		return new Term("id",((City)obj).getId()) ;
	}

	public Document getDocument(Object obj) {
		// TODO Auto-generated method stub
		City c = (City)obj ;
		Document doc = new Document();
		doc.add(Field.Keyword("id", c.getId()));
		doc.add(Field.UnIndexed("cname", c.getCname()));
		doc.add(Field.UnIndexed("alias", c.getAlias()));
		doc.add(Field.UnIndexed("s_spell", c.getS_spell()));
		doc.add(Field.UnIndexed("a_spell", c.getA_spell()));
		doc.add(Field.Keyword("parent_id", c.getParent_id()));
		
		doc.add(Field.Text("instact_name", c.getInstact_name())) ;
		doc.add(Field.Text("instact_spell", c.getInstact_spell())) ;
		return doc;
	}

	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

}
