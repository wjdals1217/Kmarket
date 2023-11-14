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
import netscape.javascript.JSObject;

@WebServlet("/member/checkUid.do")
public class CheckUidController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String km_uid = req.getParameter("km_uid");
		int result = service.selectCountUid(km_uid);
		logger.debug("km_uid : " + km_uid);
		
		
		//JSON생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		//JSON출력
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	
	}
}
