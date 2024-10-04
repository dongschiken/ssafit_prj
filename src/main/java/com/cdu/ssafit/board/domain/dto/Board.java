package com.cdu.ssafit.board.domain.dto;

public class Board {
	private int id;	
	private String title;
	private String writer;
	private String content;
	private String workOutName;
	private int viewCnt;
	private String regDate;
	private String videoUrl;

	public Board() {
	}
	
	public Board(int id, String title, String writer, String content, String workOutName, int viewCnt, String regDate,
			String videoUrl) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.workOutName = workOutName;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.videoUrl = videoUrl;
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
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWorkOutName() {
		return workOutName;
	}

	public void setWorkOutName(String workOutName) {
		this.workOutName = workOutName;
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
				+ workOutName + ", viewCnt=" + viewCnt + ", regDate=" + regDate + ", videoUrl=" + videoUrl + "]";
	}

}
