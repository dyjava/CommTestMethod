<%@ page import="java.io.*" %>
<jsp:directive.page import="com.method.upAndDown.down.DownFile"/>
<%
if(true){
	String path = request.getParameter("path");
	DownFile.smartDownFile(this.getServletConfig(),request,response,path);
}else{
	String filepath = request.getParameter("path");
	File file = new File(filepath);
	String showFilename = file.getName();

	response.reset();
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setContentType("application/octet-stream;charset=utf-8");
	response.setHeader("Content-disposition",
	"attachment;filename=\"" + showFilename + "\"");
	
	FileInputStream fis = new FileInputStream(filepath);
	ServletOutputStream fout = response.getOutputStream();
	try{
		int read=0;
		while((read = fis.read())!=-1){
			fout.write(read);
		}
	}catch(IOException e){
		throw e;
	}finally{
		fis.close();
		fout.flush();
	}
}
%>
