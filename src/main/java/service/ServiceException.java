package service;

public class ServiceException extends Exception {
	//���� ����(GetMessageListService.java, WriteMessageService.java, DeleteMessageService.java)���� 
	//����� ���� Ŭ�������� DAO�� �����ϴ� ���� SQLException�߻��� ó���ϴ� Ŭ����
	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}
	
	public ServiceException(String message) {
		super(message);
	}

}
