package kr.co.Kmarket.controller.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
	날짜 : 2023/09/14
	이름 : 김무현
	내용 : Controller 기본셋팅
*/
@WebServlet("/product/complete.do")
public class CompleteController extends HttpServlet{

	private static final long serialVersionUID = -480497830900404005L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/complete.jsp");
		dispatcher.forward(req, resp);
	}

}
