package utils.algorithm.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import domain.IndexObject;

public class queryIndex {
	public static int MaxQuery=100;
	private Directory dir;
	private String indexPath="";
	public IndexWriter getWriter() throws Exception {  
        Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_40);  
        IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_40, analyzer);  
        return new IndexWriter(dir, iwc);  
    } 
	public void update(List<IndexObject> instances){
		IndexWriter writer=null;
		try {
			dir=FSDirectory.open(new File(indexPath));  
			writer=getWriter(); 
			List<Document>docs=new ArrayList<Document>();
			for(int i=0;instances!=null&&i<instances.size();i++){
				Document doc=new Document();
	        	doc.add(new StringField("queryID",instances.get(i).getQueryID(),Store.YES));
	        	doc.add(new StringField("sessionID",instances.get(i).getSessionID(),Store.YES));
	        	doc.add(new TextField("sessionDocument",instances.get(i).getSessionDocument(),Store.NO));
	        	docs.add(doc);
			}
			writer.addDocuments(docs);
			writer.commit();
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void update(IndexObject instance){
		IndexWriter writer=null;
		try {
			dir=FSDirectory.open(new File(indexPath));  
			writer=getWriter(); 
			List<Document>docs=new ArrayList<Document>();
			Document doc=new Document();
	        doc.add(new StringField("queryID",instance.getQueryID(),Store.YES));
	        doc.add(new StringField("sessionID",instance.getSessionID(),Store.YES));
	        doc.add(new TextField("sessionDocument",instance.getSessionDocument(),Store.NO));
	        docs.add(doc);
			writer.addDocuments(docs);
			writer.commit();
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<String> queryIDs(String queryStr){
		List<String> res=new ArrayList<String>();
		IndexReader reader=null;
		try {
			reader = DirectoryReader.open(dir);
			IndexSearcher searcher=new IndexSearcher(reader); 
			Analyzer analyzer  =   new  StandardAnalyzer(Version.LUCENE_40);   
			QueryParser qp=new QueryParser(Version.LUCENE_40, "sessionDocument", analyzer);   
	        Query query=null;
			try {
				query = qp.parse(queryStr);
			} catch (ParseException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        TopDocs topdocs=searcher.search(query, MaxQuery);
			ScoreDoc[] scoreDocs=topdocs.scoreDocs;  
            System.out.println("查询结果总数---" + topdocs.totalHits+"最大的评分--"+topdocs.getMaxScore());  
            for(int i=0; i < scoreDocs.length; i++) {  
                int doc = scoreDocs[i].doc;  
                Document document = searcher.doc(doc); 
                String id=document.get("queryID");
                res.add(id);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return res;
	}
	public List<String> queryDocuments(String queryStr){
		List<String> res=new ArrayList<String>();
		IndexReader reader=null;
		try {
			reader = DirectoryReader.open(dir);
			IndexSearcher searcher=new IndexSearcher(reader); 
			Term term=new Term("queryID",queryStr);
        	TermQuery query=new TermQuery(term);  //单个字节查询
            TopDocs topdocs=searcher.search(query, 1);  
			ScoreDoc[] scoreDocs=topdocs.scoreDocs;  
            System.out.println("查询结果总数---" + topdocs.totalHits+"最大的评分--"+topdocs.getMaxScore());  
            for(int i=0; i < scoreDocs.length; i++) {  
                int doc = scoreDocs[i].doc;  
                Document document = searcher.doc(doc); 
                String id=document.get("sessionDocument");
                res.add(id);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return res;
	}
}
