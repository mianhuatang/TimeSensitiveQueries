<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有查询</title>
  </head>
  
  <body style="text-align: center;">
  <%String key="";
  	key=request.getParameter("content");
  	String time1="";
  	time1=request.getParameter("timestart");
  	String time2="";
  	time2=request.getParameter("timeend");
   %>
  	<iframe src="${pageContext.request.contextPath }/secondtop.jsp?content=<%=key %>&timestart=<%=time1 %>&timeend=<%=time2 %>" name="top" width="20%" height="100%" frameborder="0">
  	</iframe>
  	<iframe src="${pageContext.request.contextPath }/secondbottom.jsp?content=<%=key %>&timestart=<%=time1 %>&timeend=<%=time2 %>" name="bottom" width="78%" height="100%" frameborder="0" >
  	</iframe>
  </body>
  
</html>
