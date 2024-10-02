package com.cdu.ssafit.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;

public interface BoardService {
	
	void write(Member member, Board board) throws SQLException;
	
	Board detail(int id);
	
	List<Board> list();
	
	void modify(int id);
	
	void delete(int id);
}
