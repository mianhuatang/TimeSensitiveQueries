<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有查询</title>
  </head>
  
  <body style="text-align: center;">
  <form action="/TimeSensitiveQueries/servlet/ListQueryServlet">
  	
  	关键词：<input type="text" name="content" width="80%"/>
  	<!--  <input type="submit" value="查找">-->
  	起始时间：<input type="text"  name="timestart"/>
	截止时间：<input type="text"  name="timeend"/>
	<input type="submit" value="查找"/>
  	<!--<input type="button" name="processing" value="预处理" />-->
  </form>
 <a href="${pageContext.request.contextPath }/second.jsp" target="_blank"> 挖掘时间敏感查询并计算周期</a> 
    <table width="100%" frame="border">
    	<tr>
    		<td>时间</td>
    		<td>查询内容</td>
    		<td>sessionID</td>
 
    	</tr>
    	
    	<c:forEach var="c" items="${page.list}">
    		<tr>
	    		<td><c:out value="${c.time }" escapeXml="true"/></td>
	    		<td><c:out value="${c.content }" escapeXml="true"/></td>
	    		<td><c:out value="${c.sessionID }" escapeXml="true"/></td>
	    		<td>
	    			<a href="${pageContext.request.contextPath }/servlet/ListClickServlet?id=${c.id } " target="right">查看点击信息</a>
	    		</td>
    		</tr>
    		
    	</c:forEach>
    </table>
    <br/>
   	<%@include file="/public/page.jsp" %>
  </body>
  
</html>
