package com.cdu.ssafit.review.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.review.domain.dto.Review;
import com.cdu.ssafit.review.service.ReviewService;
import com.cdu.ssafit.review.service.ReviewServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/review")
public class ReviewController extends HttpServlet {

	private ReviewService reviewService;

	public ReviewController() {
		reviewService = ReviewServiceImpl.getInstance();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action = req.getParameter("action");
			switch (action) {
			case "write":
				doWrite(req, resp);
				break;
			case "list":
				doWrite(req, resp);
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

	private void doWrite(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Member member = (Member) req.getSession().getAttribute("member");
		int memberSeq = member.getSeq();
		String content = req.getParameter("content");
		int boardId = Integer.parseInt(req.getParameter("id"));

		Review review = new Review();
		review.setContent(content);
		review.setBoardId(boardId);
		review.setMemberSeq(memberSeq);
		reviewService.write(review);
		resp.sendRedirect(req.getContextPath() + "/board?action=detail&id=" + boardId);
	}

	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Member member = (Member) req.getSession().getAttribute("member");

		Board board = (Board) req.getAttribute("board");
		Review review = (Review) req.getAttribute("review");

		if (member.getSeq() != (review.getMemberSeq())) {
			System.out.println("댓글을 수정할 권한이 없습니다.");
		} else {
			String content = req.getParameter("content");

			review.setContent(content);
			review.setBoardId(board.getId());
			review.setMemberSeq(member.getSeq());
			reviewService.update(review);
			resp.sendRedirect(req.getContextPath() + "/main");
		}
	}

	private void doRemove(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Review review = (Review) req.getAttribute("review");
		Board board = (Board) req.getAttribute("board");

		reviewService.delete(review.getId());
		resp.sendRedirect(req.getContextPath() + "/board?action=detail?id=" + board.getId());
	}

}
