package com.cdu.ssafit.board.model.repository;

import java.sql.SQLException;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;

public interface BoardRepository {
	
	public void insertBoard(Member member, Board board) throws SQLException;
	
	public Map<Integer, Board> selectAll();
	
	public Board selectOne(int id) throws SQLException;

	public void updateBoard(int id);
	
	public void deleteBoard(int id);
	
	public void updateViewCnt(int id);
}
