package service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import dao.ArticleDao;
import loader.JdbcUtil;
import model.Article;
import model.DeleteRequest;

public class DeleteArticleService {
	
	private static DeleteArticleService instance = new DeleteArticleService();
	
	public static DeleteArticleService getInstance() {
		return instance;
	}
	
	private DeleteArticleService() {
		
	}
	
	public void deleteArticle(DeleteRequest deleteRequest) throws ArticleNotFoundException, InvalidPasswordException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleCheckHelper checkHelper = new ArticleCheckHelper();
			checkHelper.checkExistsAndPassword(conn, deleteRequest.getArticleId(), deleteRequest.getPassword());//삭제할 게시글이 존재여부와 암호 일치여부 확인			
			
			ArticleDao articleDao = ArticleDao.getInstance();
			articleDao.delete(conn, deleteRequest.getArticleId());//articleDao의 delete메소드를 호출해 게시글 삭제			
			
			conn.commit();
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
