package model;

import java.util.ArrayList;
import java.util.List;

public class ArticleListModel { //�Խñ� ��� ȭ���� �����ϴ� �� �ʿ��� ������ �����ϴ� Ŭ����
	private List<Article> articleList; //ȭ�鿡 ������ �Խñ� ���
	private int requestPage; // ����ڰ� ��û�� ������ ��ȣ
	private int totalPageCount; //��ü ������ ����
	private int startRow; //���� �Խñ� ����� ������
	private int endRow; //���� �Խñ� ����� ����
	
	public ArticleListModel() {
		this(new ArrayList<Article>(), 0, 0, 0, 0);
	}
	
	public ArticleListModel(List<Article> articleList, int requestPageNumber, int totalPageCount, int startRow, int endRow) {
		this.articleList = articleList;
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}
	
	//�Խñ��� ����
	public boolean isHasArticle() {
		return ! articleList.isEmpty(); 
	}
	
	
}
