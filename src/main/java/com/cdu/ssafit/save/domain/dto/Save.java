package com.cdu.ssafit.save.domain.dto;

public class Save {
	private int id;
	private String memberId;
	private int boardId;
	public Save() { }
	public Save(int id, String memberId, int boardId) {
		super();
		this.id = id;
		this.memberId = memberId;
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
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "Save [id=" + id + ", memberId=" + memberId + ", boardId=" + boardId + "]";
	}
	 
	
}
