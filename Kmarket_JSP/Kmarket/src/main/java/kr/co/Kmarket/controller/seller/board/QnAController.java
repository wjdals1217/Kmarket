package kr.co.Kmarket.controller.seller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seller/board/qna.do")
public class QnAController extends HttpServlet{

	private static final long serialVersionUID = 1020919307990913806L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/seller/board/qna.jsp");
		dispatcher.forward(req, resp);
				
	}
}
