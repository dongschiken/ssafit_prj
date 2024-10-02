package com.cdu.ssafit.member.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.util.DBUtil;
import com.mysql.cj.protocol.Resultset;

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
				member = new Member(id, password, memberName, email, regDate, status, phoneNum, postNum, roadAddress,
						jibunAddress, detailAddress);
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

}
