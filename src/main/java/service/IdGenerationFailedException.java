package service;

public class IdGenerationFailedException extends Exception {
	//IdGenerator의 generateNextId() 메소드의 예외 발생처리
	public IdGenerationFailedException(Throwable cause) {
		super(cause);
	}

}
