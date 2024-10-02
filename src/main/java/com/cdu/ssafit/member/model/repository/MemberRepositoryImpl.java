package com.cdu.ssafit.member.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.util.DBUtil;

public class MemberRepositoryImpl implements MemberRepository{
	
	private MemberRepositoryImpl() {}
	
	private static MemberRepository instance = new MemberRepositoryImpl();
	
	public static MemberRepository getInstance() {
		return instance;
	}

	@Override
	public Member selectMember(String id, String password) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = dbUtil.getConnection();
		
		return null;
	}

	@Override
	public void insertMember(Member member) throws SQLException {
		DBUtil dbUtil = DBUtil.getInstance();
		System.out.println(member);
		String sql = "INSERT INTO member(id, password, member_name, email, reg_date, post_num, jibun_addr, road_addr, detail_addr, phone_num)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		 
		 String parsedLocalDateTimeNow = member.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		try (Connection conn = dbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			
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
