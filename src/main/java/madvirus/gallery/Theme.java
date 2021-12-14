package madvirus.gallery;

import java.sql.Timestamp;

public class Theme {
	//자바빈 클래스
	//THEME_MESSAGE 테이블
	private int id; //글번호
	private int groupId; // 메인글과 답변글 묶어주는 그룹번호
	private int orderNo; //글 순서
	private int levels; //답변글 레벨
	private int parentId; //답변글의 상위글 번호
	private Timestamp register; //글작성 날짜
	private String name; //글 작성자
	private String email; //이메일
	private String image; //업로드한 이미지 파일 이름
	private String password; //비밀번호
	private String title; // 글제목
	//THEME_CONTENT 테이블
	private String content; //글내용
	//THEME_CONNTENTT 테이블
	private String contentt; //코멘트 글내용
	private String writer; //코멘트 작성자
	private int ref_level; //코멘트 순서
	
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
