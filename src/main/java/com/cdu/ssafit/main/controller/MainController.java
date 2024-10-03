package com.cdu.ssafit.main.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.board.model.service.BoardService;
import com.cdu.ssafit.board.model.service.BoardServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainController extends HttpServlet {
	
	private BoardService boardService;
	public MainController() {
		boardService = BoardServiceImpl.getInstance(); 
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<Integer, Board> list = null;
		try {
			list = boardService.list();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("list", list);
		req.getRequestDispatcher("WEB-INF/main/main.jsp").forward(req, resp);
	}
}

