package com.cdu.ssafit.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.member.domain.dto.Review;
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
		Member member = memberRepository.selectMember(id, password);
		return member;
	}

	@Override
	public void insertMember(Member member) throws SQLException {

		memberRepository.insertMember(member);
	}

	@Override
	public boolean selectMemberById(String id) throws SQLException {
		return memberRepository.selectMemberById(id);
	}

	@Override
	public Map<Integer, Review> selectReviewList(String option, Member member) throws SQLException {
		
		if (option == null || option.equals("newest")) {
			option = "DESC";
		} else if (option.equals("oldest")) {
			option = "ASC";
		} else {

		}
		return memberRepository.selectReviewList(option, member);
	}

	@Override
	public void deleteReview(int id) throws SQLException {
		memberRepository.deleteReview(id);
	}

	@Override
	public Map<Integer, Board> selectBoardList(String option, Member member) throws SQLException {
		if (option == null || option.equals("newest")) {
			option = "DESC";
		} else if (option.equals("oldest")) {
			option = "ASC";
		} else {

		}
		return memberRepository.selectBoardList(option, member);
	}
}
