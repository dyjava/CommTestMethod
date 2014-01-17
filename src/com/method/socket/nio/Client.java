package com.method.socket.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
	private SocketChannel sc;
	private final int MAX_LENGTH = 1024;
	private ByteBuffer r_buff = ByteBuffer.allocate(MAX_LENGTH);
	private ByteBuffer w_buff = ByteBuffer.allocate(MAX_LENGTH);
	private static String host ;
	private static int port = 8848;
	
	public Client(){
		try {
			InetSocketAddress addr = new InetSocketAddress(host,port);
			//生成一个socketchannel
			sc = SocketChannel.open();
			
			//连接到server
			sc.connect(addr);
			while(!sc.finishConnect())
				;
			System.out.println("connection has been established!...");
			
//			while(true){
//				//回射消息
//				String echo;
//				try{
//					System.err.println("Enter msg you'd like to send:  ");
//					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//					System.err.println("rr");
//					//输入回射消息
//					echo = br.readLine();
//
//					System.err.println("111");
//					//把回射消息放入w_buff中
//					w_buff.clear();
//					w_buff.put(echo.getBytes());
//					w_buff.flip();
//				}catch(IOException ioe){
//					System.err.println("sth. is wrong with br.readline() ");
//				}
//				//发送消息
//				while(w_buff.hasRemaining())
//					sc.write(w_buff);
//				w_buff.clear();
//
//				//进入接收状态
//				rec();
//				//间隔1秒
//				Thread.currentThread().sleep(1000);
//			}
		}catch(Exception ioe){
			ioe.printStackTrace();
		}
	}
	
	public void send(String msg) throws IOException{
		w_buff.clear();
		w_buff.put(msg.getBytes());
		w_buff.flip();
		//发送消息
		while(w_buff.hasRemaining())
			sc.write(w_buff);
		w_buff.clear();
	}
	
	//读取server端发回的数据，并显示
	public String rec() throws IOException{
		r_buff.clear();
		int count = sc.read(r_buff);
		r_buff.flip();
		if(count<=0){
			return "" ;
		}
		byte[] temp = new byte[r_buff.limit()];
		r_buff.get(temp);
//		System.out.println("reply is " + count +" long, and content is: " + new String(temp));
		return new String(temp) ;
	}
	
	public static void main(String args[]) throws IOException{
		if(args.length < 1){//输入需有主机名或IP地址
//			try{
//				System.err.println("Enter host name: ");
//				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//				host = br.readLine();
//			}catch(IOException ioe){
//				System.err.println("sth. is wrong with br.readline() ");
//			}
			host = "localhost";
		} else if(args.length == 1){
			host = args[0];
		} else if(args.length > 1){
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		Client c = new Client();
		c.send("new ") ;
		System.out.println(c.rec()) ;
	}

}
