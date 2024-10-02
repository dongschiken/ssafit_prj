package com.cdu.ssafit.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.board.model.repository.BoardRepository;
import com.cdu.ssafit.board.model.repository.BoardRepositoryImpl;
import com.cdu.ssafit.member.domain.dto.Member;

public class BoardServiceImpl implements BoardService {
	
	public BoardRepository boardRepository;
	
	private static BoardService boardService = new BoardServiceImpl();
	public BoardServiceImpl() {
		boardRepository = BoardRepositoryImpl.getInstance();
	};
	public static BoardService getInstance() {
		return boardService;
	}

	@Override
	public void write(Member member, Board board) throws SQLException {
		boardRepository.insertBoard(member, board);
	}

	@Override
	public Board detail(int id) throws SQLException {
		boardRepository.updateViewCnt(id);
		Board board = boardRepository.selectOne(id);
		return board;
	}

	@Override
	public List<Board> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modify(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
