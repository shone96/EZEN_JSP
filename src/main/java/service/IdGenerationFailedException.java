package service;

public class IdGenerationFailedException extends Exception {
	//IdGenerator�� generateNextId() �޼ҵ��� ���� �߻�ó��
	public IdGenerationFailedException(Throwable cause) {
		super(cause);
	}

}
