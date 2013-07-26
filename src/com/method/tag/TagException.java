package com.method.tag;

import javax.servlet.jsp.JspException;

/**
 * made by dyong 
 * date 2009-4-22 обнГ03:14:50
 **/
public class TagException extends JspException{

	private static final long serialVersionUID = 1L;

	public TagException(String msg){
        super(msg);
    }
}
