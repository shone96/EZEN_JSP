package service;

public class InvalidPasswordException extends Exception{
	//�Խñ� ���� �� ������ �Խñ� ��Ͻ� �Է��� ��ȣ�� �������� ���� ��ȣ �Է½� �߻��Ǵ� ����
	public InvalidPasswordException(String message) {
		super(message);
	}
}
