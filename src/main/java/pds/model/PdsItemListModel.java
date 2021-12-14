package pds.model;

import java.util.ArrayList;
import java.util.List;

public class PdsItemListModel {
	private List<PdsItem> pdsItemList; //ȭ�鿡 ������ �Խñ� ���
	private int requestPage; // ����ڰ� ��û�� ������ ��ȣ
	private int totalPageCount; //��ü ������ ����
	private int startRow; //���� �Խñ� ����� ������
	private int endRow; //���� �Խñ� ����� ����
	
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
