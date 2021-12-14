package pds.model;

import java.util.ArrayList;
import java.util.List;

public class PdsItemListModel {
	private List<PdsItem> pdsItemList; //화면에 보여줄 게시글 목록
	private int requestPage; // 사용자가 요청한 페이지 번호
	private int totalPageCount; //전체 페이지 개수
	private int startRow; //현재 게시글 목록의 시작행
	private int endRow; //현재 게시글 목록의 끝행
	
	public PdsItemListModel() {
		this(new ArrayList<PdsItem>(), 0, 0, 0, 0);
	}
	
	public PdsItemListModel(List<PdsItem> pdsItemList, int requestPageNumber, int totalPageCount, int startRow, int endRow) {
		this.pdsItemList = pdsItemList;
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public List<PdsItem> getPdsItemList() {
		return pdsItemList;
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
	
	public boolean isHasPdsItem() {
		return ! pdsItemList.isEmpty();
	}
	
}
