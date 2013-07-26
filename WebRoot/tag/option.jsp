<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://yicha.cn/optionTag/wap" prefix="option" %>
<jsp:directive.page import="com.method.tag.option.*"/>
<html>
  <head>
    <title>My JSP 'idx.jsp' starting page</title>
  </head>
  <%
  Map map = new HashMap() ;
  map.put("01","gg1") ;
  map.put("02","mm2") ;
  map.put("03","gg3") ;
//  map.put("04","gg4") ;
  request.setAttribute("map",map);
  System.out.println(map);
  
  Hashtable table = new Hashtable() ;
  table.put("01","gg1") ;
  table.put("02","mm2") ;
  table.put("03","gg3") ;
  table.put("04","gg4") ;

  request.setAttribute("table",table);
  
  List list = new ArrayList() ;
  for(int i=0;i<10;i++){
  	Option op = new Option() ;
  	op.setKey("a"+i) ;
  	op.setValue("b"+i) ;
  	list.add(op) ;
  }
    request.setAttribute("list",list);
  %>
  <script type="text/javascript">
  function out(v){
   alert("123"+v) ;
  }
  </script>
  <body>
    This is my JSP page. <br>
    <option:option name="opt" root="map" css="size='1' width='2000'" noSelectOpt="true" onchange="out(opt.value)" />
    <br/>
	<option:option name="op2" root="list" keyName="key" valueName="value"></option:option>
  </body>
</html>
