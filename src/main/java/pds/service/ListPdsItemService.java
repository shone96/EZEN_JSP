package pds.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionProvider;
import loader.JdbcUtil;
import pds.dao.PdsItemDao;
import pds.model.PdsItem;
import pds.model.PdsItemListModel;

public class ListPdsItemService {
	private static ListPdsItemService instance = new ListPdsItemService();
	
	public static ListPdsItemService getInstance() {
		return instance;
	}
	
	private ListPdsItemService() {
		
	}
	
	public static final int COUNT_PER_PAGE = 10;
	
	public PdsItemListModel getPdsItemList(int pageNumber) {
		if(pageNumber < 0) {
			throw new IllegalArgumentException("page number < 0 : " + pageNumber);
		}
		PdsItemDao pdsItemDao = PdsItemDao.getInstance();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int totalPdsItemCount = pdsItemDao.selectCount(conn); //��ü �Խñ� ����
			
			if(totalPdsItemCount == 0) {
				return new PdsItemListModel();
			}
			
			int totalPageCount = calculateTotalPageCount(totalPdsItemCount); //��ü ������ ����
			
			int firstRow = (pageNumber - 1) * COUNT_PER_PAGE + 1;
			int endRow = firstRow + COUNT_PER_PAGE - 1;
			
			if(endRow > totalPdsItemCount) {
				endRow = totalPdsItemCount;
			}
			
			List<PdsItem> PdsItemList = pdsItemDao.select(conn, firstRow, endRow);
			
			PdsItemListModel PdsItemListView = new PdsItemListModel(PdsItemList, pageNumber, totalPageCount, firstRow, endRow);
			
			return PdsItemListView;
		}catch(SQLException e) {
			throw new RuntimeException("DB ���� �߻� : " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//�˻���������� getPdsItemList�����ε�
	public PdsItemListModel getPdsItemList(int pageNumber, int searchn, String search) {
		if(pageNumber < 0) {
			throw new IllegalArgumentException("page number < 0 : " + pageNumber);
		}
		PdsItemDao pdsItemDao = PdsItemDao.getInstance();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int totalPdsItemCount = pdsItemDao.selectCount(search); //��ü �Խñ� ����
			
			if(totalPdsItemCount == 0) {
				return new PdsItemListModel();
			}
			
			int totalPageCount = calculateTotalPageCount(totalPdsItemCount); //��ü ������ ����
			
			int firstRow = (pageNumber - 1) * COUNT_PER_PAGE + 1;
			int endRow = firstRow + COUNT_PER_PAGE - 1;
			
			if(endRow > totalPdsItemCount) {
				endRow = totalPdsItemCount;
			}
			
			List<PdsItem> PdsItemList = pdsItemDao.select(firstRow, endRow, search);
			
			PdsItemListModel PdsItemListView = new PdsItemListModel(PdsItemList, pageNumber, totalPageCount, firstRow, endRow);
			
			return PdsItemListView;
		}catch(SQLException e) {
			throw new RuntimeException("DB ���� �߻� : " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//��ü �Խñ� �����κ��� ��ü ������ ������ �����ִ� �޼ҵ�
		private int calculateTotalPageCount(int totalPdsItemCount) {
			if(totalPdsItemCount == 0) {
				return 0;
			}
			int pageCount = totalPdsItemCount / COUNT_PER_PAGE;
			if(totalPdsItemCount % COUNT_PER_PAGE > 0) {
				pageCount ++;
			}
			return pageCount;
		}
		
}
