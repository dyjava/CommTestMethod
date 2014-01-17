package com.method.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server implements Runnable{
	private ByteBuffer r_buff = ByteBuffer.allocate(1024);
	private ByteBuffer w_buff = ByteBuffer.allocate(1024);
	private static int port = 8848;
	
	public Server(){
		new Thread(this).start();
	}
	
	public void run(){
		try{
			//生成一个侦听端
			ServerSocketChannel ssc = ServerSocketChannel.open();
			//将侦听端设为异步方式
			ssc.configureBlocking(false);
			//生成一个信号监视器
			Selector s = Selector.open();
			//侦听端绑定到一个端口
			ssc.socket().bind(new InetSocketAddress(port));
			//设置侦听端所选的异步信号OP_ACCEPT
			ssc.register(s,SelectionKey.OP_ACCEPT);
			
			System.err.println("echo server has been set up ......");
			
			while(true){
				int n = s.select();
				if (n == 0) {//没有指定的I/O事件发生
					continue;
				}
				Iterator it = s.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
					if (key.isAcceptable()) {//侦听端信号触发
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						//接受一个新的连接
						SocketChannel sc = server.accept();
						sc.configureBlocking(false);
						//设置该socket的异步信号OP_READ:当socket可读时，
						
						//触发函数DealwithData();
						sc.register(s,SelectionKey.OP_READ);
					}
					if (key.isReadable()) {//某socket可读信号
						dealwithData(key);
					}
					it.remove();
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void dealwithData(SelectionKey key) throws IOException{
		int count;
		//由key获取指定socketchannel的引用
		SocketChannel sc = (SocketChannel)key.channel();
		r_buff.clear();
		System.out.println("ssssssss") ;
		//读取数据到r_buff
		if(sc.isConnectionPending()){
			while((count = sc.read(r_buff))> 0){
				
			}
		}
		//确保r_buff可读
		r_buff.flip();
		
		w_buff.clear();
		//将r_buff内容拷入w_buff
		w_buff.put(r_buff);
		w_buff.flip();

		//将数据返回给客户端
		while(w_buff.hasRemaining()){
			byte[] temp = new byte[w_buff.limit()];
			w_buff.get(temp);
			String s = new String(temp) ;
			System.err.println("get String :"+s) ;
			
			//数据处理
			s = serverDeal(s);
			
			//返回数据
			ByteBuffer bf =ByteBuffer.allocate(1024) ;
			bf.put(s.getBytes()) ;
			bf.flip() ;
			while(bf.hasRemaining())
				sc.write(bf);
			bf.clear();
		}
		
		w_buff.clear();
		r_buff.clear();
	}
	
	/**
	 * 服务器端处理
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public String serverDeal(String s) throws IOException{
		s += "  test" ;
		return s ;
	}
	
	public static void main(String args[]){
		if(args.length > 0){
			port = Integer.parseInt(args[0]);
		}
		new Server();
	}
}
