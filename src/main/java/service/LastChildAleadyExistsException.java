package service;

public class LastChildAleadyExistsException extends Exception{
	//ReplyArticleService클래스의 reply()메소드실행시 답변글을 99개 등록시 더 이상 답변글을 등록할수없을경우 예외처리 클래스
	public LastChildAleadyExistsException(String message) {
		super(message);
	}

}
