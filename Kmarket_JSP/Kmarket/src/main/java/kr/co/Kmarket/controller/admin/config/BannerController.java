package kr.co.Kmarket.controller.admin.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/config/banner.do")
public class BannerController extends HttpServlet{

	private static final long serialVersionUID = 6794319509506159818L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/config/banner.jsp");
		dispatcher.forward(req, resp);
		
	}
}
