package com.cdu.ssafit.board.model.repository;

import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;

public interface BoardRepository {
	
	void insertBoard(Board board);
	
	Map<Integer, Board> selectBoard();
	
	Board selectBoardById(int id);

	void updateBoard(int id);
	
	void deleteBoard(int id);
}
