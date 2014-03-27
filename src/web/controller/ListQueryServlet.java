package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessService;

import domain.Page;



public class ListQueryServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String content=request.getParameter("content");
			String pagenum = request.getParameter("pagenum");
			BusinessService service = new BusinessService();
			
			String serletName = this.getServletName();
			Page page=null;
			String sql=null;
			String totalSql=null;
			if(content==null||content.trim().length()==0){
				sql="select * from queries  limit ?,?";
				totalSql="select count(* ) from queries ";
				page = service.getPageData(pagenum,request.getContextPath() + "/servlet/" + serletName,sql,totalSql);
			}
			else{
				sql="select * from queries where content='"+content+"' limit ?,?";
				totalSql="select count(* ) from queries where content='"+content+"'";
				page = service.getPageData(pagenum,request.getContextPath() + "/servlet/" + serletName,sql,totalSql);
			}
			request.setAttribute("page", page);
			request.getRequestDispatcher("/WEB-INF/jsp/listquery.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "查询失败！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		
	}

}
