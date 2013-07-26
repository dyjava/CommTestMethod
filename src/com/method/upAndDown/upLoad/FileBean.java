package com.method.upAndDown.upLoad;

public class FileBean {

	private String formname;	//form表单对应的表单项名
	private String filename;	//文件名
	private int size ;			//文件大小
	private String ext;			//文件扩展名
	private String path ;		//文件保存路径
	
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFormname() {
		return formname;
	}
	public void setFormname(String formname) {
		this.formname = formname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
