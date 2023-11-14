package kr.co.Kmarket.controller.cs;

import java.io.IOException;
import java.util.List;

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

@WebServlet(value={"/cs/index.do"})
public class IndexController extends HttpServlet{


	private static final long serialVersionUID = 4872308842394693534L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleService service = CsArticleService.INSTANCE;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String group = req.getParameter("group");
		String cateDetail = req.getParameter("cateDetail");
		String type = req.getParameter("type");
		
		
		logger.debug("컨트롤러 그룹::::  "+group);
		
		// index notice 
		
		List<CsArticleDTO> notice = service.selectArticlesIndex(group);
		
		
		// index qna
		List<CsArticleDTO> qna = service.selectArticlesIndex(group);
		
		
		req.setAttribute("notice", notice);
		req.setAttribute("qna", qna);
		
		
		logger.debug("인덱스 컨트롤러 노티스 투스트링:  "+ notice.toString());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/index.jsp");
		dispatcher.forward(req, resp);	
	
	}
	
	
	
	
}
