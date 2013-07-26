package com.method.file.excel.readToBean;

/**
 * made by dyong 
 * date 2008-11-24 10:29:57
 **/
public class ExcelBean {
	private String name = "name" ;
	private String sex = "sex" ;
	private Integer age = new Integer(0) ;
	private String date = "date" ;
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
