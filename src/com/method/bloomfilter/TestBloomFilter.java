package com.method.bloomfilter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestBloomFilter {

	/**
	 * @param args
	 */
	static final long ELEMENTS = 100000;
	static final double MAX_FAILURE_RATE = 0.1;
	public static void main(String[] args) throws IOException {
		String path = "e:/tmp/bf9" ;
		long s=System.currentTimeMillis();
		List list = new ArrayList() ;
		for(int i=0;i<1000;i++){
			list.add(i+"bj") ;
		}
		input(path,list) ;
        long a=System.currentTimeMillis();
        System.out.println("==>"+(a-s)) ;
        
        System.out.println(read(path,"7bj")) ;
        long x=System.currentTimeMillis();
        System.out.println("==>"+(x-a)) ;
	}
	
	private static void input(String path,List list) throws IOException{
		BloomFilter bf= BloomFilter.getFilter(ELEMENTS, MAX_FAILURE_RATE);
//		add data
		for (int i=0;i<list.size();i++){
			bf.add((String) list.get(i)) ;
		}
//		write file
        FileOutputStream fos = new FileOutputStream(path);
        DataOutputStream ostream = new DataOutputStream(fos);
        BloomFilter.serializer().serialize(bf, ostream);
        ostream.flush();
        fos.getFD().sync();
        ostream.close();
	}

	private static boolean read(String path,String key) throws IOException{
        //read from file
		DataInputStream stream = new DataInputStream(new FileInputStream(path));
		BloomFilter bf = BloomFilter.serializer().deserialize(stream);
		try {
			return bf.isPresent(key) ;
		} finally {
			stream.close();
		}
	}
}
