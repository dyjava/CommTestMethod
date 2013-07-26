package com.method.hostLadle.host;

import com.method.hostLadle.Const;

/**
 * 
 * @author diyong
 *
 */
public class CheckThread extends Thread 
{
	private static final int _DEFAULT_INTERVAL = 30;
	private boolean bRun = true;
	
	public void run() {
		while (bRun) {
			// 检测后台检索服务器端口是否可达
			RequestDispatcher.checkHostStatus();

			// 睡眠
			try {
				int checkInterval = Const.getSELLEP_TIME();
				if (Const.getSELLEP_TIME() <= 0) {
					checkInterval = _DEFAULT_INTERVAL;
				}
				Thread.sleep(checkInterval * 1000);		// checkInterval以秒为单位
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public synchronized void setRun(boolean bRun) {
		this.bRun = bRun;
	}
}

