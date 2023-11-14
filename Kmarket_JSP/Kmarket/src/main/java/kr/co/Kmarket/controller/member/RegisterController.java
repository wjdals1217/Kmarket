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

import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.service.member.MemberService;

@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = req.getParameter("type");
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/register.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = req.getParameter("type");
		
		String km_uid = req.getParameter("km_uid");
		String km_pass1 = req.getParameter("km_pass1");
		String km_name = req.getParameter("km_name");
		String km_gender = req.getParameter("km_gender");
		String km_email = req.getParameter("km_email");
		String km_hp = req.getParameter("km_hp");
		String km_zip = req.getParameter("zip");
		String km_addr1 = req.getParameter("addr1");
		String km_addr2 = req.getParameter("addr2");
		String regip = req.getRemoteAddr();
		
		MemberDTO dto = new MemberDTO();
		dto.setUid(km_uid);
		dto.setPass(km_pass1);
		dto.setName(km_name);
		dto.setGender(km_gender);
		dto.setEmail(km_email);
		dto.setHp(km_hp);
		dto.setZip(km_zip);
		dto.setAddr1(km_addr1);
		dto.setAddr2(km_addr2);
		dto.setRegip(regip);
		
		service.insertMember(type, dto);
		
		resp.sendRedirect("/Kmarket/member/login.do");
	
	}
	
	
}
