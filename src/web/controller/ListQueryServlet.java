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
			String timestart=request.getParameter("timestart");
			String timeend=request.getParameter("timeend");
			String pagenum = request.getParameter("pagenum");
			BusinessService service = new BusinessService();
			//System.out.println(timestart);
			String serletName = this.getServletName();
			Page page=null;
			String sql=null;
			String totalSql=null;
			if(content==null || content.trim().length()==0 ){
				sql="select * from queries  limit ?,?";
				totalSql="select count(*) from queries ";
				page = service.getPageData(pagenum,request.getContextPath() + "/servlet/" + serletName,sql,totalSql);
			}
			else if(content!=null && content.trim().length()!=0){
				sql="select * from queries where content='"+content+"' limit ?,?";
				totalSql="select count(*) from queries where content='"+content+"'";
				page = service.getPageData(pagenum,request.getContextPath() + "/servlet/" + serletName,sql,totalSql);
			}
			if(page!=null && timestart!=null && timestart.trim().length()!=0 && timeend!=null && timeend.trim().length()!=0){
				sql="select * from queries where time>=convert('"+timestart+"',Date) and time<=convert('"+timeend+"',Date) limit ?,?";
				//System.out.println(sql);
				totalSql="select count(* ) from queries where time>=convert('"+timestart+"',Date) and time<=convert('"+timeend+"',Date)";
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
