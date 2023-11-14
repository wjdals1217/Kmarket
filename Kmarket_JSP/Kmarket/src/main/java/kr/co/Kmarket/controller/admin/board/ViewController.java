package kr.co.Kmarket.controller.admin.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dto.cs.CommentDTO;
import kr.co.Kmarket.dto.cs.CsArticleDTO;
import kr.co.Kmarket.service.CsArticleService;

@WebServlet("/admin/board/view.do")
public class ViewController extends HttpServlet{


	private static final long serialVersionUID = 3156879656372401125L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleService service = CsArticleService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ano = req.getParameter("aNo");
		
		CsArticleDTO dto = service.selectArticle(ano);
		CommentDTO Cdto = service.selectComments(ano);
		
		req.setAttribute("dto", dto);
		req.setAttribute("Cdto", Cdto);
	
		
	
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/board/view.jsp");
		dispatcher.forward(req, resp);
		
	
	}
	
	
	
	
}
