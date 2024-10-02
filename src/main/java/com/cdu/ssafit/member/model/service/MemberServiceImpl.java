package com.cdu.ssafit.member.model.service;

import java.sql.SQLException;

import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.member.model.repository.MemberRepository;
import com.cdu.ssafit.member.model.repository.MemberRepositoryImpl;

public class MemberServiceImpl implements MemberService {
	
	private static MemberService instance = new MemberServiceImpl();
	
	public static MemberService getInstance() {
		return instance;
	}

	private MemberRepository memberRepository;
	
	public MemberServiceImpl() {
		memberRepository = MemberRepositoryImpl.getInstance();
	}
	
	@Override
	public Member selectMember(String id, String password) throws SQLException {
		
		return null;
	}

	@Override
	public void insertMember(Member member) throws SQLException {
		memberRepository.insertMember(member);
	}
	
}
