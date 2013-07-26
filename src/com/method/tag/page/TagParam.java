package com.method.tag.page;

import java.util.Iterator;
import java.util.Map;

/**
 * made by dyong 
 * date 2009-4-23 上午09:42:20
 **/
public class TagParam {

	private String url = "" ;		//URL地址
	
	private int pageNo = 0 ;		//当前页号
	private int allNo = 0 ;			//总页数
	private String pageNoName = "" ;//页号名称
	
	private Map params = null ;		//其他参数
	private String linkParams = "" ;
	private String formParams = "" ;
	
	public int getAllNo() {
		return allNo;
	}

	public void setAllNo(int allNo) {
		this.allNo = allNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageNoName() {
		return pageNoName;
	}

	public void setPageNoName(String pageNoName) {
		this.pageNoName = pageNoName;
	}

	public Map getParams() {
		return params;
	}
	
//	超链接形式的参数设置
	public String getAllLinkParams() {
		return linkParams ;
	}
	
//	form表单式的参数设置
	public String getAllFormParams() {
		return formParams ;
	}

	public String getURL(){
		if(url.indexOf("?")>=0){
			return url + "&amp;"+pageNoName+"=" ;
		} else {
			return url + "?"+pageNoName+"=" ;
		}
	}
	
	/**
	 * 参数处理
	 * @param params
	 */
	public void setParams(Map params) {
		this.params = params;

		StringBuffer sb1 = new StringBuffer() ;
		StringBuffer sb2 = new StringBuffer() ;
		if(params!=null && params.size()>0){
			Iterator ite = params.keySet().iterator() ;
			while(ite!=null && ite.hasNext()){
				String key = (String)ite.next() ;
				String value = (String) params.get(key) ;
	
				sb1.append("&amp;"+key+"="+value) ;
				sb2.append("<input type=\"hidden\" name=\""+key+"\" value=\""+value+"\">") ;
			}
			linkParams = sb1.toString() ;
			formParams = sb2.toString() ;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
