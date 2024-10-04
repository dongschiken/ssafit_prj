package com.cdu.ssafit.review.domain.dto;

public class Review {
	private int id;
	private String content;
	private String regDate;
	private int boardId;
	private int memberSeq;
	private String memberName;

	public Review() {
	}

	public Review(int id, String content, String regDate, int boardId, int memberSeq, String memberName) {
		this.id = id;
		this.content = content;
		this.regDate = regDate;
		this.boardId = boardId;
		this.memberSeq = memberSeq;
		this.memberName = memberName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", content=" + content + ", regDate=" + regDate + ", boardId=" + boardId
				+ ", memberSeq=" + memberSeq + ", memberName=" + memberName + "]";
	}
}
