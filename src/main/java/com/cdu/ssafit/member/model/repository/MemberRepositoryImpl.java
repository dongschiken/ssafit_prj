package com.cdu.ssafit.member.model.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.util.DBUtil;

public class MemberRepositoryImpl implements MemberRepository{
	
	private MemberRepositoryImpl() {}
	
	private static MemberRepository instance = new MemberRepositoryImpl();
	
	public static MemberRepository getInstance() {
		return instance;
	}

	@Override
	public Member selectMember(String id, String password) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = dbUtil.getConnection();
		
		return null;
	}
	
	
	
	
}
