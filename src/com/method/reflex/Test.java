package com.method.reflex;

import java.awt.Rectangle;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * made by dyong 
 * date 2009-4-24 13:15:16
 **/
public class Test {

	private static void getMethod(String clas){
		try { 
			Class c = Class.forName(clas); 
			Method m[] = c.getDeclaredMethods(); 
			for (int i = 0; i < m.length; i++) 
				System.out.println(m[i].toString()); 
		} catch (Throwable e) { 
			System.err.println(e); 
		}
	}
	
	private static Object getAttr(Object r,String attr) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException { 
		Class c = r.getClass();
		Field field = c.getField(attr);
		Integer value = (Integer) field.get(r);
		return value ;
	}
	
	
	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 */
	public static void main(String[] args) 
	throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//		test 1
		getMethod("java.lang.String") ;
		
//		test 2
		Rectangle r = new Rectangle(100, 325);
		System.out.println("Height:" + getAttr(r,"height"));
		
	}
	

}
