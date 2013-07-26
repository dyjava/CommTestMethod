package com.method.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class IOCommUtil {
    private HttpClient client ;
    private static IOCommUtil Instance = new IOCommUtil() ;
    public static IOCommUtil getInstance(){
    	return Instance ;
    }
    private IOCommUtil(){
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		client = new HttpClient(connectionManager);
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(60000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(60000);
		
		managerParams.setDefaultMaxConnectionsPerHost(100);  //very important!! 
		managerParams.setMaxTotalConnections(5000);  //very important!! 
	}
    
    /**
     * 
     * @param filepath
     * @return
     */
    public ArrayList<String> readLocalFile(String filepath){
    	return readLocalFile(filepath,"utf-8") ;
    }
    public ArrayList<String> readLocalFile(String filepath,String code){
    	ArrayList<String> list = new ArrayList<String>();
		try {
			InputStream is = IOCommUtil.class.getResourceAsStream(filepath);
			ReadableByteChannel channel = Channels.newChannel(is);
			ByteBuffer bs = ByteBuffer.allocate(10240);
			while(channel.read(bs)>-1){
				// flip方法让缓冲区可以将新读入的数据写入另一个通道  
				bs.flip();
				list.add(new String(bs.array(),code)) ;
				// clear方法重设缓冲区，使它可以接受读入的数据  
				bs.clear();
			}
			
//			InputStreamReader isr = new InputStreamReader(is,code);   
//			BufferedReader br = new BufferedReader(isr);
//			
//			String record = "";
//			while ((record = br.readLine()) != null) {
//			    if(record.startsWith("#")){
//				continue ;
//			    }
//			    list.add(record.trim()) ;
//			}
//			is.close();
//			isr.close();
//			br.close();
			return list ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
		return list ;
	}
    
    public ArrayList<String> readLocalFile2(String filepath){
    	return readLocalFile2(filepath,"utf-8") ;
    }
    public ArrayList<String> readLocalFile2(String filepath,String code){
    	ArrayList<String> list = new ArrayList<String>();
		try {
			InputStream is = new FileInputStream(filepath);
			ReadableByteChannel channel = Channels.newChannel(is);
			ByteBuffer bs = ByteBuffer.allocate(10240);
			while(channel.read(bs)>-1){
				// flip方法让缓冲区可以将新读入的数据写入另一个通道  
				bs.flip();
				list.add(new String(bs.array(),code)) ;
				// clear方法重设缓冲区，使它可以接受读入的数据  
				bs.clear();
			}
//			InputStreamReader isr = new InputStreamReader(is,code);   
//			BufferedReader br = new BufferedReader(isr);
//			
//			String record = "";
//			while ((record = br.readLine()) != null) {
//				list.add(record.trim()) ;
//			}
//			isr.close();
//			br.close();
			is.close();
		}catch(Exception e){
			e.printStackTrace() ;
		}
		return list;
	}
    
    /**
     * 
     * @param url
     * @param code
     * @return
     * @throws IOException
     */
    public String readUrlContent(String url,String code) throws IOException{
		return readUrlContent(url,code,true) ;
    }
    public String readUrlContent(String url,String code,boolean usegzip) {
		GetMethod get = null ;
		StringBuffer html = new StringBuffer() ;
		int statusCode=0 ;
		try {
			get = new GetMethod(url);
			if(usegzip){
				get.setRequestHeader("Accept-Encoding","gzip, deflate"); 
			}
			get.setRequestHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2)") ;//设置UA
			get.setFollowRedirects(true);	//支持重定向
			statusCode = client.executeMethod(get);
//			get.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, readTimeout);

			if (statusCode == HttpStatus.SC_OK) {
				//NIO
				ReadableByteChannel channel ;
//				gzip压缩
				if(usegzip && get.getResponseHeader("Content-Encoding")!=null && 
						get.getResponseHeader("Content-Encoding").getValue().toLowerCase().indexOf("gzip")>-1){
//				    System.out.println("gzip") ;
					GZIPInputStream gzin = new GZIPInputStream(get.getResponseBodyAsStream());
					channel = Channels.newChannel(gzin);
				} else {
//					非压缩
					channel = Channels.newChannel(get.getResponseBodyAsStream());
				}
				ByteBuffer bs = ByteBuffer.allocate(20480);
				while(channel.read(bs)>-1){
					bs.flip() ;
					html.append(new String(bs.array(),code)) ;
					bs.clear() ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {
			get.releaseConnection();
		}
		return html.toString() ;
	}

    /**
     * 
     * @param filePath
     * @param text
     * @return
     */
    public boolean StringWriteToFile(String filePath,String text){
    	return StringWriteToFile(filePath,text,false) ;
    }
    public boolean StringWriteToFile(String filePath,String text,boolean isNewFile){
		boolean b = false ;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filePath, !isNewFile), false);
			pw.println(text);
			pw.close();
			b = true;
		}catch (Exception e) {
			System.out.println("err:"+e.getMessage());
		}
		return b ;
    }
    public boolean StringWriteToFile(String filePath,String[] text,boolean newFile){
		boolean b = false ;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filePath, !newFile), false);
			for(int i=0;i<text.length;i++){
				pw.println(text[i]);
			}
			pw.close();
			b = true;
		}catch (Exception e) {
			System.out.println("err:"+e.getMessage());
		}
		return b ;
    }
    
    /**
     * 
     * @param url
     * @param filePath
     * @return
     * @throws IOException 
     */
    public boolean downFile(String url,String filePath) throws IOException{
    	return downFile(url,new File(filePath)) ;
    }
    public boolean downFile(String url,File file) throws IOException{
		GetMethod get = null ;
		int statusCode=0 ;
		try {
			get = new GetMethod(url);
			statusCode = client.executeMethod(get);
//			get.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, readTimeout);
			get.setRequestHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2)") ;
			
			if (statusCode == HttpStatus.SC_OK) {
				InputStream is = get.getResponseBodyAsStream() ;
//				InputStreamReader isr = new InputStreamReader(is, get.getResponseCharSet());
				
				FileOutputStream output = new FileOutputStream(file);
				byte b[] = new byte[1024] ;
				int len ;
				while ((len = is.read(b))>0) {
					output.write(b,0,len) ;
				}
				is.close();
				output.close() ;
			}
		} catch (IOException e) {
			throw e ;
		} finally {
			get.releaseConnection();
		}
		return true ;
	}

    @SuppressWarnings("finally")
	public boolean urlTest(String url){
    	boolean f = false ;
    	try {
    		HeadMethod method = new HeadMethod(url);
    		method.setRequestHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2)") ;
    		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000);
    		method.setRequestHeader("Connection", "close");
    		
			int code = client.executeMethod(method);
			if (code == HttpStatus.SC_OK ||
					code == HttpStatus.SC_MOVED_PERMANENTLY ||
					code == HttpStatus.SC_MOVED_TEMPORARILY) {
				f = true ;
			}
		} catch (Exception e) {
//			System.out.println(e.getMessage()) ;
		} finally {
			return f ;
		}
    }
    public boolean copeFile(File from,File to)
	throws IOException{
    	InputStream is = new FileInputStream(from);
		FileOutputStream os = new FileOutputStream(to);
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(os);//用流复制
		boolean b = false ;
		try{
			byte[] b1=new byte[1024*10];//设一个1024*bufferSize字节的数组 
			int read=0;
			while((read=bis.read(b1))!=-1){
				bos.write(b1,0,read);//从数组里往文件里写数据 
			}
			b = true ;
		}catch(IOException e){
			throw e;
		}finally{
			bis.close();
			bos.close();
		}
		return b ;
	}
 
    public static void main(String[] args) throws Exception{
    	String filepath = "/singer.xml" ;
    	ArrayList<String> result = IOCommUtil.getInstance().readLocalFile(filepath);
    	
    	filepath = "e:/strategydb2.sql" ;
    	result = IOCommUtil.getInstance().readLocalFile2(filepath) ;
    	
    	if(result.size()>0){
//    	for(String line:result){
//    		System.err.println(line) ;
//    	}
    	}
    	
    	String url = "http://www.baidu.com/" ;
    	System.out.println(IOCommUtil.getInstance().readUrlContent(url, "gbk")) ;
    	
    	String filePath = "e:/aa.jpg";
		url = "http://photocdn.sohu.com/20080320/38e_2e344281_ad3f_4589_96b6_f83f5455734d_7.jpg" ;
		url="http://pica.nipic.com/2008-03-11/2008311182922187_2.jpg";
		new IOCommUtil().downFile(url , filePath) ;
		
    }
}
