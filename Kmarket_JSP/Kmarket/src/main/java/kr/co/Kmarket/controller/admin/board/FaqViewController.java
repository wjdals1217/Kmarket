package kr.co.Kmarket.controller.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.service.CsArticleService;

@WebServlet("/admin/list.do")
public class FaqViewController extends HttpServlet{


	private static final long serialVersionUID = -6503963756892138917L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleService service = CsArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
	
	
}
