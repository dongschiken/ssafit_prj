package com.cdu.ssafit.member.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cdu.ssafit.board.domain.dto.Board;
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
			try {
				doLogin(req, resp);
			} catch (IOException | ServletException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "joinForm":
			doJoinForm(req, resp);
			break;
		case "join":
			try {
				doJoin(req, resp);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "logoutForm":
			doLogout(req, resp);
			break;
		case "myPage":
			doMemberInfo(req, resp);
			break;
		case "idCheck":
			try {
				doIdCheck(req, resp);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "myReview":
			try {
				doReviewList(req, resp);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "myBoard":
			try {
				doBoardList(req, resp);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "reviewDelete":
			try {
				doReveiwDelete(req, resp);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "boardDelete":
			try {
				doBoardDelete(req, resp);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	/**
	 * 사용자가 작성한 게시글을 삭제하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws SQLException
	 * @throws IOException
	 */
	private void doBoardDelete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int boardId = Integer.parseInt(req.getParameter("value"));
		memberService.deleteBoard(boardId);
		String path = req.getContextPath();
		resp.sendRedirect(path + "/member?action=myBoard&option=newest");
	}
	
	/**
	 * 사용자가 작성한 리뷰를 삭제하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws SQLException
	 * @throws IOException
	 */
	private void doReveiwDelete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int reviewId = Integer.parseInt(req.getParameter("value"));
		memberService.deleteReview(reviewId);
		String path = req.getContextPath();
		resp.sendRedirect(path + "/member?action=myReview&option=newest");
	}
	
	/**
	 * 사용자가 작성한 게시글 목록을 전부 가져옵니다.
	 * parameter로 option을 받아서 최신순, 과거순을 처리합니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void doBoardList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		// oldest, newest 받아서 최신순 과거순 처리
		String option = req.getParameter("option");
		Member member = (Member) req.getSession().getAttribute("member");
		Map<Integer, Board> boardList = null;
		boardList = memberService.selectBoardList(option, member);
		req.setAttribute("boardList", boardList);
		req.setAttribute("option", option);
		req.getRequestDispatcher("WEB-INF/member/myBoard.jsp").forward(req, resp);
	}
	
	/**
	 * 사용자가 작성한 리뷰 리스트를 가져옵니다. 
	 * parameter로 option을 받아서 최신순, 과거순을 처리합니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void doReviewList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		// oldest, newest 받아서 최신순 과거순 처리
		String option = req.getParameter("option");
		Member member = (Member) req.getSession().getAttribute("member");
		Map<Integer, Review> map = null;
		List<Review> reviewList = null;
		map = memberService.selectReviewList(option, member);
		if (map != null) {
			reviewList = map.values().stream().collect(Collectors.toList());
		}
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("option", option);
		req.getRequestDispatcher("WEB-INF/member/myReview.jsp").forward(req, resp);
	}
	
	/**
	 * 사용자가 입력한 id가 이미 데이터베이스에 있는 id인지 확인하는 메서드입니다.
	 * ajax를 통해 비동기 통신 처리합니다.
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws SQLException
	 */
	private void doIdCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String memberId = req.getParameter("memberId");
		boolean isDuplicate = true;
		isDuplicate = memberService.selectMemberById(memberId);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String jsonResponse = "{\"isDuplicate\": " + isDuplicate + "}";
		resp.getWriter().write(jsonResponse);

	}
	
	/**
	 * 사용자 정보를 통해 myPage를 보여주는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doMemberInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		req.getRequestDispatcher("WEB-INF/member/myPage.jsp").forward(req, resp);
	}
	
	/**
	 * 사용자가 로그아웃 버튼을 눌렀을 때 세션값을 없애주는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		String path = req.getContextPath();
		resp.sendRedirect(path + "/main?action=main");
	}
	
	/**
	 * 회원가입 화면에서 입력받은 데이터들을 받아서 member 테이블에 저장하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws SQLException
	 */
	private void doJoin(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
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
		memberService.insertMember(member);

		// 로그인으로 리다이렉트
		String path = req.getContextPath();
		resp.sendRedirect(path + "/member?action=loginForm");
	}
	
	/**
	 * 회원가입 폼으로 이동시키는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doJoinForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/member/join.jsp").forward(req, resp);
	}

	/**
	 * 회원이 로그인시 입력한 아이디, 비밀번호 가져와서 둘다 db의 데이터와 일치하는 경우
	 * 세션에 회원을 넣어서 main화면으로 보내는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	private void doLogin(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		// 아이디 비밀번호 가져와서 둘다 일치할 경우 로그인
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		Member member = null;
		member = memberService.selectMember(id, password);

		String path = req.getContextPath();
		if (member != null) {
			HttpSession session = req.getSession();
			session.setAttribute("member", member);
			resp.sendRedirect(path + "/main?action=main");
		} else {
			req.getRequestDispatcher("WEB-INF/member/login.jsp").forward(req, resp);
		}

	}
	
	/**
	 * 로그인 폼으로 이동시키는 메서드입니다.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/member/login.jsp").forward(req, resp);
	}

}
