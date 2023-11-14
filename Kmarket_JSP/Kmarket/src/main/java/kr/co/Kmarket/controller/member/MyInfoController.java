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

import com.google.gson.JsonObject;

import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.service.member.MemberService;



@WebServlet("/member/myInfo.do")
public class MyInfoController extends HttpServlet{


	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MemberService service = MemberService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/myInfo.jsp");
		dispatcher.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String kind = req.getParameter("kind"); //비밀번호 변경, 개인정보 수정, 회원탈퇴, 회원정보수정 
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		
		logger.debug("kind : " + kind);
		logger.debug("uid : " + uid);
		logger.debug("pass : " + pass);
		

		switch(kind) {
		case "WITHDRAW":
			int result1 = service.updateMemberForWithdraw(uid); //resp.sendredirect(로그인페이지로) 불가 ajax는 json으로 데이터 받아줘야 함
			
			JsonObject json1 = new JsonObject();
			json1.addProperty("result", result1);
			resp.getWriter().print(json1);
			
			break;
			
		case "PASSWORD":
			
			int result2 = service.updateMemberPass(uid, pass);
			
			JsonObject json2 = new JsonObject();
			json2.addProperty("result", result2);
			resp.getWriter().print(json2);
			
			break;
			
		case "MODIFY":
			MemberDTO dto = new MemberDTO();
			dto.setUid(uid);
			dto.setName(name);
			dto.setEmail(email);
			dto.setHp(hp);
			dto.setZip(zip);
			dto.setAddr1(addr1);
			dto.setAddr2(addr2);
			
			service.updateMember(dto);
			resp.sendRedirect("/Kmarket/member/logout.do");
			break;
		}
	
	}
}
