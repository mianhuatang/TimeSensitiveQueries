<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示相应点击</title>
  </head>
  
  <body style="text-align: center;">
    <table width="100%" frame="border">
    	<tr>
    		<td>时间</td>
    		<td>点击的链接</td>
    		<td>位置</td>
 
    	</tr>
    	
    	<c:forEach var="c" items="${page.list}">
    		<tr>
	    		<td><c:out value="${c.time }" escapeXml="true"/></td>
	    		<td><c:out value="${c.url }" escapeXml="true"/></td> 
	    		<td><c:out value="${c.position }" escapeXml="true"/></td>
    		</tr>
    	</c:forEach>
    </table>
    <br/>
   	<%@include file="/public/page.jsp" %>
  </body>
</html>
