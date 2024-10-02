package com.cdu.ssafit.member.model.repository;

import java.sql.SQLException;

import com.cdu.ssafit.member.domain.dto.Member;

public interface MemberRepository {
	
	Member selectMember(String id, String password) throws SQLException;
	
	void insertMember(Member member) throws SQLException;
}
