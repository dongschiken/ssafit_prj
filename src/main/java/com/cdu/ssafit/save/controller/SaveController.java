package com.cdu.ssafit.save.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.plaf.synth.SynthScrollBarUI;

import com.cdu.ssafit.member.domain.dto.Member;
import com.cdu.ssafit.save.domain.dto.Save;
import com.cdu.ssafit.save.model.service.SaveService;
import com.cdu.ssafit.save.model.service.SaveServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/save")
public class SaveController extends HttpServlet {
	// Saveservice 객체 가져오기
	private SaveService saveService = SaveServiceImpl.getInstance();
	
	/**
	 * 사용자가 찜과 관련한 이슈를 발생시켰을 때 호출된다.
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println(action);
		switch (action) {
		case "save":
			try {
				doSave(req, resp);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "saveList":
			doSaveList(req, resp);
			break;
		}
	}
	
	/**
	 * 사용자가 자신이 찜한 운동 리스트를 보고자 할 때 호출되는 메소드
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void doSaveList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 찜 리스트 보러 가는 기능
		
	}
	/**
	 * 사용자가 찜 버튼을 눌렀을 때 호출되는 메소드
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// 로그인 상태 확인
		HttpSession session = req.getSession();
//		Member member = (Member)session.getAttribute("member");
		Member member = new Member("1", "1234", "ddfd", "dfdfd@", null, "1234", "12312", "123123", "12312", "1212");
		if (member == null) {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/member/loginForm.jsp");
			rd.forward(req, resp);			
		}// 로그인 상태가 아니라면 로그인폼으로 이동한다.
		
		// 현재 요청으로는 운동 id가 넘어왔고, 방금 member 변수에 회원 정보가 넘어온 상황
		// 각각을 꺼낸다.
		String line = "";
//		int boardId = Integer.parseInt(req.getParameter("id"));
		StringBuilder sb = new StringBuilder();
//		System.out.println(req.getReader());
		req.getReader();
		BufferedReader br = req.getReader();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
		int memberId = member.getSeq();
		int boardId = jsonObject.get("boardId").getAsInt();
		// 찜 여부를 확인하러 감.
		boolean isSaved = saveService.isSaved(member.getSeq(), boardId);
		
		if (isSaved) { // 만약 찜한 상태라면, 해당 찜을 삭제하러 감.
            saveService.deleteSave(member.getSeq(), boardId);
        } else {	// 찜하지 않았다면 찜 객체를 생성하고 저장하러 감.
            Save save = new Save(0, member.getSeq(), boardId);  // id는 자동 생성되므로 0으로 설정
            saveService.insertSave(save);
        }
		System.out.println(isSaved);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"saved\":" + !isSaved + "}");
	}
}
