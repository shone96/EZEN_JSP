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
			checkHelper.checkExistsAndPassword(conn, deleteRequest.getArticleId(), deleteRequest.getPassword());//������ �Խñ��� ���翩�ο� ��ȣ ��ġ���� Ȯ��			
			
			ArticleDao articleDao = ArticleDao.getInstance();
			articleDao.delete(conn, deleteRequest.getArticleId());//articleDao�� delete�޼ҵ带 ȣ���� �Խñ� ����			
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB ���� �߻� : " + e.getMessage(), e);
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
