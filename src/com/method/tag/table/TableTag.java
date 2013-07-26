package com.method.tag.table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.method.tag.TagException;
import com.method.tag.TagUtil;

/**
 * made by dyong 
 * date 2009-4-29 下午03:13:02
 **/
public class TableTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String name ;				//Name of the bean to be retrieved
	private String scope = "request";	//Scope to be searched (page, request, session, application)

	public String border ;
	public String cellspacing ;
	public String cellpadding ;
//	title
	public String align ;
	public String valign ;
	public String bgcolor_title ;
//	data
	public String bgcolor_data ;
//	private String property ;
	private ArrayList property = new ArrayList();
	
	public int doStartTag() throws TagException {
		if(!Param.isLoad){
			Param.load() ;
		}
		getParam() ;
		property = new ArrayList() ;
        return EVAL_BODY_INCLUDE;
    }

	public int doEndTag() throws JspException {
		TagUtil util = new TagUtil() ;
        Object obj = util.lookup(pageContext,name,scope);
        if (obj == null) {
            throw new TagException("Object is null");
        }
        if(!(obj instanceof List)) {
        	throw new TagException("Object is not List");
        }
        List list = (List)obj ;
        
        property = (ArrayList) pageContext.getAttribute(Param.param) ;
        if(property==null){
        	property = new ArrayList() ;
        	Field[] filed = list.get(0).getClass().getDeclaredFields() ;
//        	System.out.println("===>>==="+filed.length) ;
        	for(int i=0;i<filed.length;i++){
        		TableParam tp = new TableParam() ;
        		tp.setName(filed[i].getName()) ;
        		tp.setTitle(filed[i].getName()) ;
        		property.add(tp) ;
//        		System.out.println("======"+filed[i].getName()) ;
        	}
        }
        
//      输出到页面
        try {
			util.write(pageContext ,showTable(list)) ;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        return EVAL_PAGE;
    }
	
	/**
	 * 输出table代码
	 * @param obj
	 * @return
	 * @throws TagException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String showTable(List list)
	throws TagException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		StringBuffer sb = new StringBuffer() ;
		sb.append("<table") ;
		sb.append(tableStyle()) ;
		sb.append(" >") ;
		sb.append(TagUtil.CUT) ;
		if(true){
			sb.append(getTitle()) ;
		}
		
		for(int i=0;i<list.size();i++){
			sb.append("<tr") ;
			sb.append(dataStyle()) ;
			sb.append(" >") ;
			sb.append(TagUtil.CUT) ;
			Object bean = list.get(i) ;
			TagUtil util = new TagUtil() ;
			for(int j=0;j<property.size();j++){
				sb.append("<td>");
				TableParam pro = (TableParam) property.get(j) ;
				String item = String.valueOf(util.getMethod(bean, pro.getName())) ;
				sb.append(item) ;
				sb.append("</td>").append(TagUtil.CUT) ;
			}
			sb.append("</tr>").append(TagUtil.CUT) ;
		}
		sb.append("</table>") ;
		return sb.toString() ;
	}
	
	/**
	 * 标题显示
	 * @return
	 */
	private String getTitle(){
		StringBuffer sb = new StringBuffer() ;
		sb.append("<tr") ;
		sb.append(titleStyle()) ;
		sb.append(" >") ;
		sb.append(TagUtil.CUT) ;
		for(int j=0;j<property.size();j++){
			sb.append("<td>");
			TableParam pro = (TableParam) property.get(j) ;
			sb.append(pro.getTitle()) ;
			sb.append("</td>").append(TagUtil.CUT);
		}
		sb.append("</tr>").append(TagUtil.CUT) ;
		return sb.toString() ;
	}
	
	/**
	 * table样式
	 * @return
	 */
	private String tableStyle(){
		StringBuffer sb = new StringBuffer() ;
		sb.append(" border='"+border+"'") ;
		sb.append(" cellspacing='"+cellspacing+"'") ;
		sb.append(" cellpadding='"+cellpadding+"'") ;
		return sb.toString() ;
	}
	
	/**
	 * 标题样式
	 * @return
	 */
	private String titleStyle(){
		StringBuffer sb = new StringBuffer() ;
		sb.append(" align='"+align+"'");
		sb.append(" valign='"+valign+"'") ;
		sb.append(" bgcolor='"+bgcolor_title+"'") ;
		return sb.toString() ;
	}
	
	/**
	 * 数据样式
	 * @return
	 */
	private String dataStyle(){
		StringBuffer sb = new StringBuffer() ;
		sb.append(" align='"+align+"'");
		sb.append(" valign='"+valign+"'") ;
		sb.append(" bgcolor='"+bgcolor_data+"'") ;
		return sb.toString() ;
	}
	
	private void getParam() {
		if(border==null){
			border = Param.border ;
		}
		if(cellspacing==null){
			cellspacing = Param.cellspacing ;
		}
		if(cellpadding==null){
			cellpadding = Param.cellpadding ;
		}
//		title
		if(align==null){
			align = Param.align_title ;
		}
		if(valign==null){
			valign = Param.valign_title ;
		}
		if(bgcolor_title==null){
			bgcolor_title = Param.bgcolor_title ;
		}
//		data
		if(bgcolor_data==null){
			bgcolor_data = Param.bgcolor_data ;
		}
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

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getBgcolor_data() {
		return bgcolor_data;
	}

	public void setBgcolor_data(String bgcolor_data) {
		this.bgcolor_data = bgcolor_data;
	}

	public String getBgcolor_title() {
		return bgcolor_title;
	}

	public void setBgcolor_title(String bgcolor_title) {
		this.bgcolor_title = bgcolor_title;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getCellpadding() {
		return cellpadding;
	}

	public void setCellpadding(String cellpadding) {
		this.cellpadding = cellpadding;
	}

	public String getCellspacing() {
		return cellspacing;
	}

	public void setCellspacing(String cellspacing) {
		this.cellspacing = cellspacing;
	}

	public String getValign() {
		return valign;
	}

	public void setValign(String valign) {
		this.valign = valign;
	}
}
