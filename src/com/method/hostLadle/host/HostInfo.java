package com.method.hostLadle.host;

import java.net.Socket;
import java.net.InetSocketAddress;

/**
 * 
 * @author diyong
 *
 */
public class HostInfo{
	private String _ip;
	private String _port;
	private int _percent;
	private boolean _status = false;
	
	/**
	* 判断服务器是否可连接访问
	*/
	public static boolean canConnect(String host, String port){
		final int _TIME_OUT = 200;
		
		boolean ret = true;
		try{
			Socket s = new Socket();
			int iPort = Integer.parseInt(port);
			s.connect(new InetSocketAddress(host, iPort), _TIME_OUT);
		} catch (Exception ex) {
			ret = false;
		}
		return ret;
	}

	/**
	* 设置服务可达状态
	*/
	public void checkStatus(){
		setStatus(canConnect(getIP(), getPort()));
	}
	
	public String getIP() {
		return _ip;
	}
	public void setIP(String _ip) {
		this._ip = _ip;
	}
	public String getPort() {
		return _port;
	}
	public void setPort(String _port) {
		this._port = _port;
	}
	public boolean workWell() {
		return _status;
	}
	public void setStatus(boolean _status) {
		this._status = _status;
		if (!_status) {
			System.out.println(_ip + ":" + _port + " can't connected!");
		}
	}
	public int getPercent() {
		return _percent;
	}
	public void setPercent(int _percent) {
		this._percent = _percent;
	}
}
