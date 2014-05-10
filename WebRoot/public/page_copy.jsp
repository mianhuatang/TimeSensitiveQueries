<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
	String content=request.getParameter("content");
	String timestart=request.getParameter("timestart");
	String timeend=request.getParameter("timeend");
 %>

 当前第[${page.pagenum }]页&nbsp;&nbsp;&nbsp;
    
    <c:if test="${page.pagenum>1}">
    	<c:if test="<%=content!=null %>">
			<a href="${page.url }?pagenum=${page.pagenum-1 }&content=<%=content %>&timestart=<%=timestart %>&timeend=<%=timeend %>">上一页</a>
		</c:if>
		<c:if test="<%=content==null %>">
			<a href="${page.url }?pagenum=${page.pagenum-1 }">上一页</a>
		</c:if>
    	
    </c:if>
    &nbsp;
	<c:forEach var="pagenum" begin="${page.startPage}" end="${page.endPage}">
		<c:if test="<%=content!=null %>">
			[<a href="${page.url }?pagenum=${pagenum}&content=<%=content %>&timestart=<%=timestart %>&timeend=<%=timeend %>">${pagenum }</a>]
		</c:if>
		<c:if test="<%=content==null %>">
			[<a href="${page.url }?pagenum=${pagenum}">${pagenum }</a>]
		</c:if>
	</c:forEach>  
	&nbsp;
	
	<c:if test="${page.pagenum<page.totalpage}">
		<c:if test="<%=content!=null %>">
			<a href="${page.url }?pagenum=${page.pagenum+1 }&content=<%=content %>&timestart=<%=timestart %>&timeend=<%=timeend %>">下一页</a>
		</c:if>
		<c:if test="<%=content==null %>">
			<a href="${page.url }?pagenum=${page.pagenum+1 }">下一页</a>
		</c:if>
	</c:if>
	
	&nbsp;&nbsp;&nbsp;
	共[${page.totalpage }]页,共[${page.totalrecord}]纪录,
	
	<input type="text" style="width: 30px" id="pagenum">
	<input type="button" value=" GO " onclick="goWhich(<%=content %>,<%=timestart %>)">
	
	<script type="text/javascript">
		function goWhich(content){
			var pagenum = document.getElementById('pagenum').value;
			if(pagenum==null || pagenum==""){
				alert("请输入页码！");
				return;
			}
			
			if(!pagenum.match("\\d+")){
				alert("请输入数字！");
				input.value="";
				return;
			}
			
			if(pagenum<1 || pagenum>${page.totalpage}){
				alert("无效的页码！");
				input.value="";
				return;
			}
			window.location.href="${page.url }?pagenum=" + pagenum+"&content="+content;
		}
	</script>
	////<script type="text/javascript">
	//	function goWhich(input1){
	//		alert(input1.value)
		//	var pagenum = input1.value;
			//var content="";
			//content=input2;
			//var timestart="";
			///timestart=input3.value;
			//var timeend="";
			//timeend=input4.value;
			//if(pagenum==null || pagenum==""){
			//	alert("请输入页码！");
			//	return;
			//}
			
			//if(!pagenum.match("\\d+")){
			//	alert("请输入数字！");
			//	input.value="";
			//	return;
			//}
			
			//if(pagenum<1 || pagenum>${page.totalpage}){
		//		alert("无效的页码！");
			//	input.value="";
			//	return;
			//}
			//window.location.href="${page.url }?pagenum=" + pagenum;
			//var temp="${page.url }?pagenum="+pagenum+"&content="+content+"&timestart="+timestart+"timeend="+timeend;
			//alert(temp);
			//window.location.href=temp;
			//alert("${page.url }?pagenum=" + pagenum+"&content="+content +"&timestart="+timestart "+&timeend="+timeend);
			//window.location.href="${page.url }?pagenum=" + pagenum+"&content="+content +"&timestart="+timestart "+&timeend="+timeend;
		//}
	</script>
