package service;

public class LastChildAleadyExistsException extends Exception{
	//ReplyArticleServiceŬ������ reply()�޼ҵ����� �亯���� 99�� ��Ͻ� �� �̻� �亯���� ����Ҽ�������� ����ó�� Ŭ����
	public LastChildAleadyExistsException(String message) {
		super(message);
	}

}
