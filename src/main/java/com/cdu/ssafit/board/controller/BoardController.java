package com.cdu.ssafit.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.board.model.service.BoardService;
import com.cdu.ssafit.board.model.service.BoardServiceImpl;
import com.cdu.ssafit.member.domain.dto.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board")
public class BoardController extends HttpServlet {

	private BoardService boardService;
	public BoardController() {
		boardService = BoardServiceImpl.getInstance(); 
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action = req.getParameter("action");
			switch (action) {
			case "writeForm":
				doWriteForm(req, resp);
				break;
			case "write":
				doWrite(req, resp);
				break;
			case "detail":
				doDetail(req, resp);
				break;
			case "update":
				doUpdate(req, resp);
				break;
			case "delete":
				doRemove(req, resp);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	private void doWriteForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/board/WEB-INF/board/writeForm").forward(req, resp);
	}

	private void doWrite(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		Member member = (Member) req.getSession().getAttribute("loginMember");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String workOut = req.getParameter("workOut");
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWorkOut(workOut);
		boardService.write(member, board);
		
		resp.sendRedirect(req.getContextPath() + "/WEB-INF/board?action=list");
	}

	private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Board board = boardService.detail(id);
		req.setAttribute("board", board);
		req.getRequestDispatcher("/board/detail.jsp").forward(req, resp);
	}
	
	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	
	private void doRemove(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
}	
