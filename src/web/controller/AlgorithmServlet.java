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
		System.out.println(value);
		BusinessService service = new BusinessService();
		if(value!=null&&value.equals("开始预处理")){
			String totalSql="select count(*) from queries ";
			String sql="select * from queries  limit ?,?";
			int totalRecord=service.getTotalrecord(totalSql);
			totalRecord=1000;
			System.out.println(totalRecord);
			int currentIndex=0;
			int size=100;
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
				System.out.println(pQuerys.size());
				service.addPquerys(pQuerys);
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
