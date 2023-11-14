package kr.co.Kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.service.member.MemberService;



@WebServlet("/member/findPass.do")
public class FindPassController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MemberService service = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 

		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/findPass.jsp");
		 dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uid = req.getParameter("uid"); //비밀번호변경 페이지에서 uid값만 나오기 때문에 email, auth 필요없음
		
		HttpSession session = req.getSession(); //페이지 사이에 데이터 공유할 때 session 사용(세션은 클라이언트가 가지고 있기 때문에 모든 자원, 서버에서 공유가 가능하니까)
		session.setAttribute("uid", uid);
		
		resp.sendRedirect("/Kmarket/member/findPassChange.do"); //get요청(FindChangeController)
	}
}
