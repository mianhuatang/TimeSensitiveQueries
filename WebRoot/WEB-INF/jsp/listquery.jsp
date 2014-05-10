<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

 
  <head>
    <title>显示所有查询</title>
    <script type="text/javascript" src="Calendar3.js"></script>
    <script type="text/javascript">
		function fun()
		{
			var content = document.getElementById('content').value;
			
			
		}
	</script>
  </head>
  
  <body style="text-align: center;">
  
  <form action="/TimeSensitiveQueries/servlet/ListQueryServlet">
  	
  	关键词：<input type="text" name="content" width="80%" id="key"/><br/>
  	<!--  <input type="submit" value="查找">-->
  	
  	
  	
  	<span>起止时间：</span> <span>从
	<input name="timestart" type="text" id="control_date" size="10"
                        maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />
	</span>
	<span>至
	<input name="timeend" type="text" id="control_date2" size="10"
                        maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" onblur='fun()'/>
	</span>

	<input type="submit" value="查找"/>
  	<!--<input type="button" name="processing" value="预处理" />-->
  </form>
  <%String key="";
  	key=request.getParameter("content");
  	String time1="";
  	time1=request.getParameter("timestart");
  	String time2="";
  	time2=request.getParameter("timeend");
   %>
  <!--  <a href="${pageContext.request.contextPath }/second.jsp?content=" onclick="this.href=this.href+document.getElementById('key').value+&timestart=+document.getElementById('control_date').value+&timeend=+document.getElementById('control_date2').value" target="_blank">挖掘时间敏感查询并计算周期</a>-->
 <!--  <a href="${pageContext.request.contextPath }/second.jsp?content=>" target="_blank"> 挖掘时间敏感查询并计算周期</a> -->
 <a href="${pageContext.request.contextPath }/second.jsp?content=<%=key %>&timestart=<%=time1 %>&timeend=<%=time2 %>" target="_blank"> 挖掘时间敏感查询并计算周期</a>
    <table width="100%" frame="border">
    	<tr>
    		<td>时间</td>
    		<td>查询内容</td>
    		<td>sessionID</td>
 
    	</tr>
    	<%int count=0; %>
    	<c:forEach var="c" items="${page.list}">
    	<%if(count%2==0){ %>
    		<tr style="background-color: #4F96C0">
    	<%}else{ %>	
    	<tr  >
    	<%} count++; %>
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
