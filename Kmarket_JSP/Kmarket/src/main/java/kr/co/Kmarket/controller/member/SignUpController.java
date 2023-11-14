package kr.co.Kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dao.member.TermsDAO;
import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.dto.member.TermsDTO;
import kr.co.Kmarket.service.member.MemberService;
import kr.co.Kmarket.service.member.TermsService;

@WebServlet("/member/signup.do")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private TermsService service = new TermsService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = req.getParameter("type");
		
		req.setAttribute("type", type);
		logger.debug("type : " + type );
		
		TermsDTO dto = service.selectTerms();
		req.setAttribute("dto", dto);
		logger.debug("dto : " + dto );
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/signup.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	
	
}
