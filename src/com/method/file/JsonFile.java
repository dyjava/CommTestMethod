package com.method.file;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Userq u = new Userq() ;
		u.setName("beijing") ;
		u.setAaa(18) ;
//		String json = JsonFile.obj2json(u) ;
//		System.out.println(json.toString()) ;
//		User u2 = JsonFile.json2Obj(json, User.class) ;
//		System.out.println(u2.getName()+"=="+u2.getAge()) ;
		
		List<Userq> list = new ArrayList<Userq>() ;
		list.add(u) ;
		Userq u2 = new Userq() ;
		u2.setName("beijing") ;
		u2.setAaa(18) ;
		list.add(u2) ;
		String json = JsonFile.obj2json(list) ;
		System.out.println(json.toString()) ;
		
//		List list2 = JsonFile.json2Obj(json, List.class) ;
//		for(int i=0;i<list2.size();i++){
//			System.out.println(list2.get(i)) ;
//		}
	}

	public static String obj2json(Object obj){
		if(obj instanceof List){
			JSONArray json = JSONArray.fromObject(obj) ;
			return "{"+json.toString()+"}" ;
		} else {
			JSONObject json = JSONObject.fromObject(obj) ;
			return "{"+json.toString()+"}" ;
		}
	}
	
	public static <T> T json2Obj(String json,Class<T> clazz){
		JSONObject jsonObject = JSONObject.fromObject(json.toString()); 
		return (T) JSONObject.toBean(jsonObject,clazz) ;
	}
	
	
}
class Userq{
	private String name ;
	private int aaa ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAaa() {
		return aaa;
	}
	public void setAaa(int aaa) {
		this.aaa = aaa;
	}
	
}
