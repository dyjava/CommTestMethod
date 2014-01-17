package com.method.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.method.reflex.bean.ChildBean;
import com.method.reflex.bean.Father;

/**
 * map对象映射到bean对象中。
 * 将map中的数据赋值到bean对象中，得到赋值后的bean对象。
 * @author diyong
 * @date 2011-10-18
 */
public class HashMapToBean {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChildBean c = new ChildBean() ;
		Father f = new Father() ;
		
		//测试取属性
		System.out.println("======= 测试取属性 ========") ;
		Class cc = c.getClass() ;
		Class cf = f.getClass() ;
		Field[] fs = cc.getDeclaredFields();
		System.out.println("===============") ;
		for(Field i:fs){
			System.out.println("child:"+i.getName()) ;
		}
		
		fs = cc.getSuperclass().getDeclaredFields() ;
		System.out.println("===============") ;
		for(Field i:fs){
			System.out.println("child super:"+i.getName()) ;
		}
		
		fs = cf.getDeclaredFields();
		System.out.println("===============") ;
		for(Field i:fs){
			System.out.println("father:"+i.getName()) ;
		}

		System.out.println("======= 赋值 ========") ;
		//赋值
		HashMap<String,Object> map = new HashMap<String,Object>() ;
		map.put("add", "beijing") ;
		map.put("hasSon", true) ;
		map.put("name", "java") ;
		map.put("age", 31) ;
		map.put("father", null)  ;
		
		ChildBean child = new ChildBean() ;
		child.setName("kill") ;
		child.setAge(10) ;
		map.put("child", child) ;
		
		new HashMapToBean().mapToBean(map, c) ;
		System.out.println(c.getName()+"\t"+c.getAdd()+"\t"+c.getAge()+"\t"+c.isHasSon()) ;
		System.out.println(c.getChild().getName()+""+c.getChild().getAge()) ;
		
		System.out.println("======= class ========") ;
		ChildBean obj = (ChildBean)new HashMapToBean().mapToBean(map, c.getClass());
		System.out.println(obj.getName()+"\t"+obj.getAdd()+"\t"+obj.getAge()+"\t"+obj.isHasSon()) ;
		System.out.println(obj.getChild().getName()+""+obj.getChild().getAge()) ;
		
		
		//获取方法
		System.out.println("======= 获取方法 ========") ;
		Method[] ms = cc.getMethods();
		for(Method m:ms){
			System.out.println(m.getName()) ;
		}
		System.out.println("===============") ;
		ms = cc.getDeclaredMethods() ;
		for(Method m:ms){
			System.out.println(m.getName()) ;
		}
		System.out.println("===============") ;
		ms = cc.getSuperclass().getDeclaredMethods() ;
		for(Method m:ms){
			System.out.println(m.getName()) ;
		}
		
		System.out.println("======= 通过方法赋值 ========") ;
		//通过方法赋值
		c = new ChildBean() ;
		new HashMapToBean().mapToBeanMethod(map, c) ;
		System.out.println("===============") ;
		System.out.println(c.getName()+"\t"+c.getAdd()+"\t"+c.getAge()+"\t"+c.isHasSon()) ;
		System.out.println(c.getChild().getName()+""+c.getChild().getAge()) ;
		
		
	}
	
	/**
	 * 通过对象属性的方式赋值
	 * 只能对public属性赋值，对private属性不能赋值，需设置参数.setAccessible(true);后可以访问
	 * @param map
	 * @param bean
	 */
	public void mapToBean(HashMap<String,Object> map,Object bean){
		mapToBean(map,bean,bean.getClass()) ;
	}
	public Object mapToBean(HashMap<String,Object> map,Class<?> clas) {
		try {
			Object bean = clas.newInstance();
			mapToBean(map,bean,clas) ;
			return bean ;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	private void mapToBean(HashMap<String,Object> map,Object bean,Class<?> clas){
//		System.out.println("=====start>"+clas.getName()) ;
		if(!clas.getSuperclass().equals(Object.class)){
			this.mapToBean(map, bean, clas.getSuperclass()) ;
		}
		Field[] fs = clas.getDeclaredFields();	//获取私有属性。
//		fs = clas.getFields();	//获取public的属性
		for(Field f:fs){
			if(map.containsKey(f.getName())){
				f.setAccessible(true);//设置私有、保护变量的可以访问权限。
//				System.out.println(f.getName()+"\t"+map.get(f.getName())) ;
				try {
					f.set(bean, map.get(f.getName())) ;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
//		System.out.println("=====end>"+clas.getName()) ;
	}

	/**
	 * 通过Methods方法赋值
	 * 通过属性的set方法赋值
	 * @param map
	 * @param bean
	 */
	public void mapToBeanMethod(HashMap<String,Object> map,Object bean){
		Method[] ms = bean.getClass().getMethods();
		for(Method m:ms){
			String name = m.getName() ;
			if(name.startsWith("set")){
				name = name.substring(3) ;
				name = name.substring(0,1).toLowerCase()+name.substring(1) ;
				if(map.containsKey(name)){
					try {
						System.out.println(m.getName()+"\t"+map.get(name)) ;
						m.invoke(bean, map.get(name)) ;
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
//			System.out.println("-----") ;
		}
	}
}
