package my.common.database.db.xml;

public class Util {

	public static String praseString(Object obj,String defalt){
		return obj==null?defalt:(String)obj;
	}
	
	public static int praseInt(Object obj,int defalt){
		String s = String.valueOf(obj);
		return obj==null||s.equals("")?defalt:Integer.parseInt(s) ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(praseString("sfasdf","dd"));
		System.out.println(praseInt("34",3));
	}

}
