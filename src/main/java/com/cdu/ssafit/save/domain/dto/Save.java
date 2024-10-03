package com.cdu.ssafit.save.domain.dto;

public class Save {
	private int id;
	private int memberSeq;
	private int boardId;
	public Save() { }
	public Save(int id, int memberSeq, int boardId) {
		super();
		this.id = id;
		this.memberSeq = memberSeq;
		this.boardId = boardId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	@Override
	public String toString() {
		return "Save [id=" + id + ", memberSeq=" + memberSeq + ", boardId=" + boardId + "]";
	}
	 
	
}
