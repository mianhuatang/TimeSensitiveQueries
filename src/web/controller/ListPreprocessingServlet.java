package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessService;

import domain.Page;

public class ListPreprocessingServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fromURL = request.getHeader("Referer");
		String[]parameterArray=fromURL.split("\\?")[1].split("\\&");
		String content=(parameterArray[0].split("\\=").length==2)?parameterArray[0].split("\\=")[1]:null;
		String timestart=(parameterArray[1].split("\\=").length==2)?parameterArray[1].split("\\=")[1]:null;
		String timeend=(parameterArray[2].split("\\=").length==2)?parameterArray[2].split("\\=")[1]:null;
		
		String serletName = this.getServletName();
		Page page=null;
		String sql=null;
		String totalSql=null;
		String pagenum = request.getParameter("pagenum");
		BusinessService service=new BusinessService();
		 totalSql="select count(*) from processing,queries where queries.id=processing.queryID  ";
		 sql="select processing.* from queries,processing where queries.id=processing.queryID      ";
		if(content!=null&&content.trim().length()!=0&&content.trim()!="null"){
			sql+="and processing.content='"+content+"'";
			totalSql+="and processing.content='"+content+"'";
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
		System.out.println(sql);
//		sql="select * from processing  limit ?,?";
//		totalSql="select count(*) from processing ";
		page = service.getPageData(pagenum,request.getContextPath() + "/servlet/" + serletName,sql,totalSql);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/jsp/listpreprocessing.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
