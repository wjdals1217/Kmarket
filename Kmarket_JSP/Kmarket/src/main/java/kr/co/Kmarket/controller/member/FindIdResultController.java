package kr.co.Kmarket.controller.member;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.service.member.MemberService;



@WebServlet("/member/findIdResult.do")
public class FindIdResultController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	private MemberService service = MemberService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		MemberDTO member = service.selectMemberByNameAndEmail(name, email);
		req.setAttribute("member", member);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/findIdResult.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	
}
