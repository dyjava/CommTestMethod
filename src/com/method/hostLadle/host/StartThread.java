package com.method.hostLadle.host;

/**
 * 
 * @author diyong
 *
 */
public class StartThread{
	private static boolean _start_status = false;
	private static CheckThread _check_thread = null;
	
	public static boolean isCheckThreadStart() {
		return _start_status;
	}

	public static synchronized void startCheckThread()  {
		if (_check_thread != null) {
			return;
		}
		
		_check_thread = new CheckThread();
		_check_thread.start();
		setStartStatus(true);
	}

	public static synchronized void stopCheckThread()  {
		if (!_start_status) {
			return;
		}
		
		_check_thread.setRun(false);
		setStartStatus(false);
	}
	
	private static void setStartStatus(boolean value) {
		_start_status = value;
	}
}
