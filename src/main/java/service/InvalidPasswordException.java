package service;

public class InvalidPasswordException extends Exception{
	//게시글 수정 과 삭제는 게시글 등록시 입력한 암호와 동일하지 않은 암호 입력시 발생되는 예외
	public InvalidPasswordException(String message) {
		super(message);
	}
}
