package com.method.util;

import java.io.IOException;

import com.method.file.ReadLocalFileMethod;
import com.method.file.ReadWebFileMethod;

/**
 * made by dyong 
 * date 2009-9-22 上午09:47:31
 **/
public class BaiduMp3DownUrl {

	private int[] I = new int[123],F = new int[123];
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String url = "http://220.181.38.82/m?word=rm,http://www.djxf.com/song/%A1%BEdj%CF%C8%B7%E6%A1%BF%CE%E8%C7%FA%CE%C4%BC%FE/%A1%BEdj%CF%C8%B7%E6%A1%BFrm%CE%E8%C7%FA%CE%C4%BC%FE/%D6%D0%CE%C4%CE%E8%C7%FA/%D6%D0%CE%C4%D7%A8%BC%AF032/~Qe56IKbo6eVq2Nq7wXqAV52nYuapJuxMQ$$.rm,,[melody+%CC%D5%86%B4]&ct=134217728&tn=baidusg,melody%20%20dj&si=melody;;%CC%D5%86%B4;;5633;;5633&lm=16777216&sgid=1" ;
//		url = "http://220.181.38.82/m?word=mp3,http://cgzx.yuxi.gov.cn/../jin/npefo5mvNw$$.mp3,,[melody+%CC%D5%86%B4]&ct=134217728&tn=baidusg,melody%20%20&si=melody;;%CC%D5%86%B4;;5633;;5633&lm=16777216&sgid=10" ;
		url = getUrl(url) ;
		System.out.println(url) ;
	}
	
	public static String getUrl(String url) throws IOException{
		String countent = ReadWebFileMethod.readUrlContent(url,"gbk") ;
		countent = countent.substring(countent.lastIndexOf("<script>")) ;
		int begin = countent.indexOf("sertim=")+7 ;
		int end = countent.indexOf(",",begin) ;
		String sert = countent.substring(begin,end) ;
		
		begin = countent.indexOf("var B=\"")+7 ;
		end = countent.indexOf("\"",begin) ;
		String u = countent.substring(begin,end) ;
		
		BaiduMp3DownUrl baidu = new BaiduMp3DownUrl() ;
		int sertim = Integer.parseInt(sert) ;
		return baidu.decode(u,sertim) ;
	}

	private String decode(String B,int sertim){
		int E=B.length();
		String C = "";
		int J=sertim%26;
		J=J>0?J:1;
		
		K(0,9,48);
		K(10,35,55);
		K(36,61,61);
		for(int D=0;D<E;D++){
			char A=B.charAt(D);
			if(isIn(A)){
				int H=F[A]-J;
				if(H<0){H+=62;}
				A= (char)I[H];
			}
			C+=A ;
		}
		return C ;
	}
	private void K(int O,int L,int M){
		for(int N=O;N<=L;N++){
			I[N]=N+M ;
			F[N+M]=N ;
		}
	}
	private boolean isIn(char a){
		if(a>=48 && a<=57){
			return true ;
		}
		if(a>=65 && a<=90){
			return true ;
		}
		if(a>=97 && a<=122){
			return true ;
		}
		return false ;
	}
	
}
