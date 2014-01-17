<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<jsp:directive.page import="com.method.upAndDown.upLoad.AllUpFile"/>
<html>
<head>
<title>Untitled Document</title>
</head>
<body>
<%
String tmppath = request.getRealPath("/upload/")+"/";
String path = tmppath;
if(true){
	List files = AllUpFile.FileUpload(request,path);
	for(int i=0;i<files.size();i++){
		out.println(files.get(i)+"<br/>");
	}
}else{
try {
        DiskFileUpload fu = new DiskFileUpload();
        // 设置最大文件尺寸，这里是4MB
        fu.setSizeMax(4194304);
        // 设置缓冲区大小，这里是4kb
        fu.setSizeThreshold(4096);
        // 设置临时目录：
        fu.setRepositoryPath(tmppath);

        // 得到所有的文件：
        List fileItems = fu.parseRequest(request);
        Iterator i = fileItems.iterator();
        // 依次处理每一个文件：
        while(i.hasNext()) {
            FileItem fi = (FileItem)i.next();
            // 获得文件名，这个文件名包括路径：
            String fileName = fi.getName();
            if(fileName!=null) {
           		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
           		fileName = new String(fileName.getBytes("utf-8"),"GBK");
            	if(fileName.indexOf(".")>0){
            	out.println("<br/>"+fileName);
                // 在这里可以记录用户和文件信息
                // 写入文件a.txt，你也可以从fileName中提取文件名：
                fi.write(new File(path ,fileName));
                }
            }
        }
        // 跳转到上传成功提示页面
    }
    catch(Exception e) {
        out.println("文件上传失败"+e.getMessage());
		System.out.println("文件上传失败=="+e.getMessage());
    }
}
 %>
</body>
</html>