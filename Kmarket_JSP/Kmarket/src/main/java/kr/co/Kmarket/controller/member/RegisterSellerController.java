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

@WebServlet("/member/registerSeller.do")
public class RegisterSellerController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("type");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/registerSeller.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("type");
		
		String km_uid = req.getParameter("km_uid");
		String km_pass1 = req.getParameter("km_pass1");
		String kms_company = req.getParameter("kms_company");
		String kms_ceo = req.getParameter("kms_ceo");
		String kms_corp_reg = req.getParameter("kms_corp_reg");
		String kms_online_reg = req.getParameter("kms_online_reg");
		String kms_tel = req.getParameter("kms_tel");
		String kms_fax = req.getParameter("kms_fax");
		String kms_email = req.getParameter("kms_email");
		String kms_zip = req.getParameter("zip");
		String kms_addr1 = req.getParameter("addr1");
		String kms_addr2 = req.getParameter("addr2");
		String km_name = req.getParameter("km_name");
		String km_hp = req.getParameter("km_hp");
		String regip = req.getRemoteAddr();
		
		MemberDTO dto = new MemberDTO();
		dto.setUid(km_uid);
		dto.setPass(km_pass1);
		dto.setCompany(kms_company);
		dto.setCeo(kms_ceo);
		dto.setBizRegNum(kms_corp_reg);
		dto.setComRegNum(kms_online_reg);
		dto.setTel(kms_tel);
		dto.setFax(kms_fax);
		dto.setEmail(kms_email);
		dto.setZip(kms_zip);
		dto.setAddr1(kms_addr1);
		dto.setAddr2(kms_addr2);
		dto.setManager(km_name);
		dto.setManagerHp(km_hp);
		dto.setRegip(regip);
		
		service.insertMember(type, dto);
		
		resp.sendRedirect("/Kmarket/member/login.do");
	}
	
}
