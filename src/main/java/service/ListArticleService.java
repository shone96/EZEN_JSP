package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionProvider;
import dao.ArticleDao;
import loader.JdbcUtil;
import model.Article;
import model.ArticleListModel;

public class ListArticleService { //ArticleDao의 selectCount() 메소드와 select() 메소드를 이용해서 사용자가 요청한 페이지에 해당하는 게시글 목록을 구한뒤 
								  //ArticleListModel 객체에 목록을 담아 리턴
	private static ListArticleService instance = new ListArticleService();
	
	public static ListArticleService getInstance() {
		return instance;
	}
	
	private ListArticleService() {
		
	}
	
	public static final int COUNT_PER_PAGE = 10;
	
	public ArticleListModel getArticleList(int requestPageNumber) {
		if(requestPageNumber < 0) {
			throw new IllegalArgumentException("page number < 0 : " + requestPageNumber);
		}
		ArticleDao articleDao = ArticleDao.getInstance();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int totalArticleCount = articleDao.selectCount(conn); //전체 게시글 개수
			
			if(totalArticleCount == 0) {
				return new ArticleListModel();
			}
			
			int totalPageCount = calculateTotalPageCount(totalArticleCount); //전체 페이지 개수
			
			int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1;
			int endRow = firstRow + COUNT_PER_PAGE - 1;
			
			if(endRow > totalArticleCount) {
				endRow = totalArticleCount;
			}
			
			List<Article> articleList = articleDao.select(conn, firstRow, endRow);
			
			ArticleListModel articleListView = new ArticleListModel(articleList, requestPageNumber, totalPageCount, firstRow, endRow);
			
			return articleListView;
		}catch(SQLException e) {
			throw new RuntimeException("DB 에러 발생 : " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//검색 기능을위한 getArticleList 오버로딩
	public ArticleListModel getArticleList(int requestPageNumber, int searchn, String search) {
		if(requestPageNumber < 0) {
			throw new IllegalArgumentException("page number < 0 : " + requestPageNumber);
		}
		ArticleDao articleDao = ArticleDao.getInstance();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int totalArticleCount = articleDao.selectCount(requestPageNumber, searchn, search);//오버로딩한 selectCount메소드를 호출해 전체 게시글 개수를 구함
			
			if(totalArticleCount == 0) {
				return new ArticleListModel();
			}
			
			int totalPageCount = calculateTotalPageCount(totalArticleCount); //전체 페이지 개수
			
			int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1;
			int endRow = firstRow + COUNT_PER_PAGE - 1;
			
			if(endRow > totalArticleCount) {
				endRow = totalArticleCount;
			}
			
			List<Article> articleList = articleDao.select(firstRow, endRow, searchn, search);
			
			ArticleListModel articleListView = new ArticleListModel(articleList, requestPageNumber, totalPageCount, firstRow, endRow);
			
			return articleListView;
		}catch(SQLException e) {
			throw new RuntimeException("DB 에러 발생 : " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//전체 게시글 개수로부터 전체 페이지 개수를 구해주는 메소드
	private int calculateTotalPageCount(int totalArticleCount) {
		if(totalArticleCount == 0) {
			return 0;
		}
		int pageCount = totalArticleCount / COUNT_PER_PAGE;
		if(totalArticleCount % COUNT_PER_PAGE > 0) {
			pageCount ++;
		}
		return pageCount;
	}
	
}
