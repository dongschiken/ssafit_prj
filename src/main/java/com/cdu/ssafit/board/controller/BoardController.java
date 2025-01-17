package com.cdu.ssafit.board.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.board.model.service.BoardService;
import com.cdu.ssafit.board.model.service.BoardServiceImpl;
import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.review.domain.dto.Review;
import com.cdu.ssafit.review.service.ReviewService;
import com.cdu.ssafit.review.service.ReviewServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	
	private BoardService boardService;
	private ReviewService reviewService;
	public BoardController() {
		boardService = BoardServiceImpl.getInstance(); 
		reviewService = ReviewServiceImpl.getInstance();
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
			case "list":
				doList(req, resp);
				break;
			case "update":
				doUpdate(req, resp);
				break;
			case "delete":
				doRemove(req, resp);
				break;
			case "updateForm":
				doUdpateForm(req, resp);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
	
	/**
	 * 게시글 수정을 위한 Form으로 이동하는 메서드 입니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doUdpateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Board board = null;
		try {
			board = boardService.updateGetBoard(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/board/updateForm.jsp").forward(req, resp);
	}
	
	/**
	 * 게시글 작성을 위한 Form으로 이동하는 메서드 입니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doWriteForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/board/writeForm.jsp").forward(req, resp);
	}
	
	/**
	 * 게시글 작성을 위해 form으로 넘어온 parameter를 받아서 게시글을 db에 insert하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws SQLException
	 */
	private void doWrite(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		Member member = (Member) req.getSession().getAttribute("member");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String workOut = req.getParameter("workOut");
		String videoUrl = req.getParameter("videoUrl");
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWorkOutName(workOut);
		board.setVideoUrl(videoUrl);
		boardService.write(member, board);
		resp.sendRedirect(req.getContextPath() + "/main");
	}
	
	/**
	 * 게시글 상세보기 화면을 위해 게시글, 리뷰 정보를 req에 담아서 forward하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(req.getParameter("id"));
		Board board = boardService.detail(id);
		Map<Integer, Review> reviews = reviewService.list(id);
		req.setAttribute("board", board);
		req.setAttribute("reviews", reviews);
		System.out.println(reviews);
		req.getRequestDispatcher("/WEB-INF/board/detail.jsp").forward(req, resp);
	}
	
	/**
	 * 게시글 리스트를 반환해주는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		Map<Integer, Board> list = boardService.list();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/main.jsp").forward(req, resp);
	}
	
	/**
	 * 게시글 수정을 위해 수정 정보를 parameter로 받아 db에 update하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws SQLException
	 * @throws IOException
	 */
	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Member member = (Member) req.getSession().getAttribute("member");
		
		int id = Integer.parseInt(req.getParameter("id"));
		Board board = boardService.detail(id);
		
		if (!member.getMemberName().equals(board.getWriter())) {
			System.out.println("글을 수정할 권한이 없습니다.");
		} else {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String workOutName = req.getParameter("workOutName");
			
			System.out.println(workOutName);
			board.setTitle(title);
			board.setContent(content);
			board.setWorkOutName(workOutName);
			board.setWriter(member.getMemberName());
			boardService.update(board, member);
			resp.sendRedirect(req.getContextPath() + "/main");
		}
	}
	
	/**
	 * 게시글을 삭제하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws SQLException
	 */
	private void doRemove(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		int id = Integer.parseInt(req.getParameter("id"));
		boardService.delete(id);
		resp.sendRedirect(req.getContextPath() + "/main");
	}
}	
