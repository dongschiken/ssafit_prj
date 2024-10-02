package com.cdu.ssafit.save.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.save.domain.dto.Save;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/main")
public class SaveController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		switch (action) {
		case "save":
			doSave(req, resp);
			break;
		case "saveList":
			break;
		}

	}

	private void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 상태 확인
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("member");
		if (member == null) {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/member/loginForm.jsp");
			rd.forward(req, resp);			
		}
		// 로그인이 된 상태
		List<Save> saveList = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssafit_prj?serverTimezone=UTC", "ssafy", "ssafy");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO save (id, member_id, board_id) VALUES (?, ?, ?)";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Save save = new Save();
				save.setId(rs.getInt("id"));
				save.setMemberId(rs.getInt("member_id"));
				save.setBoardId(rs.getInt("board_id"));
				saveList.add(save);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO save (id, member_id, board_id) VALUES (?, ?, ?);";
		
		try {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
	}

	private void doMain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/main.jsp").forward(req, resp);
	}
}
