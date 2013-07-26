package com.method.tag.page;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.method.tag.*;


/**
 * made by dyong 
 * date 2009-4-22 下午04:07:23
 **/
public class PageTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String root ;				//Name of the bean to be retrieved
	private String scope = "request";	//Scope to be searched (page, request, session, application)
	
	private String type = "0" ;			//翻页类型 0先文字翻页后数字翻页,1先数字翻页后文字翻页,2只有数字翻页,3只有文字翻页
	private String method = "link" ;	//提交类型
	
	private TagParam tagParam ;			//翻页相关数据对象
	
	public int doStartTag() throws JspException {
		if(!Param.isLoad){
			Param.load() ;
		}
        return EVAL_BODY_INCLUDE;
    }
	
	public int doEndTag() throws JspException {
		TagUtil util = new TagUtil() ;
        Object obj = util.lookup(pageContext,root,scope);
//        System.out.println("=========>>") ;
        if (obj == null) {
            throw new TagException("page null");
        }
        
//      取得参数
        getParem(obj);
        
//      拼装输出
        StringBuffer sb = new StringBuffer() ;
        if(type.equals("0")){
            sb.append(charPageLink()+"\n") ;
        	sb.append(numPageLink()+"\n") ;
        } else if(type.equals("1")){
        	sb.append(numPageLink()+"\n") ;
            sb.append(charPageLink()+"\n") ;
        } else if(type.equals("2")){
        	sb.append(numPageLink()+"\n") ;
        } else if(type.equals("3")){
            sb.append(charPageLink()+"\n") ;
        }
        
//      输出到页面
        util.write(pageContext ,sb.toString()) ;
//        pageContext.getRequest();// 获取JSP页面的请求对象 request
//        pageContext.getSession();// 获取JSP页面的会话对象 session
//        pageContext.getServletContext();// 获取JSP页面的应用对象 application [Page]
        return EVAL_PAGE;
    }
	
    /**
     * 取参数
     * @param list
     * @throws PageTagException
     */
    private void getParem(Object obj) throws TagException {
		tagParam = (TagParam)obj ;
			
//			String types =getParemValue(obj,type);
//			log.debug(count);
//			tag.setTotlePageNo(Integer.parseInt(count));
//			
//			String pageno = getParemValue(list,pno);
//			log.debug(pageno);
//			tag.setPageNo(Integer.parseInt(pageno)) ;
//			String typ = getParemValue(obj,type);
//			if(this.getLink_pre()!=null){
//				strLink = this.getLink_pre()+strLink ;
//			}
//			tag.setStrLink(strLink);
//			log.debug(strLink);
	}


    /**
     * 数字翻页
     * @return
     */
	private String numPageLink() {
		
		StringBuffer sb = new StringBuffer();
		int PageNo = tagParam.getPageNo() ;
		int PageCount = tagParam.getAllNo() ;
		String link = tagParam.getURL() ;
		String linkParam = tagParam.getAllLinkParams() ;
		String pnoName = tagParam.getPageNoName() ;
		
		int SIZE = Param.getNum();
		String pre = Param.getPre();
		String next = Param.getNext();
		String point = Param.getNumSepa();
		
//		loopStart
		int loopStart = PageNo-(SIZE-1)/2;
		if(loopStart+SIZE > PageCount){
			loopStart = PageCount-SIZE;
		}
		if(loopStart<0){
			loopStart =0;
		}
		if(loopStart<1){
		    pre = "";
		}
//		loopEnd
		int loopEnd = loopStart+SIZE;
		if(loopStart+SIZE >= PageCount) {
		    loopEnd = PageCount;
		    next = "";
		}
		
//		form表单提交
		if(method.equalsIgnoreCase("get")||method.equalsIgnoreCase("post")){
			String form = "pform" ;
			sb.append("<form name=\""+form+"\" action=\""+link+"\" method=\""+method+"\">") ;
			sb.append(tagParam.getAllFormParams()) ;
			sb.append("<input type='hidden' name='"+pnoName+"' value='"+PageNo+"'/>") ;
			if(pre.length() > 0){
			    sb.append("<input type='button' value='"+pre+"' onclick='javascript:page_go("+(PageNo-1)+");' />");
			    sb.append(point);
			} 
			for(int p = loopStart; p < loopEnd; p++){
			    if(PageCount<=1){
			    	break;
		    }
			    if(p == (PageNo)) {
			    	sb.append(p+1);
			    }else{
			    	sb.append("<input type='button' value='"+(p+1)+"' onclick='javascript:page_go("+p+");' />");
			    }
			    if ((loopEnd-1 != p) || (next.length() > 0)){
			    	sb.append(point);
			    }
			}
			if(next.length() > 0){
				sb.append("<input type='button' value='"+next+"' onclick='javascript:page_go("+(PageNo+1)+");' />");
			}
//			if(PageCount>1){
//				sb.append("<br/>");
//			}
			sb.append("</form>\n") ;
			sb.append(writeScript(form)) ;
		}else{
//		超链接形式
			if(pre.length() > 0){
			    sb.append("<a href='"+link+(PageNo-1)+linkParam+ "'/>"+pre+"</a>"+point);
			} 
			for(int p = loopStart; p < loopEnd; p++){
			    if(PageCount<=1){
			    	break;
			    }
			    if(p == (PageNo)) {
			    	sb.append(p+1+"");
			    }else{
			    	sb.append("<a href='"+link+p+linkParam+ "'>"+(p+1)+"</a>");
			    }
			    if ((loopEnd-1 != p) || (next.length() > 0)){
			    	sb.append(point);
			    }
			}
			if(next.length() > 0){
				sb.append("<a href='"+link+(PageNo+1)+linkParam+ "'>"+next+"</a>");
			}
			if(PageCount>1){
				sb.append("<br/>\n");
			}
		}
		return sb.toString();
	}
    
	/**
	 * 文字翻页
	 * @return
	 */
	private String charPageLink() {
		StringBuffer sb = new StringBuffer();
		int PageNo = tagParam.getPageNo() ;
		int PageCount = tagParam.getAllNo() ;
		String link = tagParam.getURL() ;
		String linkParam = tagParam.getAllLinkParams() ;
		String pnoName = tagParam.getPageNoName() ;
		
		String pre = Param.getPrePage() ;
		String next = Param.getNextPage() ;
		String first = Param.getFistPage() ;
		String last = Param.getLastPage() ;
		String point = Param.getWordSepa();
		
//		form表单提交
		if(method.equalsIgnoreCase("get")||method.equalsIgnoreCase("post")){
			String form = "pform2" ;
			sb.append("<form name=\""+form+"\" action=\""+link+"\" method=\""+method+"\">") ;
			sb.append(tagParam.getAllFormParams()) ;
			sb.append("<input type='hidden' name='"+pnoName+"' value='"+PageNo+"'/>") ;
			
			if(PageCount>1 && (PageNo+1)<PageCount){
				sb.append("<input type='button' value='"+next+"' onclick='javascript:page_go("+(PageNo+1)+");' />");
			}
			if(PageNo>0){
				if((PageNo+1)<PageCount){
					sb.append(point);
				}
				sb.append("<input type='button' value='"+pre+"' onclick='javascript:page_go("+(PageNo-1)+");' />");
			}
			if(PageNo>2){
				sb.append(point);
				sb.append("<input type='button' value='"+first+"' onclick='javascript:page_go(0);' />");
			}
			if(PageCount>3 && PageNo<PageCount-1){
				sb.append(point);
				sb.append("<input type='button' value='"+last+"' onclick='javascript:page_go("+(PageCount-1)+");' />");
			}
			sb.append("</form>") ;
			sb.append(writeScript(form)) ;
		} else {
//		超链接形式
			if(PageCount>1 && (PageNo+1)<PageCount){
				sb.append("<a href='"+link+(PageNo+1)+linkParam+ "'>"+next+"</a>");
			}
			if(PageNo>0){
				if((PageNo+1)<PageCount){
					sb.append(point);
				}
				sb.append("<a href='"+link+(PageNo-1)+linkParam+ "'>"+pre+"</a>");
			}
			if(PageNo>2){
				sb.append(point);
				sb.append("<a href='"+link+0+linkParam+ "'>"+first+"</a>");
			}
			if(PageCount>3 && PageNo<PageCount-1){
				sb.append(point);
				sb.append("<a href='"+link+(PageCount-1)+linkParam+ "'>"+last+"</a>");
			}
			if (PageCount>1){
				sb.append("<br/>");
			}
		}
		return sb.toString();
	}
    
	/**
	 * 输出js
	 * @param form
	 * @return
	 */
	private String writeScript(String form){
		StringBuffer sb = new StringBuffer() ;
		sb.append("<script>") ;
		sb.append("	function page_go(pg){")
		.append("		"+form+"."+tagParam.getPageNoName()+".value=pg ;")
		.append("		"+form+".submit();")
		.append("	}") ;
		
		sb.append("</script>") ;
		return sb.toString() ;
	}
	
    public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
