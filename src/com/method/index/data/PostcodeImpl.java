package com.method.index.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Sort;

import com.ibatis.sqlmap.client.SqlMapClient;

/** 
 * by dyong 2010-7-8
 */
public class PostcodeImpl implements BaseIndexInterface{

	private static SqlMapClient sqlmapclient ;
	public PostcodeImpl(SqlMapClient client) {
		sqlmapclient = client ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String ss = (new Date()).toLocaleString() ;
//		System.out.println(ss) ;
	}

	@SuppressWarnings("deprecation")
	public Document getDocument(Object obj) {
		// TODO Auto-generated method stub
		Postcode data = (Postcode) obj ;
		Document doc = new Document();
		
		String prov =data.getProvince().replaceAll("省","").replace("市","") ;
		if(prov.contains("自治区")){
			prov = prov.substring(0,2) ;
		}
		String city = data.getCity().replace("市", "") ;
		
		String key = data.getAddress() ;
		if(!key.contains(data.getArea())){
			key = data.getArea()+"#"+key ;
		}
		if(!key.contains(city)){
			key = city+"#"+key ;
		}
		if(!key.contains(prov)){
			key = prov+"#"+key ;
		}
		doc.add(Field.Text("key", key));
		doc.add(Field.Keyword("id", data.getId())) ;
		doc.add(Field.Keyword("prov", prov));
		doc.add(Field.Keyword("city", city));
		doc.add(Field.UnIndexed("area", data.getArea()));
		doc.add(Field.UnIndexed("addr", data.getAddress()));
		doc.add(Field.Keyword("dnum", data.getDnumber()));
		doc.add(Field.Keyword("post", data.getPostcode())) ;
		
		doc.add(Field.Keyword("UpdateTime",(new Date()).toLocaleString()));
		return doc;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getDataList() {
		// TODO Auto-generated method stub
		try {
			return sqlmapclient.queryForList("selectAll") ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Object>() ;
	}

	public Term getDeleteTerm(Object obj) {
		// TODO Auto-generated method stub
		Postcode data = (Postcode)obj ;
		return new Term("id",data.getId()) ;
	}

	public Object DocumentToObject(Document doc) {
		// TODO Auto-generated method stub
		Postcode post = new Postcode() ;
		post.setId(doc.get("id"));
		post.setProvince(doc.get("prov"));
    	post.setCity(doc.get("city")) ;
    	post.setArea(doc.get("area")) ;
    	post.setAddress(doc.get("addr")) ;
    	post.setDnumber(doc.get("dnum")) ;
    	post.setPostcode(doc.get("post")) ;
		return post;
	}

	public Sort getSort() {
		// TODO Auto-generated method stub
		Sort sort = new Sort("UpdateTime",true);
		return null ;
	}
	

}
