package service;

public class ArticleNotFoundException extends Exception{
	//ReadArticleService 클래스나 UpdateArticleService 클래스 등에서 게시글이 존재하지 않을 경우 생기는 예외 처리
	public ArticleNotFoundException(String msg) {
		super(msg);
	}
}
