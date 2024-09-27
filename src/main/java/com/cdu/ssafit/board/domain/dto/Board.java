package com.cdu.ssafit.board.domain.dto;

import java.security.Timestamp;

public class Board {
	int id;	
	String title;
	String content;
	String workOut;
	int viewCnt;
	Timestamp regDate;
	
	public Board() {
	}

	public Board(int id, String title, String content, String workOut, int viewCnt, Timestamp regDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.workOut = workOut;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getWorkOut() {
		return workOut;
	}

	public void setWorkOut(String workOut) {
		this.workOut = workOut;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + ", workOut=" + workOut + ", viewCnt="
				+ viewCnt + ", regDate=" + regDate + "]";
	}
}
