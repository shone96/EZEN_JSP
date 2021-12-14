package service;

public class CannotReplyArticleException extends Exception{
	//ReplyArticleService클래스의 reply()메소드는 레벨3 인 글에 답변 글 등록할때의 예외처리 클래스
	public CannotReplyArticleException(String message) {
		super(message);
	}

}
