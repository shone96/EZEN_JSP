package service;

public class ArticleNotFoundException extends Exception{
	//ReadArticleService Ŭ������ UpdateArticleService Ŭ���� ��� �Խñ��� �������� ���� ��� ����� ���� ó��
	public ArticleNotFoundException(String msg) {
		super(msg);
	}
}
