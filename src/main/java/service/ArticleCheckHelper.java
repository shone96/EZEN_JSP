package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ArticleDao;
import model.Article;

public class ArticleCheckHelper {
	//수정과 삭제시 암호를 체크하는 클래스
	public Article checkExistsAndPassword(Connection conn, int articleId, String password) throws SQLException, ArticleNotFoundException, InvalidPasswordException {
		ArticleDao articleDao = ArticleDao.getInstance();
		Article article = articleDao.selectById(conn, articleId);
		
		if(article == null) {
			throw new ArticleNotFoundException("게시글이 존재하지 않습니다 : " + articleId);
		}
		if(!checkPassword(article.getPassword(), password)) {
			throw new InvalidPasswordException("잘못된 암호");
		}
		return article;		
	}
	
	private boolean checkPassword(String realPassword, String userInputPassword) {
		if(realPassword == null) {
			return false;
		}
		if(realPassword.length() == 0) {
			return false;
		}
		return realPassword.equals(userInputPassword);
	}
}
