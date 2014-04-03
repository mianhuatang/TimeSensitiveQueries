<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>页头</title>
  </head>
  
  <body style="text-align: center;">
    
    <br/><br/>
   
    <jsp:forward page="/servlet/ListQueryServlet"/>
   
    <!--  <a href="${pageContext.request.contextPath }/servlet/AddCustomerUIServlet" target="body">添加客户</a>-->
    <!--<a href="${pageContext.request.contextPath }/servlet/ListQueryServlet" target="body">查看客户</a>-->
  </body>
</html>
