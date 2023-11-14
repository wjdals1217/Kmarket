package kr.co.Kmarket.controller.admin.board;

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
import kr.co.Kmarket.service.PageService;

@WebServlet("/admin/board/qna.do")
public class QnaController extends HttpServlet{

	private static final long serialVersionUID = -8040723935264663405L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleService articleService = CsArticleService.INSTANCE;
	private final PageService ps = new PageService();
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cateDetail = req.getParameter("cateDetail");
		String type = req.getParameter("type");
		String pg = req.getParameter("pg");
		
		logger.debug("QnaController 에서 에러나는데 이게맞냐? : "+cateDetail);
		logger.debug("QnaController 에서 에러나는데 이게맞냐? : "+ pg);
		logger.debug("QnaController 에서 에러나는데 이게맞냐? : "+ type);
		logger.debug("QnaController 에서 에러나는데 이게맞냐? : "+ group);
		
		
		logger.debug("cateDetail  : "+cateDetail);
		
		// 현재 페이지 번호
		int currentPage = ps.getCurrentPage(pg);
		
		logger.debug("qnalistController currentPage값: "+ currentPage);

		// 전체 게시물 갯수 
		int total = articleService.selectCountTotalFAQ(group, type);
		
		// 마지막 페이지 번호
		int lastPageNum = ps.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = ps.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = ps.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = ps.getStartNum(currentPage);
		
		
		// 현재 페이지 게시물 조회
		List<CsArticleDTO> articles = articleService.SelectQnaArticlesAll(group, start, cateDetail, type);
		
		req.setAttribute("group", group);
		req.setAttribute("type", type);
		req.setAttribute("articles", articles);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("cateDetail", cateDetail);
		
		logger.debug("카테디테일 ㄱㄱㄱㄱㄱㄱ "+cateDetail);
		logger.debug("pageStartNum ㄱㄱㄱㄱㄱㄱ "+pageStartNum);
		logger.debug("result[1] ㄱㄱㄱㄱㄱㄱ "+result[1]);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/board/qna.jsp");
		dispatcher.forward(req, resp);
	}
}
