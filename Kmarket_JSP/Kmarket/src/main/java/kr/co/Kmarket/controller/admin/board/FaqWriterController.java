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
import kr.co.Kmarket.dto.cs.CsCateAsideDTO;
import kr.co.Kmarket.service.CsArticleService;

@WebServlet("/admin/faq/write.do")
public class FaqWriterController extends HttpServlet{

	
	private static final long serialVersionUID = 5211346667780461168L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleService service = CsArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String type = req.getParameter("type");
		String cateDetail = req.getParameter("cateDetail");
		
		logger.debug("cateDto 값이 나왔다~~~~~"+group);
		logger.debug("cateDto 값이 나왔다~~~~~"+type );
		logger.debug("cateDto 값이 나왔다~~~~~"+cateDetail);
		
		
		List<CsCateAsideDTO> cateDto = service.selectCsWriteCateFAQ();
		logger.debug("cateDto 값이 나왔다~~~~~");
		
		
		
		req.setAttribute("group", group);
		req.setAttribute("type", type);				
		req.setAttribute("cateDetail", cateDetail); 
		req.setAttribute("cateDto", cateDto);
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/board/write.jsp?group="+ group);
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		String group = req.getParameter("group");
		String writer = req.getParameter("writer");
		String type = req.getParameter("type");
		String uLevel = req.getParameter("uLevel");
		String selectDetailView  = req.getParameter("selectDetailView");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String regip = req.getRemoteAddr();
		
		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("group : " + group);
		logger.debug("type : " + type);
		logger.debug("uLevel : " + uLevel);
		logger.debug("regip : " + regip);
		logger.debug("selectDetailView : " + selectDetailView);
		
	    if ("회원".equals(selectDetailView)) {
	        selectDetailView = "member";
	    } else if ("취소/반품/교환".equals(selectDetailView)) {
	        selectDetailView = "cancle";
	    } else if("이벤트상품".equals(selectDetailView)) {
	        selectDetailView = "event"; 
	    } else if("배송".equals(selectDetailView)) {
	    	selectDetailView = "delivery"; 
	    } else if("위해상품".equals(selectDetailView)) {
	    	selectDetailView = "harzard"; 
	    } else if("고객서비스".equals(selectDetailView)) {
	    	selectDetailView = "service"; 
	    } else if("여행/숙박/항공".equals(selectDetailView)) {
	    	selectDetailView = "travel"; 
	    } else if("안전거래".equals(selectDetailView)) {
	    	selectDetailView = "safe"; 
	    } else if("주문/결제".equals(selectDetailView)) {
	    	selectDetailView = "order"; 
	    } else if("쿠폰/이벤트".equals(selectDetailView)) {
	    	selectDetailView = "coupon"; 
	    }
		
		
		CsArticleDTO dto = new CsArticleDTO();
		dto.setGroup(group);
		dto.setWriter(writer);
		dto.setType(type);
		dto.setuLevel(Integer.parseInt(uLevel));
		dto.setCateDetail(selectDetailView);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setRegip(regip);
	    
		logger.debug("dto카테 디테일? 왜안나옴? : " + dto.getCateDetail());
		service.insertArticleFAQ(dto);
		
		
		resp.sendRedirect("/Kmarket/admin/board/faq.do?group="+group+"&cateDetail="+selectDetailView+"&type="+type);
	}
	
	
}
