package com.cdu.ssafit.main.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.board.model.service.BoardService;
import com.cdu.ssafit.board.model.service.BoardServiceImpl;
import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.save.domain.dto.Save;
import com.cdu.ssafit.save.model.service.SaveService;
import com.cdu.ssafit.save.model.service.SaveServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainController extends HttpServlet {
	
	private BoardService boardService;
	private SaveService saveService;
	public MainController() {
		boardService = BoardServiceImpl.getInstance(); 
		saveService = SaveServiceImpl.getInstance();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<Integer, Board> list = null;
		List<Save> saveList = null;
		Member member = (Member)req.getSession().getAttribute("member");
		if(member != null) {
			try {
				saveList = saveService.selectSaves(member.getSeq());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			list = boardService.list();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(saveList);
		req.setAttribute("saveList", saveList);
		req.setAttribute("list", list);
		req.getRequestDispatcher("WEB-INF/main/main.jsp").forward(req, resp);
	}
}

