<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示聚类信息</title>
  </head>
  
  <body style="text-align: center;">
  	<h1>聚类结果</h1>
    <table width="100%" frame="border">
    	<tr>
    		<td>clusterID</td>
    		<td>queryID</td>
    		<td>原始查询</td>
    		<td>预处理后的查询</td>
    		<td>sessionID</td>
    		<td>topicPart</td>
    		<td>timePart</td>
    	</tr>
    	
    	<c:forEach var="c" items="${page.list}">
    		<tr>
    			<td><c:out value="${c.clusterID }" escapeXml="true"/></td>
    			<td><c:out value="${c.queryID }" escapeXml="true"/></td>
	    		<td><c:out value="${c.content }" escapeXml="true"/></td>
	    		<td><c:out value="${c.pQuery }" escapeXml="true"/></td>
	    		<td><c:out value="${c.sessionID }" escapeXml="true"/></td>
	    		<td><c:out value="${c.topicPart }" escapeXml="true"/></td>
	    		<td><c:out value="${c.timePart }" escapeXml="true"/></td>
    		</tr>
    		
    	</c:forEach>
    </table>
    <br/>
   	<%@include file="/public/page.jsp" %>
  </body>
  
</html>
