package kr.co.Kmarket.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.Kmarket.service.member.MemberService;


@WebServlet("/member/authEmail.do")
public class AuthEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String division = req.getParameter("division"); //이메일 인증 - 회원가입, 아이디 찾기, 비밀번호 찾기 구분짓기 위해서
		String uid = req.getParameter("uid"); //비밀번호 찾기
		String name = req.getParameter("name"); //아이디 찾기
		String email = req.getParameter("email");

		int result = 0;
		int status = 0;
		
		if(division.equals("REGISTER")) {
			//회원가입 할 때 이메일 인증
			result = service.selectCountEmail(email);//이메일 중복체크
			if(result == 0) { //가입된 이메일로 인증할 수 없음
				status  = service.sendCodeByEmail(email);//이메일 발송 함수 호출
			}
		}else if(division.equals("FIND_ID")){
			//아이디 찾기 할 때 이메일 인증
			result = service.selectCountNameAndEmail(name, email);
			if(result == 1) { //가입된 이메일로 인증
				status  = service.sendCodeByEmail(email);//이메일 발송 함수 호출
			}
		}else if(division.equals("FIND_PASS")){
			//비밀번호 찾기 할 때 이메일 인증
			result = service.selectCountUidAndEmail(uid, email);
			
			if(result == 1) { //가입된 이메일로 인증
				status  = service.sendCodeByEmail(email);//이메일 발송 함수 호출
			}
		}else if(division.equals("MODIFY")){
			//회원정보 수정 할 때 이메일 인증
			result = service.selectCountEmail(email);//이메일 중복체크
			if(result == 0) { //가입된 이메일로 인증할 수 없음
			//회원정보 수정(이메일)할 때 이메일 인증
				status  = service.sendCodeByEmail(email);//이메일 발송 함수 호출
				
			}
		}
		
		//JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("status", status);
	
		//JSON 출력
		PrintWriter writer= resp.getWriter();
		writer.print(json.toString());
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String code = req.getParameter("code");
			
		int result = service.confirmCodeByEmail(code);
			
		//JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
	
		//JSON 출력
		PrintWriter writer= resp.getWriter();
		writer.print(json.toString());
	}
}
