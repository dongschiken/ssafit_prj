package com.cdu.ssafit.member.domain.dto;

public class Review {
	private int id;
	private String boardTitle;
	private String content;
	private String regDate;
	private int boardId;
	private int memberSeq;
	
	public Review(int id, String boardTitle, String content, String regDate, int boardId, int memberSeq) {
		super();
		this.id = id;
		this.boardTitle = boardTitle;
		this.content = content;
		this.regDate = regDate;
		this.boardId = boardId;
		this.memberSeq = memberSeq;
	}
	
	public Review() {
		super();
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", boardTitle=" + boardTitle + ", content=" + content + ", regDate=" + regDate
				+ ", boardId=" + boardId + ", memberSeq=" + memberSeq + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

}
