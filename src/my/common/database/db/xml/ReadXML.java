package my.common.database.db.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadXML {
	
	/**
	 * ��ȡ�ļ��������ַ�
	 * @param filepath �ļ���·��
	 * @return
	 * @throws Exception
	 */
	public static String readFileToString(String FilePath)
	throws IOException {
		FileReader fr= new FileReader(FilePath);
		String record = "";
		StringBuffer sb = new StringBuffer();
		int recCount = 0;
		BufferedReader br = new BufferedReader(fr);

		while ((record = br.readLine()) != null) {
			recCount++;
			sb.append(record.trim());
		}
		fr.close();
		br.close();
		return sb.toString() ;
	}
	
	public static String parseTag0(String content, String tag){
		return parseTag(content,tag)[0] ;
	}
	
	public static String[] parseTag(String content, String tag){
		final String _TAG_START = "<" + tag + ">";
		final String _TAG_END = "</" + tag + ">";

		ArrayList results = new ArrayList();

		int start = content.indexOf(_TAG_START);
		int end = 0;
		while (start >= 0) {
			end = content.indexOf(_TAG_END, start);
			if (end < start) {
				break;
			}
			String item = content.substring(start + _TAG_START.length(), end);
			start = content.indexOf(_TAG_START, end);

			results.add(item);
		}
		return (String[]) results.toArray(new String[0]);
	}
}
