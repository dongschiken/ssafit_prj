package com.cdu.ssafit.member.model.service;

import java.sql.SQLException;

import com.cdu.ssafit.member.domain.dto.Member;

public interface MemberService {
	
	Member selectMember(String id, String password) throws SQLException;
	
}
