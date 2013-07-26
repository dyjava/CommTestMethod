package com.method.tag;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * made by dyong 
 * date 2009-4-23 上午10:30:49
 **/
public class TagUtil {

    private static final Map scopes = new HashMap();

    public static String CUT = "\n" ;
    static {
        scopes.put("page", new Integer(PageContext.PAGE_SCOPE));
        scopes.put("request", new Integer(PageContext.REQUEST_SCOPE));
        scopes.put("session", new Integer(PageContext.SESSION_SCOPE));
        scopes.put("application", new Integer(PageContext.APPLICATION_SCOPE));
    }
    
    /**
     * 取参数
     * @param pageContext
     * @param name
     * @param scopeName
     * @return
     * @throws JspException
     */
    public Object lookup(PageContext pageContext, String name, String scopeName)
    throws JspException {
    	if (scopeName == null) {
    		return pageContext.findAttribute(name);
    	}

    	try {
    		return pageContext.getAttribute(name, this.getScope(scopeName));
    	} catch (JspException e) {
//    		saveException(pageContext, e);
    		throw e;
    	}
    }
    
    private int getScope(String scopeName) throws JspException {
        Integer scope = (Integer) scopes.get(scopeName.toLowerCase());

        if (scope == null) {
            throw new JspException("lookup.scope null");
        }

        return scope.intValue();
    }
    
    /**
     * 取得对象属性值
     * @param obj
     * @param attribute
     * 
     */
	public Object getAttr(Object obj,String attribute) 
	throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Class c = obj.getClass() ;
		Field field = c.getField(attribute);
		field.setAccessible(true);
		return field.get(obj);
	}
    

	/**
	 *  执行对象方法
	 * @param obj 对象
	 * @param methodName 方法名
	 * @param clas 参数类型
	 * @param params 参数值
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Object executeMethod(Object obj,String methodName,Class[] clas,Object[] params)
	throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Class objClass = obj.getClass() ;
		Method method = objClass.getDeclaredMethod(methodName, clas);
		return method.invoke(obj, params) ;
	}
	
	/**
	 * 执行对象方法
	 * @param obj 对象
	 * @param methodName 方法名
	 * @param params 参数列表
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Object executeMethod(Object obj,String methodName,Object[] params)
	throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Class[] paramClass = null ;
		if(params!=null && params.length>0){
			paramClass = new Class[params.length] ;
//			Object[] paramValue = new Object[params.length] ;
			for(int i=0;i<params.length;i++){
				Object param = params[i] ;
				Class clas = param.getClass() ;
//				Object value = param ;
//				System.out.println(clas +"==="+ value) ;
				if(clas.isAssignableFrom(Integer.class)){			//Integer
					paramClass[i] = int.class ;
				} else if(clas.isAssignableFrom(Boolean.class)){	//Boolean
					paramClass[i] = boolean.class ;
				} else if(clas.isAssignableFrom(Double.class)){		//Double
					paramClass[i] = double.class ;
				} else if(clas.isAssignableFrom(Character.class)){	//Character
					paramClass[i] = char.class ;
				}  else if(clas.isAssignableFrom(Byte.class)){		//Byte
					paramClass[i] = byte.class ;
				} else {
					paramClass[i] = clas ;
				}
//				paramValue[i] = value ;
			}
		}
		return executeMethod(obj,methodName,paramClass,params) ;
	}
	
	public Object getMethod(Object obj,String property)
	throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		String methodName = getMethodName(property) ;
		return executeMethod(obj,methodName) ;
	}
	private Object executeMethod(Object obj,String methodName)
	throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		return executeMethod(obj,methodName,null) ;
	}
	private String getMethodName(String name) {
	    String str = "get";
	    str += name.substring(0, 1).toUpperCase();
	    str += name.substring(1);
	    return str;
	}
	
    /**
     * 输出到页面
     * @param pageContext
     * @param text
     * @throws JspException
     */
    public void write(PageContext pageContext, String text)
    throws JspException {
    	JspWriter writer = pageContext.getOut();
    	try {
    		writer.print(text);
    	} catch (IOException e) {
    		throw new JspException
            ("write.io error");
    	}

    }
}
