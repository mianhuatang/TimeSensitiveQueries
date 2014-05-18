package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessService;
import domain.Page;

public class ListSensitivityServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serletName = this.getServletName();
		Page page=null;
		String sql=null;
		String totalSql=null;
		String pagenum = request.getParameter("pagenum");
		BusinessService service=new BusinessService();
		sql="select * from sensitivity order by clusterID limit ?,?";
		totalSql="select count(*) from sensitivity ";
		page = service.getPageData(pagenum,request.getContextPath() + "/servlet/" + serletName,sql,totalSql);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/jsp/listsensitivity.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
