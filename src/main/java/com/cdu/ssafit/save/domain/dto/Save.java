package com.cdu.ssafit.save.domain.dto;

import com.cdu.ssafit.board.domain.dto.Board;

public class Save {
	private int id;
	private int memberSeq;
	private int boardId;
	private Board board;
	public Save() { }
	
	
	// 
	public Save(int id, int boardId) {
		super();
		this.id = id;
		this.boardId = boardId;
	}
	
	public Save(int id, int memberSeq, int boardId) {
		super();
		this.id = id;
		this.memberSeq = memberSeq;
		this.boardId = boardId;
	}



	public Save(int id, int memberSeq, int boardId, Board board) {
		super();
		this.id = id;
		this.memberSeq = memberSeq;
		this.boardId = boardId;
		this.board = board;
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
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	@Override
	public String toString() {
		return "Save [id=" + id + ", memberSeq=" + memberSeq + ", boardId=" + boardId + "]";
	}
	
	 
	/*
	 * 
	 * 
	 */
}
