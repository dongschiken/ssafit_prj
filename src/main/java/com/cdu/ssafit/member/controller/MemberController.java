package com.cdu.ssafit.member.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.member.domain.dto.Review;
import com.cdu.ssafit.member.model.service.MemberService;
import com.cdu.ssafit.member.model.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member")
public class MemberController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private MemberService memberService;

	public MemberController() {
		memberService = MemberServiceImpl.getInstance();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		switch (action) {
		case "loginForm":
			doLoginForm(req, resp);
			break;
		case "login":
			doLogin(req, resp);
			break;
		case "joinForm":
			doJoinForm(req, resp);
			break;
		case "join":
			doJoin(req, resp);
			break;
		case "logoutForm":
			doLogout(req, resp);
			break;
		case "myPage":
			doMemberInfo(req, resp);
			break;
		case "idCheck":
			doIdCheck(req, resp);
			break;
		case "myReview":
			doReviewList(req, resp);
			break;
		case "myBoard":
			doBoardList(req, resp);
			break;
		}
	}

	private void doBoardList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// oldest, newest 받아서 최신순 과거순 처리
		String option = req.getParameter("option");
		Member member = (Member) req.getSession().getAttribute("member");
		Map<Integer, Review> map = null;
		List<Review> boardList = null;
		try {
			map = memberService.selectReviewList(option, member);
			if (map != null) {
				boardList = map.values().stream().collect(Collectors.toList());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("boardList", boardList);
		req.setAttribute("option", option);
		req.getRequestDispatcher("WEB-INF/member/myBoard.jsp").forward(req, resp);
	}

	private void doReviewList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// oldest, newest 받아서 최신순 과거순 처리
		String option = req.getParameter("option");
		Member member = (Member) req.getSession().getAttribute("member");
		Map<Integer, Review> map = null;
		List<Review> reviewList = null;
		try {
			map = memberService.selectReviewList(option, member);
			if (map != null) {
				reviewList = map.values().stream().collect(Collectors.toList());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("option", option);
		req.getRequestDispatcher("WEB-INF/member/myReview.jsp").forward(req, resp);
	}

	private void doIdCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String memberId = req.getParameter("memberId");
		boolean isDuplicate = true;
		try {
			isDuplicate = memberService.selectMemberById(memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String jsonResponse = "{\"isDuplicate\": " + isDuplicate + "}";
		resp.getWriter().write(jsonResponse);

	}

	private void doMemberInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		req.getRequestDispatcher("WEB-INF/member/myPage.jsp").forward(req, resp);
	}

	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		String path = req.getContextPath();
		resp.sendRedirect(path + "/main");
	}

	private void doJoin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String memberName = req.getParameter("name");
		String email = req.getParameter("email");
		LocalDateTime regDate = LocalDateTime.now();
		String phoneNum = String.format("%s-%s-%s", req.getParameterValues("phno1"), req.getParameterValues("phno2"),
				req.getParameterValues("phno3"));
		String postNum = req.getParameter("postNum");
		String roadAddress = req.getParameter("roadAddress");
		String jibunAddress = req.getParameter("jibunAddress");
		String detailAddress = req.getParameter("detailAddress");

		Member member = new Member(id, password, memberName, email, regDate, phoneNum, postNum, roadAddress,
				jibunAddress, detailAddress);
		try {
			memberService.insertMember(member);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 로그인으로 리다이렉트
		String path = req.getContextPath();
		resp.sendRedirect(path + "/member?action=loginForm");
	}

	private void doJoinForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/member/join.jsp").forward(req, resp);
	}

	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 아이디 비밀번호 가져와서 둘다 일치할 경우 로그인
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		Member member = null;
		try {
			member = memberService.selectMember(id, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String path = req.getContextPath();
		if (member != null) {
			HttpSession session = req.getSession();
			session.setAttribute("member", member);
			resp.sendRedirect(path + "/main");
		} else {
			req.getRequestDispatcher("WEB-INF/member/login.jsp").forward(req, resp);
		}
		
	
	}

	private void doLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/member/login.jsp").forward(req, resp);
	}

}
