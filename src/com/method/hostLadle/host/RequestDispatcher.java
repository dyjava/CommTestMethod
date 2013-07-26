package com.method.hostLadle.host;

import java.util.*;

import com.method.hostLadle.Const;

/**
 * 
 * @author diyong
 *
 */
public class RequestDispatcher{
	private static final String _HOST_SEPA = ",";
	private static final String _IP_PORT_SEPA = ":";
	private static final int _TOTAL_PERCENT = 100;

	private static HostInfo[] _host_array = null;
	private static int[] _dispatcher_list = new int[_TOTAL_PERCENT];
	private static int _dispatcher_index = -1;
	
	/**
	 * 结果页访问URL
	 * @param key
	 * @param pno
	 * @param psize
	 * @return
	 */
	public static String getLink() {
		StringBuffer sb = new StringBuffer();
		HostInfo host = getNormalHost();
		sb.append("http://").append(host.getIP());
		sb.append(":").append(host.getPort());
		return sb.toString();
	}
	
	/**
	* 
	*/
	private static HostInfo[] fetchAllHosts(){
		HostInfo[] results = null;

		String hostConfig = Const.getHOST();
		if (hostConfig != null) {
			results = parseConfig(hostConfig);
		}
		return results;
	}

	/**
	 * 
	*/
	private static HostInfo[] parseConfig(String hostConfig){
		ArrayList results = new ArrayList();

		StringTokenizer st = new StringTokenizer(hostConfig, _HOST_SEPA);
		while (st.hasMoreTokens()){
			String token = st.nextToken();
			HostInfo hi = extractHost(token);
			if (hi != null) {
				results.add(hi);
			}
		}

		return (HostInfo[]) results.toArray(new HostInfo[0]);
	}

	/**
	*
	*/
	private static HostInfo extractHost(String token){
		int pos = token.indexOf(_IP_PORT_SEPA);
		if (pos < 0) {
			return null;
		} else {
			String ip = token.substring(0, pos);
			int pos2 = token.indexOf(_IP_PORT_SEPA, pos + 1);
			String port = token.substring(pos + 1, pos2);
			String percent = token.substring(pos2 + 1);

			HostInfo hi = new HostInfo();
			hi.setIP(ip.trim());
			hi.setPort(port.trim());
			hi.setPercent(Integer.parseInt(percent.trim()));
			return hi;
		}
	}

	/**
	 *
	 */
	private static synchronized void generateDispatcherList() {
		int pos, count, percent, remain;
		Random rand = new Random(new Date().getTime());

		for (int i = 0; i < _TOTAL_PERCENT; i++) {
			_dispatcher_list[i] = -1;
		}

		remain = _TOTAL_PERCENT;
		for (int i = 0; i < _host_array.length; i++) {
			percent = _host_array[i].getPercent();
			for (int j = 0; j < percent; j++) {
				pos = rand.nextInt(remain - j) + 1;
				int listPos = 0;
				count = 0;
				while (count != pos) {
					if (_dispatcher_list[listPos] == -1) {
						count++;
					}
					listPos++;
				}

				_dispatcher_list[listPos - 1] = i;
			}
			remain = remain - percent;
		}

		System.out.println("Dispatch List");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(_dispatcher_list[i * 10 + j] + "   ");
			}
			System.out.println(" ");
		}
	}

	/**
	* 
	*/
	private static synchronized int getRequestIndex(){
		while (true) {
			if (_dispatcher_index == _TOTAL_PERCENT - 1) {
				_dispatcher_index = 0;
			} else {
				_dispatcher_index++;
			}
			if (_host_array[_dispatcher_list[_dispatcher_index]].workWell()) {
				break;
			}
		}

		return _dispatcher_list[_dispatcher_index];
	}

	/**
	* 
	*/
	private static HostInfo getNormalHost(){
		if (!StartThread.isCheckThreadStart()) {
			StartThread.startCheckThread();
		}

		HostInfo[] hiArray = getHostInfoArray();
		if (hiArray == null || hiArray.length <= 0) { return null; }

		int index = getRequestIndex();
		return hiArray[index];
	}
	
	public static void checkHostStatus(){
		System.out.println("start to check host status ...");
		HostInfo[] hiArray = getHostInfoArray();
		for (int i=0; i < hiArray.length; i++) {
			hiArray[i].checkStatus();
		}
	}
	
	private static HostInfo[] getHostInfoArray() {
		if (_host_array == null) {
			_host_array = fetchAllHosts();
			generateDispatcherList();
		}
		return _host_array;
	}
	
	public static void stopRequestDispatcher(){
		StartThread.stopCheckThread();
	}

}
