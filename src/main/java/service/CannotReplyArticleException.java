package service;

public class CannotReplyArticleException extends Exception{
	//ReplyArticleServiceŬ������ reply()�޼ҵ�� ����3 �� �ۿ� �亯 �� ����Ҷ��� ����ó�� Ŭ����
	public CannotReplyArticleException(String message) {
		super(message);
	}

}
