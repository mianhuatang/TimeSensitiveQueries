<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有查询</title>
  </head>
  
  <body style="text-align: left">
  	<form action="/TimeSensitiveQueries/servlet/AlgorithmServlet" method="post">
  		<input type="submit" value="开始预处理" name="test" />
  		<br/>
  		<a href="${pageContext.request.contextPath }/servlet/ListPreprocessingServlet" target="bottom">显示预处理结果</a>
  		<br/>
  		<br/>
  		<br/>
  			关键词相似度的权重：<input type="text" name="querysim" width="50px" /><br/>
  			URL相似度的权重：<input type="text" name="urlsim" width="50px"/><br/>
  			session相似度的权重：<input type="text" name="sessionsim"/><br/>
  			probase相似度的权重：<input type="text" name="probasesim"/><br/>
  			<input type="submit" value="开始聚类" name="test"/>
  		<br/>
  		<a href="${pageContext.request.contextPath }/servlet/ListClusterServlet" target="bottom">显示聚类结果</a>
  		<br/>
  		<br/>
  		<br/>
  		<input type="submit" value="开始过滤" name="test" />
  		<br/>
  	</form>
  	<br/>
  	
  </body>
  
</html>