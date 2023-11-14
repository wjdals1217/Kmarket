package kr.co.Kmarket.controller.cs;

import java.io.IOException;
import java.util.ArrayList;
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
import kr.co.Kmarket.dto.cs.CsCateDetailDTO;
import kr.co.Kmarket.service.CsArticleService;


@WebServlet("/cs/faq.do")
public class FaqListController extends HttpServlet{


	private static final long serialVersionUID = 4872308842394693534L;
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private CsArticleService service = CsArticleService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate"); 			
		String cateDetail  = req.getParameter("cateDetail"); // =aside
		String atype  = req.getParameter("type"); 			// = asdie 하위 cate
		

		List<CsArticleDTO> articles = service.selectArticlesFAQ(group, cateDetail);
		
		
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("type", atype);
		req.setAttribute("cateDetail", cateDetail);
		req.setAttribute("articles", articles);
		
		logger.debug("FaqListController cate " + cate);
		logger.debug("FaqListController atype " + atype);
		logger.debug("FaqListController cateDetail " + cateDetail);
		
		
		
		
	
		logger.debug("  " + articles);
		List<CsArticleDTO> cates 
		= service.selectArticlesFAQ(group, cateDetail);
		logger.info("cates size : " + cates.size());
		List<CsCateDetailDTO> types
			=service.selectCsCateDetailFAQ(cateDetail);
		
		
		
		logger.debug("selectArticlesFAQ 컨트롤러 faq 아티클스 데이터:  "+ cates);
		logger.debug("selectArticlesFAQ 컨트롤러 faq 타입 데이터:  "+ types);
		
		
		// BoardTypeDTO에 있는 주석을 일단 봐
		for(CsCateDetailDTO type : types) {
			List<CsArticleDTO> csBoard = new ArrayList<>();
			for(CsArticleDTO board : cates) {
				logger.info("type.getType() :" + type.getType());
				logger.info("board.getType() :" + board.getType());
				if(type.getType() == board.getType()) {
					csBoard.add(board);
					logger.info("1");
					logger.info("1");
				}else {
					logger.info("2");
				}
			}
			type.setDto(csBoard);
			logger.info("BoardList_get type.getDto() : " + type.getDto());
		}
		
		req.setAttribute("cs", cates);
		logger.info("BoardList_get cate : " + cates);
		req.setAttribute("types", types);
		logger.info("BoardList_get types : " + types);
	
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/faq/list.jsp");
		dispatcher.forward(req, resp);	
	
	}
	
	
	
	
}
