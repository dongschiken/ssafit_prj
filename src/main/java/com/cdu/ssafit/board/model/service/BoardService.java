package com.cdu.ssafit.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;

public interface BoardService {
	
	void write(Member member, Board board) throws SQLException;
	
	Board detail(int id) throws SQLException;
	
	Map<Integer, Board> list() throws SQLException;
	
	void update(Board board) throws SQLException;
	
	void delete(int id) throws SQLException;
}
