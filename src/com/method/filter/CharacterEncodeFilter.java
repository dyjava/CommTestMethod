package com.method.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * made by dyong 
 * date 2008-8-15 03:00:36
 **/
public class CharacterEncodeFilter implements Filter {

	private FilterConfig filterConfig;
	protected String encoding = null;
	protected boolean ignore = true;
	
	public void init(FilterConfig fconfig) throws ServletException {
		filterConfig = fconfig;
		encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("used");
		if (value == null || value.equalsIgnoreCase("true")||value.equalsIgnoreCase("yes")){
			this.ignore = true;
		}else {
			this.ignore = false;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {
		if (ignore || (request.getCharacterEncoding() == null)) {
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}
		filter.doFilter(request, response);
	}

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

}
