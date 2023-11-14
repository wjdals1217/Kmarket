package kr.co.Kmarket.controller.cs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dto.cs.CsArticleDTO;
import kr.co.Kmarket.service.CsArticleService;

@WebServlet("/cs/view.do")
public class CsViewController extends HttpServlet{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleService service = CsArticleService.INSTANCE; 
	private static final long serialVersionUID = 2008628929668163329L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String ano = req.getParameter("aNo");
		String group = req.getParameter("group");
		
		CsArticleDTO dto = service.selectArticle(ano);
		
		req.setAttribute("dto", dto);
	
	
		logger.debug("뷰 컨트롤러 aNO값~~"+dto.getaNo());
		logger.debug("뷰 컨트롤러 타이틀값~~"+dto.getTitle());
		logger.debug("뷰 컨트롤러 컨텐츠값~~"+dto.getContent());
		
		

		req.setAttribute("group", group);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/view.jsp");
		dispatcher.forward(req, resp);
	}
	
}
