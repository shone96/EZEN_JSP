package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import connection.ConnectionProvider;
import dao.ArticleDao;
import loader.JdbcUtil;
import model.Article;
import model.WritingRequest;

public class WriteArticleService {
	private static WriteArticleService instance = new WriteArticleService();
	
	public static WriteArticleService getInstance() {
		return instance;
	}
	
	private WriteArticleService() {
		
	}
	
	public Article write(WritingRequest writingRequest) throws IdGenerationFailedException{
		int groupId = IdGenerator.getInstance().generateNextId("article");
		
		Article article = writingRequest.toArticle();
		
		//article�� group_id�� �ʱⰪ ���� 
		article.setGroupId(groupId);
		article.setPostingDate(new Date());
		DecimalFormat decimalFormat = new DecimalFormat("0000000000");
		article.setSequenceNumber(decimalFormat.format(groupId) + "999999");
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int articleId = ArticleDao.getInstance().insert(conn, article);//insert ���н� -1
			if(articleId == -1) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException("DB ���� �ȵ� : " + articleId);
			}
			
			conn.commit();
			
			article.setId(articleId);
			return article;			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB ���� : " + e.getMessage(), e);
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException e) {
					
				}
			}
			JdbcUtil.close(conn);
		}
	}

}
