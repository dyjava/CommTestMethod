<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<% request.setCharacterEncoding("gb2312"); %>
<%@ page import="java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
</head>
<body>
<%
String path = (String)request.getRealPath("upload")+"/";
	try{
		String temp=(String)session.getId();
		File f1=new File(path,temp);

		FileOutputStream o=new FileOutputStream(f1);

		InputStream in=request.getInputStream();
		byte b[]=new byte[1024*1024];
		int n;
		while((n=in.read(b))!=-1){
			o.write(b,0,n);
		}
		o.close();
		in.close();
		//读取临时文件f1,从中获取上传文件的名字和上传文件的内容。
		RandomAccessFile random=new RandomAccessFile(f1,"r");
		int second=1;
		String secondLine=null;
		while(second<=2){
			secondLine=random.readLine();
			second++;
		}
		int position=secondLine.lastIndexOf('\\');
		String fileName=new String((secondLine.substring(position+1,secondLine.length()-1)).getBytes("iso-8859-1"), "GB2312");
		
		random.seek(0);
		long forthEnPosition=0;
		int forth=1;
		while((n=random.readByte())!=1&&(forth<=4)){
			if((n=='\n')){
				forthEnPosition=random.getFilePointer();
				forth++;
			}
		}
		File f2=new File(path ,fileName);
		
		RandomAccessFile random2=new RandomAccessFile(f2,"rw");
		random.seek(random.length());
		long endPosition=random.getFilePointer();
		long mark=endPosition;
		int j=1;
		while((mark>=0)&&(j<=2)){
			mark--;
			random.seek(mark);
			n=random.readByte();
			if(n=='\n'){
				endPosition=random.getFilePointer();
				j++;
			}
		}
		random.seek(forthEnPosition);
		long startPoint=random.getFilePointer();
		while(startPoint<endPosition-1){
			n=random.readByte();
			random2.write(n);
			startPoint=random.getFilePointer();
		}
		random2.close();
		random.close();
		f1.delete();
		
		out.println("上传成功.<br/>");//window.close();opener.frm.Photo.value='../photo/"+fileName+"';
		out.println(f2);
	}catch(IOException e){
		out.println("文件上传失败");
	}
%>
</body>
</html>
