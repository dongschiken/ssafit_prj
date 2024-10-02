package com.cdu.ssafit.board.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;	

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.util.DBUtil;

public class BoardRepositoryImpl implements BoardRepository {
	
	private DBUtil dbUtil = DBUtil.getInstance();
	
	private static BoardRepository repository = new BoardRepositoryImpl();
	private BoardRepositoryImpl() {};
	public static BoardRepository getInstance() {
		return repository;
	}
	
	@Override
	public void insertBoard(Member member, Board board) throws SQLException {
		String sql = "insert into board(title, writer, content, workout) values(?, ?, ?, ?, ?)";
		
		
		String regDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

		try (
				Connection con = dbUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				) {
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getWorkOut());
			pstmt.setInt(5, board.getViewCnt());
			pstmt.setString(6, regDate);
			pstmt.executeUpdate();
		}
	}

	@Override
	public Board selectOne(int id) throws SQLException {
		String sql = "select * from board where id = ?";
		try (
				Connection con = dbUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				) {
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String content = rs.getString("content");
				String workOut = rs.getString("work_out");
				int viewCnt = rs.getInt("view_cnt");
				Board board = new Board();
				board.setId(id);
				board.setTitle(title);
				board.setWriter(writer);
				board.setContent(content);
				board.setWorkOut(workOut);
				board.setViewCnt(viewCnt);
				return board;
			}
			return null;
		}
	}

	@Override
	public Map<Integer, Board> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBoard(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateViewCnt(int id) {
		// TODO Auto-generated method stub
		
	}
}
