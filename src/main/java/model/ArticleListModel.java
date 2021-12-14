package model;

import java.util.ArrayList;
import java.util.List;

public class ArticleListModel { //게시글 목록 화면을 구성하는 데 필요한 정보를 제공하는 클래스
	private List<Article> articleList; //화면에 보여줄 게시글 목록
	private int requestPage; // 사용자가 요청한 페이지 번호
	private int totalPageCount; //전체 페이지 개수
	private int startRow; //현재 게시글 목록의 시작행
	private int endRow; //현재 게시글 목록의 끝행
	
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
	
	//게시글이 유무
	public boolean isHasArticle() {
		return ! articleList.isEmpty(); 
	}
	
	
}
