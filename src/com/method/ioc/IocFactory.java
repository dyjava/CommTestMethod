package com.method.ioc;

/**
 * made by dyong 
 * date 2009-12-29 09:55:27
 * 
 * Java反射示例
 **/
public class IocFactory {

	@SuppressWarnings("unchecked")
	public Object ioc(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class c = Class.forName(className) ;
//		String name = c.getSimpleName() ;
		Object obj = c.newInstance() ;
		return obj ;
	}
	
	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		IocFactory iof = new IocFactory() ;
		String c = "com.method.ioc.imp.QidianIoc" ;
		c = "com.method.ioc.imp.FeikuIoc" ;
		IocInterface ii = (IocInterface) iof.ioc(c) ;
		ii.out() ;
	}

}
