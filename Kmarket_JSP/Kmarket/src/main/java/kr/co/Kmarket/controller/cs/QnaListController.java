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
import kr.co.Kmarket.service.PageService;

@WebServlet("/cs/qna.do")
public class QnaListController extends HttpServlet{


	private static final long serialVersionUID = 4872308842394693534L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final CsArticleService cas = CsArticleService.INSTANCE;
	private final PageService ps = new PageService();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String group = req.getParameter("group");
		String cateDetail = req.getParameter("cateDetail");
		String type = req.getParameter("type");
		String pg = req.getParameter("pg");
		
		logger.debug("qnalistController group값: "+ group);
		logger.debug("qnalistController line40번 pg값: "+ pg);
		logger.debug("qnalistController line40번 cateDetail값: "+ cateDetail);
		
		// 현재 페이지 번호
		int currentPage = ps.getCurrentPage(pg);
		
		logger.debug("qnalistController currentPage값: "+ currentPage);

		int total = 0;
		
		// 전체 게시물 갯수 
		if(cateDetail.equals("all")) {
			
			total = cas.selectCountTotalCateAll(group, type, cateDetail);
			
		}else {
			
			total = cas.selectCountTotal(group, type, cateDetail);
			
		}
		logger.debug("qnalistController total: "+ total);
		
		
		// 마지막 페이지 번호
		int lastPageNum = ps.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = ps.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = ps.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = ps.getStartNum(currentPage);
		
		logger.debug("카테 디테일정보11======================="+ cateDetail);
		logger.debug("카테 디테일정보11======================="+ start);
		
		// 현재 페이지 게시물 조회
		List<CsArticleDTO> articles = cas.SelectQnaArticlesAll(group, start, cateDetail, type);
		
		req.setAttribute("group", group);
		req.setAttribute("type", type);
		req.setAttribute("articles", articles);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("cateDetail", cateDetail);
		
		logger.debug("카테 디테일정보22======================="+ cateDetail);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/list.jsp");
		dispatcher.forward(req, resp);	
	
	}
	
	
}
