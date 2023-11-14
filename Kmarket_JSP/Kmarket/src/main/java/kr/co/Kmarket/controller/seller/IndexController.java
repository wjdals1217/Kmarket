package kr.co.Kmarket.controller.seller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.Kmarket.dto.member.MemberDTO;

@WebServlet(value= {"/seller", "/seller/index.do"})

public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 5925164803242121696L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/seller/index.jsp");
		dispatcher.forward(req, resp);
	}
}
