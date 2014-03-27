package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Page;

import service.impl.BusinessService;



public class ListClickServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			String id=request.getParameter("id");
			String pagenum = request.getParameter("pagenum");
			BusinessService service = new BusinessService();
			
			String serletName = this.getServletName();
			
			String sql="select * from clicks where queryID='"+id+"' limit ?,?";
			System.out.println(sql);
			String totalSql="select count(* ) from clicks where queryID='"+id+"' ";
			Page page = service.getPageData(pagenum,request.getContextPath() + "/servlet/" + serletName,sql,totalSql);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/WEB-INF/jsp/listclicksbyqueryid.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "查询失败！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
