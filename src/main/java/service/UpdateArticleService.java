package service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import dao.ArticleDao;
import loader.JdbcUtil;
import model.Article;
import model.UpdateRequest;

public class UpdateArticleService {
	
	private static UpdateArticleService instance = new UpdateArticleService();
	
	public static UpdateArticleService getInstance() {
		return instance;
	}
	
	private UpdateArticleService() {
		
	}
	
	public Article update(UpdateRequest updateRequest) throws ArticleNotFoundException, InvalidPasswordException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleCheckHelper checkHelper = new ArticleCheckHelper();
			checkHelper.checkExistsAndPassword(conn, updateRequest.getArticleId(), updateRequest.getPassword());//게시글 존재 여부와 암호 일치 여부 확인
			
			Article updatedArticle = new Article();
			updatedArticle.setId(updateRequest.getArticleId());
			updatedArticle.setTitle(updateRequest.getTitle());
			updatedArticle.setContent(updateRequest.getContent());
			
			ArticleDao articleDao = ArticleDao.getInstance();
			int updateCount = articleDao.update(conn, updatedArticle);
			if(updateCount == 0) {
				throw new ArticleNotFoundException("게시글이 존재하지 않습니다." +updateRequest.getArticleId());
			}
			
			Article article = articleDao.selectById(conn, updateRequest.getArticleId()); //update된 article객체 구함
			
			conn.commit();
			
			return article;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 에러 발생 : " + e.getMessage(), e);
		}catch(ArticleNotFoundException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}catch(InvalidPasswordException e) {
			JdbcUtil.rollback(conn);
			throw e;
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
