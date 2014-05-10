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
			String serletName = this.getServletName();
			Page page=null;
			String sql=null;
			String totalSql=null;
			sql="select * from queries ";
			totalSql="select count(*) from queries ";
			if(content!=null&&content.trim().length()!=0&&content.trim()!="null"){
				sql+="where content='"+content+"'";
				totalSql+="where content='"+content+"'";
				if(timestart!=null&&timestart.trim().length()!=0&&timestart.trim()!="null"){
					sql+=" and time>=convert('"+timestart+"',Date)";
					totalSql+=" and time>=convert('"+timestart+"',Date)";
				}
				if(timeend!=null&&timeend.trim().length()!=0&&timeend.trim()!="null"){
					sql+=" and time<=convert('"+timeend+"',Date)";
					totalSql+=" and time<=convert('"+timeend+"',Date)";
				}
			}
			else if(timestart!=null&&timestart.trim().length()!=0&&timestart.trim()!="null"){
				sql+=" where time>=convert('"+timestart+"',Date)";
				totalSql+=" where time>=convert('"+timestart+"',Date)";
				if(timeend!=null&&timeend.trim().length()!=0&&timeend.trim()!="null"){
					sql+=" and time<=convert('"+timeend+"',Date)";
					totalSql+=" and time<=convert('"+timeend+"',Date)";
				}
			}
			else if(timeend!=null&&timeend.trim().length()!=0&&timeend.trim()!="null"){
				sql+=" and time<=convert('"+timeend+"',Date)";
				totalSql+=" and time<=convert('"+timeend+"',Date)";
			}
			sql+=" limit ?,?";
			page = service.getPageData(pagenum,request.getContextPath() + "/servlet/" + serletName,sql,totalSql);
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
