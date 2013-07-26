package com.method.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter {

	public static void sorter(int[] a){
		Arrays.sort(a);
	}
	public static void sorter(int[] a,int begin,int end){
		Arrays.sort(a,begin,end);
	}
	
	public static void sorter(String[] a){
		Arrays.sort(a);
	}
	public static void sorter(String[] a,int begin,int end){
		Arrays.sort(a,begin,end);
	}
	
	public static void sorter(Bean[] a){
		Arrays.sort(a,new comp());
	}
	
	public static void listSort(List list){
		Collections.sort(list,new comp()) ;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int[] in= {1,8,6,4,3,9,7,10,2,5};
//		sorter(in);
//		String[] s = {"a","d","vad","ar","hds","fad","aa","dd","csd",};
//		sorter(s,3,8);
//		
//		for(int i=0;i<s.length;i++){
//			System.out.println("=="+s[i]);
//		}
//		��������
		Bean[] b = new Bean[5];
		Bean bean0 = new Bean();
		bean0.setAge(12.5);
		bean0.setId(1);
		bean0.setName("hello");
		bean0.setSex(true);
		b[0]=bean0 ;
		
		Bean bean1 = new Bean();
		bean1.setAge(10.5);
		bean1.setId(3);
		bean1.setName("hsaflo");
		bean1.setSex(true);
		b[1]=bean1 ;
		
		Bean bean2 = new Bean();
		bean2.setAge(18.5);
		bean2.setId(5);
		bean2.setName("asdf");
		bean2.setSex(true);
		b[2]=bean2 ;
		
		Bean bean3 = new Bean();
		bean3.setAge(6.5);
		bean3.setId(2);
		bean3.setName("saaf");
		bean3.setSex(true);
		b[3]=bean3 ;
		
		Bean bean4 = new Bean();
		bean4.setAge(52.5);
		bean4.setId(4);
		bean4.setName("sfs");
		bean4.setSex(true);
		b[4]=bean4 ;
		
		sorter(b);
		for(int i=0;i<b.length;i++){
			Bean be = b[i];
			System.out.println("=="+be.getId());
		}
	}
}

class Bean{
	int id ;
	String name ;
	double age ;
	boolean sex ;
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
}

class comp implements Comparator<Bean>{

	public int compare(Bean a, Bean b) {
		if(a.getId()>b.getId()){
			return -1 ;
		}else if(a.getId()==b.getId()){
			return 0 ;
		}else{
			return 1 ;
		}
	}
	
}
