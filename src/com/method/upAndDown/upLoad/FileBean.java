package com.method.upAndDown.upLoad;

public class FileBean {

	private String formname;	//form����Ӧ�ı�����
	private String filename;	//�ļ���
	private int size ;			//�ļ���С
	private String ext;			//�ļ���չ��
	private String path ;		//�ļ�����·��
	
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
