package com.method.tag.page;

import java.util.Iterator;
import java.util.Map;

/**
 * made by dyong 
 * date 2009-4-23 ����09:42:20
 **/
public class TagParam {

	private String url = "" ;		//URL��ַ
	
	private int pageNo = 0 ;		//��ǰҳ��
	private int allNo = 0 ;			//��ҳ��
	private String pageNoName = "" ;//ҳ������
	
	private Map params = null ;		//��������
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
	
//	��������ʽ�Ĳ�������
	public String getAllLinkParams() {
		return linkParams ;
	}
	
//	form��ʽ�Ĳ�������
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
	 * ��������
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
