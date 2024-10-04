package com.cdu.ssafit.board.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.member.model.repository.MemberRepository;
import com.cdu.ssafit.member.model.repository.MemberRepositoryImpl;
import com.cdu.ssafit.util.DBUtil;

public class BoardRepositoryImpl implements BoardRepository {

	private DBUtil dbUtil = DBUtil.getInstance();

	private static BoardRepository boardRepository = new BoardRepositoryImpl();
	MemberRepository memberRepository = MemberRepositoryImpl.getInstance();

	private BoardRepositoryImpl() {
	};

	public static BoardRepository getInstance() {
		return boardRepository;
	}

	@Override
	public void insertBoard(Member member, Board board) throws SQLException {
		String sql = "insert into board(title, member_seq, content, workout_name, reg_date, video_url) values(?, ?, ?, ?, ?, ?)";

		String regDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
		
		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, board.getTitle());
			pstmt.setInt(2, member.getSeq());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getWorkOutName());
			pstmt.setString(5, regDate);
			pstmt.setString(6, board.getVideoUrl());
			pstmt.executeUpdate();
		}
	}

	@Override
	public Board selectOne(int id) throws SQLException {
		String sql = "select * from board where id = ?";
		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				int seq = rs.getInt("member_seq");
				String workOutName = rs.getString("workout_name");
				int viewCnt = rs.getInt("view_cnt");
				String writer = memberRepository.selectMemberBySeq(seq);
				String videoUrl = rs.getString("video_url");
				String regDate = rs.getString("reg_date");
				Board board = new Board();
				board.setId(id);
				board.setTitle(title);
				board.setWriter(writer);
				board.setContent(content);
				board.setWorkOutName(workOutName);
				board.setViewCnt(viewCnt);
				board.setVideoUrl(videoUrl);
				board.setRegDate(regDate);
				return board;
			}
			return null;
		}
	}

	@Override
	public Map<Integer, Board> selectAll() throws SQLException {
		Map<Integer, Board> map = new HashMap<>();

		String sql = "select * from board order by id desc";
		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int seq = rs.getInt("member_seq");
				
				String writer = memberRepository.selectMemberBySeq(seq);
				String content = rs.getString("content");
				String workOutName = rs.getString("workout_name");
				int viewCnt = rs.getInt("view_cnt");
				String videoUrl = rs.getString("video_url");
				Board board = new Board();
				board.setTitle(title);
				board.setWriter(writer);
				board.setContent(content);
				board.setWorkOutName(workOutName);
				board.setViewCnt(viewCnt);
				board.setVideoUrl(videoUrl);
				map.put(id, board);
			}
			return map;
		}
	}

	@Override
	public void updateBoard(Board board) throws SQLException {
		String sql = "update board set title = ?, content = ?, workout_name = ?, reg_date = ?, where id = ?";
		
		String regDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
		
		try (
				Connection con = dbUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				) {
			ResultSet rs = pstmt.executeQuery();
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWorkOutName());
			pstmt.setString(4, regDate);
			pstmt.setInt(5, board.getId());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void deleteBoard(int id) throws SQLException {
		String sql = "delete from board where id = ?";
		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

	@Override
	public void updateViewCnt(int id) throws SQLException {
		String sql = "update board set view_cnt = view_cnt + 1 where id = ?";
		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}
}
