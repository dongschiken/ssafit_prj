package com.cdu.ssafit.member.model.repository;

import java.sql.SQLException;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.member.domain.dto.Review;

public interface MemberRepository {
	
	Member selectMember(String id, String password) throws SQLException;
	
	void insertMember(Member member) throws SQLException;
	
	boolean selectMemberById(String id) throws SQLException;
	
	Map<Integer, Review> selectReviewList(String option, Member member) throws SQLException;
	
	void deleteReview(int id) throws SQLException;
	
	Map<Integer, Board> selectBoardList(String option, Member member) throws SQLException;
}
