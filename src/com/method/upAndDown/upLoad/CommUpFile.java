package com.method.upAndDown.upLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.http.HttpServletRequest;

public class CommUpFile {
	
	/**
	 * 自己做的上传文件的方法，每次上传一个文件，可以上传中文文件。
	 * @param request
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean upFile(HttpServletRequest request,String path) throws IOException{
		try{
//			定义输入流in获取全部信息,建立临时文件tempFile并用session的id保存
			InputStream in = request.getInputStream();
			String tmpFile = request.getSession().getId();
//			建立文件f1,做为临时文件tempFile1传递的参数
			File f1=new File(path,tmpFile);
			FileOutputStream out = new FileOutputStream(f1,false);

//			读取文件内容并写入输出流文件toFile
			int n;
			byte b[]=new byte[100];
			while((n=in.read(b))!=-1){
				out.write(b,0,n);
			}
			in.close();
			out.close();
//			保存文件完毕。
		
//			获取文件的文件名
//			读取文件内容的第二行
			RandomAccessFile randomFile = new RandomAccessFile(f1,"r");
			randomFile.readLine();
			String secondLine = randomFile.readLine();
	
	//		获取文件名
			int selectStart=secondLine.lastIndexOf('\\')+1;
			int selectEnd=secondLine.length()-1;
			String fileName=secondLine.substring(selectStart,selectEnd);
			
	//		对文件名fileName进行中文字符处理
			fileName=new String(fileName.getBytes("iso-8859-1"),"GBK");
			File f2=new File(path,fileName);
			
	//	    获取文件内容		
	//		获取文件内容的起始位置
			randomFile.readLine();
			randomFile.readLine();
			long fileStartPosition = randomFile.getFilePointer();
	//		获取文件内容最后的位置
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
	//		重新指定文件内容的起始位置
			RandomAccessFile randomNewFile=new RandomAccessFile(f2,"rw");
			randomFile.seek(fileStartPosition);
	
	//		读取文件内容的全部内容
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
