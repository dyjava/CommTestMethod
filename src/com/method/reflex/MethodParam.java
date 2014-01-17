package com.method.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * made by dyong 
 * date 2009-4-27 下午05:50:08
 **/
public class MethodParam {

	public static void main(String args[]) 
	throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		
//		getAttr()
		Bean b = new Bean() ;
		b.setLen(100) ;
		b.setStr("aaa") ;
		String s = (String) getAttr(b,"str");
		System.out.println("str:"+s) ;
		
//		executeMethod
		b = new Bean() ;
		Object[] param = {"abc"} ;
		executeMethod(b,"setStr",param) ;
		System.out.println(executeMethod(b,"getStr",null)) ;
		
		param = new Object[3] ;
		param[0] ="abc" ;
		param[1] = new Integer(100) ;
		param[2] = Boolean.valueOf(false) ;
		
		System.out.println(executeMethod(b,"add",param)) ;
	}

	/**
	 * 获取对象属性
	 * @param clas
	 * @param attribute
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getAttr(Object obj,String attribute) 
	throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Class c = obj.getClass() ;
//		Field field = c.getField(attribute);	//获取publish变量
		
		Field field = c.getDeclaredField(attribute);
		field.setAccessible(true);//设置私有、保护变量的可以访问权限。
		
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
	private static Object executeMethod(Object obj,String methodName,Class[] clas,Object[] params)
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
	public static Object executeMethod(Object obj,String methodName,Object[] params)
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

}
