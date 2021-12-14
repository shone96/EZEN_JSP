package service;

public class ServiceException extends Exception {
	//방명록 예제(GetMessageListService.java, WriteMessageService.java, DeleteMessageService.java)에서 
	//사용할 서비스 클래스들이 DAO를 실행하는 도중 SQLException발생시 처리하는 클래스
	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}
	
	public ServiceException(String message) {
		super(message);
	}

}
