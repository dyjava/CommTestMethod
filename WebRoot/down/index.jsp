<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String datapath = "/data/";

	String srcFolder = request.getRealPath(datapath);
	String url = "http://"+request.getLocalAddr()+request.getContextPath()+datapath;
	File src = new File(srcFolder);
	File[] fileList = src.listFiles();
	Arrays.sort(fileList);
	
	int all = fileList.length-1;
	int p = (request.getParameter("pno")==null)?all:Integer.parseInt(request.getParameter("pno"));
	int t = 0;
	int psize = 10 ;
%>
<html>
  <head>
    <title>page list</title>
   </head>
  <body>
  <table border="1" align="center">
   <%
	StringBuffer sb = new StringBuffer();
    for(;p>0 && t<psize;p--){
    	sb.append("<tr><td>");
    	String filename = fileList[p].getName();
    	//out.print((t+1)+".<a href='down2.jsp?path="+fileList[p]+"'>"+filename);
    	sb.append((t+1)+".<a href='/test/down?path="+fileList[p]+"'>"+filename);
    	//out.print((t+1)+".<a href='/test/down?url=http://192.168.0.110:8080/test/a.rar'>"+filename);
    	sb.append("</a></td><td>");
    	if(filename.endsWith(".jpg") || filename.endsWith(".gif") || filename.endsWith(".bmp")){
    		sb.append("<img alt='img' src='"+fileList[p]+"' height='80'/>");
    	}else if(filename.endsWith(".mp3")){
    		sb.append("<span style='cursor:hand' onClick=\"document.MediaPlayer1.filename='"+url+filename+"';\" > >>����</span>");
    	}
    	sb.append("</td></tr>");
    	t++;
    }
    out.println(sb.toString());
    %>
<%-- MP3���߲����� --%>
    <object classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95" id="MediaPlayer1" width="286" height="70">
              <param name="AudioStream" value="-1">
              <param name="AutoSize" value="-1">
              <!--�Ƿ��Զ��������Ŵ�С-->
              <param name="AutoStart" value="-1">
              <!--�Ƿ��Զ�����-->
              <param name="AnimationAtStart" value="-1">
              <param name="AllowScan" value="-1">
              <param name="AllowChangeDisplaySize" value="-1">
              <param name="AutoRewind" value="0">
              <param name="Balance" value="0">
              <!--��������ƽ��,����-9640,����9640-->
              <param name="BaseURL" value>
              <param name="BufferingTime" value="15">
              <!--����ʱ��-->
              <param name="CaptioningID" value>
              <param name="ClickToPlay" value="-1">
              <param name="CursorType" value="0">
              <param name="CurrentPosition" value="0">
              <!--��ǰ���Ž��� -1 ��ʾ����,0��ʾ��ͷ ��λ����,����10��ʾ�ӵ�10�봦��ʼ����,ֵ������-1.0����ڵ���0-->
              <param name="CurrentMarker" value="0">
              <param name="DefaultFrame" value>
              <param name="DisplayBackColor" value="0">
              <param name="DisplayForeColor" value="16777215">
              <param name="DisplayMode" value="0">
              <param name="DisplaySize" value="0">
              <!--��Ƶ1-50%, 0-100%, 2-200%,3-ȫ�� ������ֵ��0����,С���������������Ȼ��ǰ�Ĵ���-->
              <param name="Enabled" value="-1">
              <param name="EnableContextMenu" value="-1">
              <!--�Ƿ����Ҽ������˵�����-->
              <param name="EnablePositionControls" value="-1">
              <param name="EnableFullScreenControls" value="-1">
              <param name="EnableTracker" value="-1">
              <!--�Ƿ������������Ž�����������ط�����-->
              <param name="Filename" value="a.mp3" valuetype="ref">
              <param name="InvokeURLs" value="-1">
              <param name="Language" value="-1">
              <param name="Mute" value="0">
              <!--�Ƿ���-->
              <param name="PlayCount" value="10">
              <!--�ظ����Ŵ���,0Ϊʼ���ظ�-->
              <param name="PreviewMode" value="-1">
              <param name="Rate" value="1">
              <!--�����ٶ�1.0-2.0�����ٶȲ���-->
              <param name="SAMILang" value>
              <param name="SAMIStyle" value>
              <param name="SAMIFileName" value>
              <!--ѡ��ͬʱ����(����)�ĸ���-->
              <param name="SelectionStart" value="-1">
              <param name="SelectionEnd" value="-1">
              <param name="SendOpenStateChangeEvents" value="-1">
              <param name="SendWarningEvents" value="-1">
              <param name="SendErrorEvents" value="-1">
              <param name="SendKeyboardEvents" value="0">
              <param name="SendMouseClickEvents" value="0">
              <param name="SendMouseMoveEvents" value="0">
              <param name="SendPlayStateChangeEvents" value="-1">
              <param name="ShowCaptioning" value="0">
              <!--�Ƿ���ʾ��Ļ,Ϊһ���ɫ,�������һ����ɫ,һ�㲻��ʾ-->
              <param name="ShowControls" value="-1">
              <!--�Ƿ���ʾ����,���粥��,ֹͣ,��ͣ-->
              <param name="ShowAudioControls" value="-1">
              <!--�Ƿ���ʾ��������-->
              <param name="ShowDisplay" value="0">
              <!--��ʾ��Ŀ��Ϣ,�����Ȩ��-->
              <param name="ShowGotoBar" value="0">
              <!--һ����,������,�����¼�ͷ-->
              <param name="ShowPositionControls" value="-1">
              <!--�Ƿ���ʾ��ǰ�����б�,�����ʾһ��Ҳ���ǻ�ɫ���ɿ���-->
              <param name="ShowStatusBar" value="-1">
              <!--��ǰ������Ϣ,��ʾ�Ƿ����ڲ���,���ܲ���ʱ��͵�ǰ���ŵ���ʱ��-->
              <param name="ShowTracker" value="-1">
              <!--�Ƿ���ʾ��ǰ���Ÿ�����,����ǰ�Ĳ��Ž�����-->
              <param name="TransparentAtStart" value="-1">
              <param name="VideoBorderWidth" value="0">
              <!--��ʾ���Ŀ�,���С����Ƶ��,����СΪ��Ƶ��,���߼Ӵ�ָ��ֵ,���Զ��Ӵ�߶�.�˸ı�ֻ�ı����ܵĺڿ��С,���ı���Ƶ��С-->
              <param name="VideoBorderColor" value="0">
              <!--��ʾ��ɫ�����ɫ, ΪRGBֵ,����ffff00Ϊ��ɫ-->
              <param name="VideoBorder3D" value="0">
              <param name="Volume" value="0">
              <!--������С,��ֵ��ʾ�ǵ�ǰ�����ļ�ֵ,ֵ�Զ���ȡ����ֵ,���Ϊ0,��СΪ-9640,���0-->
              <param name="WindowlessVideo" value="0">
              <!--�����0��������ȫ��,����ֻ���ڴ����в鿴-->
</object>
<%-- end --%>
	</table>
	<%if(p>0){%>
		<a href="?pno=<%=p%>">��ҳ</a>
	<%} if(p<all-psize){%>
	<a href="">��һҳ</a>
	<%} %>
  </body>
</html>
