package com.cdu.ssafit.review.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.review.domain.dto.Review;
import com.cdu.ssafit.util.DBUtil;

public class ReviewRepositoryImpl implements ReviewRepository {

	private DBUtil dbUtil = DBUtil.getInstance();

	private static ReviewRepository reviewRepository = new ReviewRepositoryImpl();

	private ReviewRepositoryImpl() {
	};

	public static ReviewRepository getInstance() {
		return reviewRepository;
	}

	@Override
	public void insertReview(Review review) throws SQLException {
		String sql = "insert into review(content, reg_date, board_id, member_seq) values(?, ?, ?, ?)";

		String regDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, review.getContent());
			pstmt.setString(2, regDate);
			pstmt.setInt(3, review.getBoardId());
			pstmt.setInt(4, review.getMemberSeq());
			pstmt.executeUpdate();
		}
	}

	@Override
	public Map<Integer, Review> selectAll(int boardId) throws SQLException {
		Map<Integer, Review> map = new HashMap<>();

		String sql = "SELECT review.id, review.content, review.reg_date, review.board_id, member_name FROM review, member WHERE review.member_seq = member.seq AND board_id = ?";

		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);
				
		) {
			pstmt.setInt(1, boardId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Review review = new Review();
				int id = rs.getInt("id");
				review.setId(id);
				review.setContent(rs.getString("content"));
				review.setRegDate(rs.getString("reg_date"));
				review.setBoardId(rs.getInt("board_id"));
				review.setMemberName(rs.getString("member_name"));
				map.put(id, review);
			}
			return map;
		}

	}

	@Override
	public void updateReview(Review review) throws SQLException {
		String sql = "update board set content = ?, reg_date = ?, board_id, member_seq = ?, where id = ?";

		String regDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			pstmt.setString(1, review.getContent());
			pstmt.setString(2, regDate);
			pstmt.setInt(3, review.getBoardId());
			pstmt.setInt(4, review.getMemberSeq());
			pstmt.setInt(5, review.getId());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void deleteReview(int id) throws SQLException {
		String sql = "delete from board where id = ?";
		try (Connection con = dbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

}
