package madvirus.gallery;

import java.sql.Timestamp;

public class Theme {
	//�ڹٺ� Ŭ����
	//THEME_MESSAGE ���̺�
	private int id; //�۹�ȣ
	private int groupId; // ���α۰� �亯�� �����ִ� �׷��ȣ
	private int orderNo; //�� ����
	private int levels; //�亯�� ����
	private int parentId; //�亯���� ������ ��ȣ
	private Timestamp register; //���ۼ� ��¥
	private String name; //�� �ۼ���
	private String email; //�̸���
	private String image; //���ε��� �̹��� ���� �̸�
	private String password; //��й�ȣ
	private String title; // ������
	//THEME_CONTENT ���̺�
	private String content; //�۳���
	//THEME_CONNTENTT ���̺�
	private String contentt; //�ڸ�Ʈ �۳���
	private String writer; //�ڸ�Ʈ �ۼ���
	private int ref_level; //�ڸ�Ʈ ����
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContentt() {
		return contentt;
	}
	public void setContentt(String contentt) {
		this.contentt = contentt;
	}
	public int getRef_level() {
		return ref_level;
	}
	public void setRef_level(int ref_level) {
		this.ref_level = ref_level;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Timestamp getRegister() {
		return register;
	}
	public void setRegister(Timestamp register) {
		this.register = register;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
}
