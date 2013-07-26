package com.method.index.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Sort;

import com.method.index.Const;
/** 
 * by dyong 2010-7-8
 */
public class TestImpl implements BaseIndexInterface{
	private static final Logger log = Logger.getLogger(TestImpl.class);
	
	public List<Object> getDataList() {
		// TODO Auto-generated method stub
		try {
			return getData() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}

	public Term getDeleteTerm(Object obj) {
		// TODO Auto-generated method stub
		TestBean data = (TestBean)obj ;
		return new Term("key",data.getKey()) ;
	}

	@SuppressWarnings("deprecation")
	public Document getDocument(Object obj) {
		// TODO Auto-generated method stub
		TestBean data = (TestBean) obj ;
		Document doc = new Document();
		doc.add(Field.Text("key", data.getKey()));
		doc.add(Field.Keyword("keyword", data.getKey()));
		doc.add(Field.Text("name", data.getName()));
		doc.add(Field.Text("url", data.getUrl()));
		doc.add(Field.Text("path", data.getPath()));
		
		doc.add(Field.Text("file", data.getFile()));
		doc.add(Field.UnIndexed("url2", data.getUrl()));
		doc.add(Field.UnIndexed("UpdateTime",new Date().toLocaleString()));
		return doc;
	}

	/**
	 * 获取建索引的数据
	 * @param bufferSize
	 * @return
	 * @throws Exception
	 */
	public List getData()
	throws Exception{
		InputStream is = null;
		try{
			List list = new ArrayList();

			is = getInputStream(Const.srcPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s = "";
			int count = 0 ;
			while((s=br.readLine())!=null){
				TestBean db = addDataBean(s);
				list.add(count,db);
				count++;
			}
			br.close();
			log.debug("data size:"+count);
			return list ;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			is.close();
		}
	}

	/**
	 * Bean赋值
	 * @param str
	 * @return
	 */
	private static TestBean addDataBean(String str) {
		TestBean db = new TestBean();
		String[] s = str.split("##");
		log.info("length:"+s.length);
		db.setKey(s[0]);
		db.setName(s[1]);
		db.setPath(s[2]);
		db.setUrl(s[3]);
		db.setFile(s[4]);
		return db;
	}

	/**
	 * 复制、重命名文件，删除原有文件。
	 * @param size
	 * @throws Exception
	 */
	private static void copyFile(){
		BufferedInputStream is = null;
		BufferedOutputStream os = null;
		try{
			is = new BufferedInputStream(new FileInputStream(new File(Const.srcPath)));
			os = new BufferedOutputStream(new FileOutputStream(new File(Const.outFile)));
			byte[] b1=new byte[1024*5];
			int read=0;
			while((read=is.read(b1))!=-1){
				os.write(b1,0,read);
			}
			is.close();
			os.close();
		}catch(IOException e){
			log.error("copyFile err:"+e);
		}
		//删除文件
		(new File(Const.srcPath)).delete();
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	private static InputStream getInputStream(String path)
	throws Exception{
		if(path.indexOf("www")>=0||path.indexOf("http")>=0){
			URL url = new URL(path);
			return url.openStream();
		}else{
			return new FileInputStream(new File(path));
//			return DataFactory.class.getResourceAsStream(path);
		}
	}
	
	/**
	 * 截取字节流
	 * @param bt
	 * @param begin
	 * @param len
	 * @return
	 * /
	private static byte[] SubBytes(byte[] bt,int begin,int len){
		byte[] b = new byte[len];
		int j=0;
		for(int i=begin;i<len&&i<bt.length;i++){
			b[j]=bt[i];
		}
		return b ;
	}
	
	private static short byte2short(byte[] _b, int _offset){
		short sValue = 0;
		long mask=0xFF;
        long temp=0;
		for (int i = 1; i >= 0; i--){
			sValue <<= 8;
			temp = _b[_offset+i] & mask;
			sValue += temp;
		}
		return sValue;
	}

	private static long byte2long(byte[] _b, int _offset){
		long lValue = 0;
		long mask=0xFF;
        long temp=0;
		for (int i = 3; i >= 0; i--){
			lValue <<= 8;
			temp = _b[_offset+i] & mask;
			lValue += temp;
		}
		return lValue;
	}
	
	private static int byte2int(byte[] _b, int _offset,int len){
		int sValue = 0;
		long mask=0xFF;
        long temp=0;
		for (int i = len-1; i >= 0; i--){
			sValue <<= 8;
			temp = _b[_offset+i] & mask;
			sValue += temp;
		}
		return sValue;
	}
	
	private static String byte2String(byte[] _b, int _offset, int _length){
		int length = 0;
		for (int i = 0; i < _length; i ++){
			if (0 == _b[_offset+i]){
				break;
			}
			length ++;
		}
		String retStr = new String(_b, _offset, length);
		return retStr;
	}
	 * @throws Exception 
	*/
	
	
	public List getDelData()
	throws Exception{
		List list = getData();//new ArrayList() ;
		return list;
	}

	public Object DocumentToObject(Document doc) {
		// TODO Auto-generated method stub
		
    	TestBean db = new TestBean();
    	db.setKey(doc.get("key"));
    	db.setName(doc.get("name"));
    	db.setUrl(doc.get("url"));
		return db;
	}

	public Sort getSort() {
		// TODO Auto-generated method stub
		Sort sort = new Sort("UpdateTime",true);
		return sort ;
	}
	

}
