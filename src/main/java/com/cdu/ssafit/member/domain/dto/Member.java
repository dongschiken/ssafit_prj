package com.cdu.ssafit.member.domain.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class Member {
	
	private String id;
	private String password;
	private String memberName;
	private String email;
	private LocalDateTime regDate;
	private int status;
	private String phoneNum;
	private String postNum;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	
	public Member(String id, String password, String memberName, String email, LocalDateTime regDate, int status,
			String phoneNum, String postNum, String roadAddress, String jibunAddress, String detailAddress) {
		super();
		this.id = id;
		this.password = password;
		this.memberName = memberName;
		this.email = email;
		this.regDate = regDate;
		this.status = status;
		this.phoneNum = phoneNum;
		this.postNum = postNum;
		this.roadAddress = roadAddress;
		this.jibunAddress = jibunAddress;
		this.detailAddress = detailAddress;
	}
	
	public Member() {
		super();
	}
	
	public Member(String id, String password, String memberName, String email, LocalDateTime regDate,
			String phoneNum, String postNum, String roadAddress, String jibunAddress, String detailAddress) {
		this.id = id;
		this.password = password;
		this.memberName = memberName;
		this.email = email;
		this.regDate = regDate;
		this.phoneNum = phoneNum;
		this.postNum = postNum;
		this.roadAddress = roadAddress;
		this.jibunAddress = jibunAddress;
		this.detailAddress = detailAddress;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", memberName=" + memberName + ", email=" + email
				+ ", regDate=" + regDate + ", status=" + status + ", phoneNum=" + phoneNum + ", postNum=" + postNum
				+ ", roadAddress=" + roadAddress + ", jibunAddress=" + jibunAddress + ", detailAddress=" + detailAddress
				+ "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getJibunAddress() {
		return jibunAddress;
	}
	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
}
