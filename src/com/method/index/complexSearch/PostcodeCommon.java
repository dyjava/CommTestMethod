package com.method.index.complexSearch;

import java.util.Date;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.method.index.data.Postcode;


/** 
 * by dyong 2010-8-10
 */
public class PostcodeCommon{
	
	
	public static Postcode documentToObject(Document doc) {
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
	
 	public static Document getDocument(Postcode data) {
		Document doc = new Document();
		
		String prov =data.getProvince() ;
		String city = data.getCity() ;
		
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
}
