package com.method.upAndDown.upLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.http.HttpServletRequest;

public class CommUpFile {
	
	/**
	 * �Լ������ϴ��ļ��ķ�����ÿ���ϴ�һ���ļ��������ϴ������ļ���
	 * @param request
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean upFile(HttpServletRequest request,String path) throws IOException{
		try{
//			����������in��ȡȫ����Ϣ,������ʱ�ļ�tempFile����session��id����
			InputStream in = request.getInputStream();
			String tmpFile = request.getSession().getId();
//			�����ļ�f1,��Ϊ��ʱ�ļ�tempFile1���ݵĲ���
			File f1=new File(path,tmpFile);
			FileOutputStream out = new FileOutputStream(f1,false);

//			��ȡ�ļ����ݲ�д��������ļ�toFile
			int n;
			byte b[]=new byte[100];
			while((n=in.read(b))!=-1){
				out.write(b,0,n);
			}
			in.close();
			out.close();
//			�����ļ���ϡ�
		
//			��ȡ�ļ����ļ���
//			��ȡ�ļ����ݵĵڶ���
			RandomAccessFile randomFile = new RandomAccessFile(f1,"r");
			randomFile.readLine();
			String secondLine = randomFile.readLine();
	
	//		��ȡ�ļ���
			int selectStart=secondLine.lastIndexOf('\\')+1;
			int selectEnd=secondLine.length()-1;
			String fileName=secondLine.substring(selectStart,selectEnd);
			
	//		���ļ���fileName���������ַ�����
			fileName=new String(fileName.getBytes("iso-8859-1"),"GBK");
			File f2=new File(path,fileName);
			
	//	    ��ȡ�ļ�����		
	//		��ȡ�ļ����ݵ���ʼλ��
			randomFile.readLine();
			randomFile.readLine();
			long fileStartPosition = randomFile.getFilePointer();
	//		��ȡ�ļ���������λ��
			long fileEndPosition=randomFile.length()-1;
		
			int fileNum=0;
			while((fileEndPosition>=0) && (fileNum<5)){
			    fileEndPosition--;
				randomFile.seek(fileEndPosition);
				n=randomFile.readByte();
				if(n=='\n'){
					fileNum++;
				}
			}
			
			fileEndPosition=randomFile.getFilePointer() -1;
	//		����ָ���ļ����ݵ���ʼλ��
			RandomAccessFile randomNewFile=new RandomAccessFile(f2,"rw");
			randomFile.seek(fileStartPosition);
	
	//		��ȡ�ļ����ݵ�ȫ������
			while(fileStartPosition<fileEndPosition-1){
				n=randomFile.readByte();
				randomNewFile.write(n);
				fileStartPosition=randomFile.getFilePointer();
			}
			randomFile.close();
			randomNewFile.close();
//			f1.delete();
		} catch (IOException e) {
			e.printStackTrace();
			return false ;
		}
		return true ;
	}

}
