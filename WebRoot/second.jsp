<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有查询</title>
  </head>
  
  <body style="text-align: center;">
  	<iframe src="${pageContext.request.contextPath }/secondtop.jsp" name="top" width="20%" height="100%" frameborder="0">
  	</iframe>
  	<iframe src="${pageContext.request.contextPath }/secondbottom.jsp" name="bottom" width="78%" height="100%" frameborder="0" >
  	</iframe>
  </body>
  
</html>
