package com.method.tag.table;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * made by dyong 
 * date 2009-5-4 ÏÂÎç01:51:41
 **/
public class TableParam extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String name ;
	private String title ;
	private String scope = "request";
	
	private ArrayList propertys ;
	
	public int doStartTag() throws JspException {
		Object obj = pageContext.getAttribute(Param.param) ;
		if(obj == null ){
			propertys = new ArrayList();
		} else {
			propertys = (ArrayList) obj;
		}
        return EVAL_BODY_INCLUDE;
    }
	
	public int doEndTag() throws JspException {
		TableParam p = new TableParam() ;
		p.setName(name) ;
		p.setTitle(title) ;
		propertys.add(p) ;
		pageContext.setAttribute(Param.param, propertys) ;
		return EVAL_PAGE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
