package kr.co.Kmarket.controller.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.Kmarket.dto.cs.CommentDTO;
import kr.co.Kmarket.service.CsArticleService;

@WebServlet("/admin/board/write.do")
public class AdminWriteController extends HttpServlet{

	private static final long serialVersionUID = -7883736132040545421L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleService service = CsArticleService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String aNo = req.getParameter("aNo");
		String writer = req.getParameter("name");
		String content = req.getParameter("content");
		String type = req.getParameter("type");
		
		logger.debug("admin review controller 값확인 : " + content);
		logger.debug("admin review controller 값확인 : " + aNo);
		logger.debug("admin review controller 값확인 : " + writer);
		
		int result = 0;
		
		switch(type) {
		case "REMOVE":
			result = service.deleteComment(aNo);
			break;
		case "MODIFY":
			result = service.updateComment(aNo, content);
			break;
		}
		
		// JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String aNo = req.getParameter("aNo");
		String writer = req.getParameter("name");
		String content = req.getParameter("content");
		
		logger.debug("admin review controller 값확인 : " + content);
		logger.debug("admin review controller 값확인 : " + aNo);
		logger.debug("admin review controller 값확인 : " + writer);
		
		
		CommentDTO dto = new CommentDTO();
		dto.setaNo(Integer.parseInt(aNo));
		dto.setWriter(writer);
		dto.setContent(content);
		
		CommentDTO comment = service.insertComment(dto);
		
		// Json 출력
		resp.setContentType("application/json;charset=UTF-8"); // <-- 이거해야 클라이언트로 전송되는 JSON 한글 안깨짐
		Gson gson = new Gson();
		String strJson = gson.toJson(comment);
		resp.getWriter().print(strJson);
		
		
	}
	
}
