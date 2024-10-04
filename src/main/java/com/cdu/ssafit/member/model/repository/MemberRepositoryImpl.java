package com.cdu.ssafit.member.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.member.domain.dto.Review;
import com.cdu.ssafit.util.DBUtil;

public class MemberRepositoryImpl implements MemberRepository {

	private MemberRepositoryImpl() {
	}

	private static MemberRepository instance = new MemberRepositoryImpl();

	public static MemberRepository getInstance() {
		return instance;
	}
	
	@Override
	public Member selectMember(String id, String password) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		Member member = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id = ? AND password = ? ";
		try (Connection conn = dbUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int seq = rs.getInt("seq");
				id = rs.getString("id");
				password = rs.getString("password");
				String memberName = rs.getString("member_name");
				String email = rs.getString("email");
				String reg_date = rs.getString("reg_date");
				int status = rs.getInt("status");
				String postNum = rs.getString("post_num");
				String roadAddress = rs.getString("road_addr");
				String jibunAddress = rs.getString("jibun_addr");
				String detailAddress = rs.getString("detail_addr");
				String phoneNum = rs.getString("phone_num");
				LocalDateTime regDate = LocalDateTime.parse(reg_date,
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				member = new Member(seq, id, password, memberName, email, regDate, status, phoneNum, postNum, roadAddress, jibunAddress, detailAddress);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			rs.close();
		}
		return member;
	}

	@Override
	public void insertMember(Member member) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		System.out.println(member);
		String sql = "INSERT INTO member(id, password, member_name, email, reg_date, post_num, jibun_addr, road_addr, detail_addr, phone_num)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		String parsedLocalDateTimeNow = member.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		try (Connection conn = dbUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, parsedLocalDateTimeNow);
			pstmt.setString(6, member.getPostNum());
			pstmt.setString(7, member.getJibunAddress());
			pstmt.setString(8, member.getRoadAddress());
			pstmt.setString(9, member.getDetailAddress());
			pstmt.setString(10, member.getPhoneNum());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean selectMemberById(String id) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		String sql = "SELECT id FROM member WHERE id LIKE ?";
		ResultSet rs = null;
		String memberId = null;
		try (
			Connection conn = dbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberId = rs.getString("id");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			rs.close();
		}
		// id가 없으면 중복 X -> true
		return memberId == null;
	}

	@Override
	public String selectMemberBySeq(int seq) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		String sql = "SELECT member_name FROM member WHERE seq = ?";
		ResultSet rs = null;
		String memberName = null;
		try (
			Connection conn = dbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){			
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberName = rs.getString("member_name");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			rs.close();
		}
		// id가 없으면 중복 X -> true
		return memberName;
	}
	
	@Override
	public Map<Integer, Review> selectReviewList(String option, Member member) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		String sql = " SELECT review.id, review.content, board.title, board.id, review.member_seq, review.reg_date\r\n "
				+ " FROM review, board WHERE review.member_seq = ? AND board.id = review.board_id ORDER BY reg_date "+option;
		System.out.println(sql);
		Map<Integer, Review> map = null;
		try (
			Connection conn = dbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setInt(1, member.getSeq());
			try (ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) {
					map = new LinkedHashMap<Integer, Review>();
					do {
						int reviewId = rs.getInt(1);
						String content = rs.getString(2);
						String title = rs.getString(3);
						int boardId = rs.getInt(4);
						int memberSeq = rs.getInt(5);
						String regDate = rs.getString(6);
						Review review = new Review(reviewId, title, content, regDate, boardId, memberSeq);
						map.put(reviewId, review);
					} while (rs.next());
				}
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	@Override
	public void deleteReview(int id) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		String sql = "DELETE FROM review WHERE id = ?";
		try (
			Connection conn = dbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Map<Integer, Board> selectBoardList(String option, Member member) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		String sql = "SELECT * FROM board WHERE member_seq = ? ORDER BY reg_date "+option;
		Map<Integer, Board> map = null;
		try (
			Connection conn = dbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setInt(1, member.getSeq());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				map = new LinkedHashMap<>();
				do {
					Board board = new Board();
					board.setId(rs.getInt("id"));
					board.setTitle(rs.getString("title"));
					board.setContent(rs.getString("content"));
					board.setWorkOutName(rs.getString("workout_name"));
					board.setViewCnt(rs.getInt("view_cnt"));
					board.setRegDate(rs.getString("reg_date"));
					board.setVideoUrl(rs.getString("video_url"));
					map.put(board.getId(), board);
				} while (rs.next());
			}
		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	@Override
	public void deleteBoard(int id) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		String sql = "DELETE FROM board WHERE id = ?";
		try (
			Connection conn = dbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
}
