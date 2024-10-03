package com.cdu.ssafit.board.domain.dto;

public class Board {
	int id;	
	String title;
	int writer;
	String content;
	String workOut;
	int viewCnt;
	String regDate;
	String videoUrl;
	
	
	public Board() {
	}
	
	public Board(int id, String title, int writer, String content, String workOut, int viewCnt, String regDate,
			String videoUrl) {
		super();
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.workOut = workOut;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.videoUrl = videoUrl;
	}

	public Board(int id, String title, int writer, String content, String workOut, int viewCnt, String regDate) {
		this.id = id;
		this.title = title;
		this.writer = writer;
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
	
	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
		this.writer = writer;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content + ", workOut="
				+ workOut + ", viewCnt=" + viewCnt + ", regDate=" + regDate + ", videoUrl=" + videoUrl + "]";
	}

}
