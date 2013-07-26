package com.method.tag.option;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.method.tag.*;


/**
 * made by dyong 
 * date 2009-4-23 下午04:07:23
 **/
public class OptionTag extends BodyTagSupport{

	private String root ;				//Name of the bean to be retrieved
	private String scope = "request";	//Scope to be searched (page, request, session, application)
	private String selectedId ;			//默认选中值
	private String name ;				//选择框ID

	private String css = "" ;			//选择框样式
	private String onchange = "" ;		//onchange动作
	private String noSelectOpt = "" ;	//添加未选择选项
	
	private String keyName = "key" ;	//其他存储对象的key名称
	private String valueName = "value" ;//value名称
	
	public int doStartTag() throws JspException {
		if(!Param.isLoad){
			Param.load() ;
		}
		return EVAL_BODY_INCLUDE;
    }
	
	public int doEndTag() throws JspException {
		TagUtil util = new TagUtil() ;
        Object obj = util.lookup(pageContext,root,scope);
        
        if (obj == null) {
            throw new TagException("page null");
        }
        
        StringBuffer sb = new StringBuffer() ;
        sb.append("<select id='"+name+"' "+css+" onchange='"+onchange+"'>\n") ;
        if(noSelectOpt.equalsIgnoreCase("true")){
            sb.append("<option value='"+Param.getNoSelectValue()+"'>"+Param.getNoSelectName()+"</option>\n") ;
        }
        if(obj instanceof Map){
        	sb.append(optionShow((Map)obj)) ;
        } else if(obj instanceof List){
        	sb.append(optionShow((List)obj)) ;
        } else {
        	sb.append(optionShow(obj)) ;
        }
        sb.append("</select>\n") ;
        
        util.write(pageContext ,sb.toString()) ;
        return EVAL_PAGE;
    }
	
	private String optionShow(Map map){
		StringBuffer sb = new StringBuffer() ;
    	Set set = map.keySet();
    	Iterator it = set.iterator();
    	while(it.hasNext()){
    		Object key = it.next();
    		if(map.containsKey(key)){
        		String value = (String) map.get(key);
        		sb.append("<option value='" + key + "'");
                if (selectedId != null && selectedId.length() > 0
                        && selectedId.equalsIgnoreCase(key.toString())){
                	sb.append(" selected");
                }
                sb.append(">");
                sb.append(value);
                sb.append("</option>\n");
    		}
    	}
		return sb.toString() ;
	}
//	private String optionShow(Hashtable hash){
//		StringBuffer sb = new StringBuffer() ;
//		Set set = hash.keySet();
//    	Iterator it = set.iterator();
//    	while(it.hasNext()){
//    		Object key = it.next();
//    		if(hash.containsKey(key)){
//        		String value = (String) hash.get(key);
//        		sb.append("<option value='" + key + "'");
//                if (selectedId != null && selectedId.length() > 0
//                        && selectedId.equalsIgnoreCase(key.toString())){
//                	sb.append(" selected");
//                }
//                sb.append(">");
//                sb.append(value);
//                sb.append("</option>\n");
//    		}
//    	}
//		return sb.toString() ;
//	}
	private String optionShow(Object obj){
		List list = (List)obj ;
    	StringBuffer sb = new StringBuffer();
    	String keyMethod = getMethodName(keyName);
    	String valueMethod = getMethodName(valueName);
    	for(int i=0;i<list.size();i++){
    		obj = list.get(i);
    		try {
    	           Method m0 = obj.getClass().getDeclaredMethod(keyMethod, null);
    	           Method m1 = obj.getClass().getDeclaredMethod(valueMethod, null);
    	           Object obj0 = m0.invoke(obj, null);
    	           Object obj1 = m1.invoke(obj, null);
    	           String key = obj0.toString();
    	           String value = obj1.toString();
    	           sb.append("<option value='" + key + "'");
    	           if (selectedId != null && selectedId.length() > 0 && selectedId.equalsIgnoreCase(key)){
    	        	   sb.append(" selected");
    	           }
    	           sb.append(">");
    	           sb.append(value);
    	           sb.append("</option>\n");
    	       } catch (Exception e) {
    	           e.printStackTrace();
    	       }
    	}
        return sb.toString();
	}
	private String getMethodName(String name) {
	    String str = "get";
	    str += name.substring(0, 1).toUpperCase();
	    str += name.substring(1);
	    return str;
	}
	
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getNoSelectOpt() {
		return noSelectOpt;
	}

	public void setNoSelectOpt(String noSelectOpt) {
		this.noSelectOpt = noSelectOpt;
	}

}
