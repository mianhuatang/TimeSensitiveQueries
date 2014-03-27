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
  	<input type="text" name="content"/>
  	<input type="submit" value="查找">
  </form>
  
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
	    			<!-- <a href="${pageContext.request.contextPath }/servlet/UpdateCustomerUIServlet?id=${c.id }">修改</a>
	    			
	    			<a href="javascript:dodelete('${c.id }')">删除</a> -->
	    			
	    			
	    		</td>
    		</tr>
    	</c:forEach>
    </table>
    <br/>
   	<%@include file="/public/page.jsp" %>
  </body>
  <!--  <script type="text/javascript">
  	function dodelete(id)
  	{
  		var b = window.confirm("您确认删除吗？？");
  		if(b){
  			window.location.href="${pageContext.request.contextPath }/servlet/DeleteCustomerServlet?id=" + id;
  		}
  	}
  </script>-->
  
</html>
