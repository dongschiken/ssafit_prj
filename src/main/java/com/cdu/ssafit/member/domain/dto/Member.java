package com.cdu.ssafit.member.domain.dto;

import java.time.LocalDateTime;

public class Member {
	
	private int id;
	private String password;
	private String member_name;
	private String email;
	private LocalDateTime reg_date;
	private int status;
	
	public Member(int id, String password, String member_name, String email, LocalDateTime reg_date, int status) {
		super();
		this.id = id;
		this.password = password;
		this.member_name = member_name;
		this.email = email;
		this.reg_date = reg_date;
		this.status = status;
	}
	
	public Member() {
		super();
	}
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", password=" + password + ", member_name=" + member_name + ", email=" + email
				+ ", reg_date=" + reg_date + ", status=" + status + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getReg_date() {
		return reg_date;
	}
	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
