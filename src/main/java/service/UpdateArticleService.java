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
			checkHelper.checkExistsAndPassword(conn, updateRequest.getArticleId(), updateRequest.getPassword());//�Խñ� ���� ���ο� ��ȣ ��ġ ���� Ȯ��
			
			Article updatedArticle = new Article();
			updatedArticle.setId(updateRequest.getArticleId());
			updatedArticle.setTitle(updateRequest.getTitle());
			updatedArticle.setContent(updateRequest.getContent());
			
			ArticleDao articleDao = ArticleDao.getInstance();
			int updateCount = articleDao.update(conn, updatedArticle);
			if(updateCount == 0) {
				throw new ArticleNotFoundException("�Խñ��� �������� �ʽ��ϴ�." +updateRequest.getArticleId());
			}
			
			Article article = articleDao.selectById(conn, updateRequest.getArticleId()); //update�� article��ü ����
			
			conn.commit();
			
			return article;
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
