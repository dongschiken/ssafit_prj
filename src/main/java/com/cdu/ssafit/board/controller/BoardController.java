package com.cdu.ssafit.board.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board")
public class BoardController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
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

	private void doWriteForm(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void doWrite(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void doDetail(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	
	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	
	private void doRemove(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
}	
