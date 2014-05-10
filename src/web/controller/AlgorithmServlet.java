package web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Page;
import domain.ProcessingQuery;
import domain.Query;

import service.impl.BusinessService;
import utils.algorithm.cluster.DBSCANnew;
import utils.algorithm.extract.Extract;
import utils.algorithm.filter.ClusterFilterNoises;
import utils.algorithm.similarity.CalculateSimilarity;



public class AlgorithmServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String value=request.getParameter("test");
//		String content=(String) request.getAttribute("content");
		
//		String uri=request.getQueryString();
		String fromURL = request.getHeader("Referer");
		String[]parameterArray=fromURL.split("\\?")[1].split("\\&");
		String content=(parameterArray[0].split("\\=").length==2)?parameterArray[0].split("\\=")[1]:null;
		String timestart=(parameterArray[1].split("\\=").length==2)?parameterArray[1].split("\\=")[1]:null;
		String timeend=(parameterArray[2].split("\\=").length==2)?parameterArray[2].split("\\=")[1]:null;
		BusinessService service = new BusinessService();
		if(value!=null&&value.equals("开始预处理")){
			String totalSql="select count(*) from queries,query_status where queries.id=query_status.queryID and status=0  ";
			String sql="select queries.* from queries,query_status where queries.id=query_status.queryID and status=0     ";
			if(content!=null&&content.trim().length()!=0&&content.trim()!="null"){
				sql+="and content='"+content+"'";
				totalSql+="and content='"+content+"'";
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
			int totalRecord=service.getTotalrecord(totalSql);
//			totalRecord=1000;
//			System.out.println(totalRecord);
			int currentIndex=0;
			int size=5000;
			while(currentIndex<totalRecord){
				List <Query> data=service.getData(currentIndex, size, sql);
				List<ProcessingQuery> pQuerys=new ArrayList<ProcessingQuery>();
				for(Query query:data){
					ProcessingQuery pquery=Extract.extractTopicPart(query);
					if(pquery!=null){
						pQuerys.add(pquery);
						System.out.println("timePart:"+pquery.getTimePart());
					}
				}
//				System.out.println(pQuerys.size());
				service.addPquerys(pQuerys);
				StringBuffer queryIDStr=new StringBuffer();
				for(int i=0;pQuerys!=null&&i<pQuerys.size();i++){
					if(i!=0)
						queryIDStr.append(",");
					queryIDStr.append("'");
					queryIDStr.append(pQuerys.get(i).getQueryID());
					queryIDStr.append("'");
					
				}
				String updateSql="update query_status set status=1 where queryID in ("+queryIDStr+")";
				System.out.println(updateSql);
				service.update(updateSql, null);
				currentIndex+=size;
			}
		}
		else if(value!=null&&value.equals("开始聚类")){
			String sql="select * from processing limit ?,?";
			String totalSql="select count(*) from processing ";
			int totalRecord=service.getTotalrecord(totalSql);
			List<ProcessingQuery> pQuerys=service.getData(0, totalRecord, sql);
			CalculateSimilarity.CalSim(pQuerys);
			DBSCANnew dbscan=new DBSCANnew();
			try {
				dbscan.run(pQuerys);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(value!=null&&value.equals("开始过滤")){
			ClusterFilterNoises.filter();
		}
		request.getRequestDispatcher("/secondtop.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
