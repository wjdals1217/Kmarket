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

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import kr.co.Kmarket.dto.FileDTO;
import kr.co.Kmarket.dto.cs.CsArticleDTO;
import kr.co.Kmarket.dto.cs.CsCateDetailDTO;
import kr.co.Kmarket.service.CsArticleService;
import kr.co.Kmarket.service.FileService;


@WebServlet("/cs/write.do")
public class CsWriteController extends HttpServlet{
	
	private static final long serialVersionUID = 1950905414750457227L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsArticleService aService = CsArticleService.INSTANCE;
	private FileService fService = new FileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String group = req.getParameter("group");
		String type = req.getParameter("type");
		String cateDetail = req.getParameter("cateDetail");
		
		
		
		List<CsCateDetailDTO> cateDto = aService.selectCsWriteCate(cateDetail);
		logger.debug("cateDto 값이 나왔다~~~~~");
		logger.debug("cateDto 값이 나왔다~~~~~");
		
		
		
		req.setAttribute("group", group);
		req.setAttribute("type", type);
		req.setAttribute("cateDetail", cateDetail);
		req.setAttribute("cateDto", cateDto);
		
	
		
		
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/write.jsp");
		dispatcher.forward(req, resp);	
		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일 업로드 
		String path = aService.getPath(req, "/upload");
		MultipartRequest mr = aService.uploadFile(req, path);
		
		// 폼 데이터 수신
		String group   = mr.getParameter("group");
		String selectDetailView   = mr.getParameter("selectDetailView");
		String cateDetail   = mr.getParameter("cateDetail");
		String type   = mr.getParameter("type");
		String title   = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer  = mr.getParameter("writer");
		String uLevel  = mr.getParameter("uLevel");
		String oriName   = mr.getOriginalFileName("file");
		String regip   = req.getRemoteAddr();
		
		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("oName : " + oriName);
		logger.debug("regip : " + regip);
		logger.debug("regip : " + regip);
		logger.debug("selectDetailView : " + selectDetailView);
				
		
	    if ("탈퇴".equals(selectDetailView) || "가입".equals(selectDetailView) || "회원정보".equals(selectDetailView)|| "로그인".equals(selectDetailView)) {
	        selectDetailView = "member";
	    } else if ("취소".equals(selectDetailView) || "반품".equals(selectDetailView) || "교환".equals(selectDetailView)) {
	        selectDetailView = "cancle";
	    } else if("이벤트".equals(selectDetailView)|| "발표".equals(selectDetailView)) {
	        selectDetailView = "event"; 
	    } else if("배송".equals(selectDetailView)) {
	    	selectDetailView = "delivery"; 
	    } else if("위해상품".equals(selectDetailView)) {
	    	selectDetailView = "harzard"; 
	    } else if("안내".equals(selectDetailView)|| "점검".equals(selectDetailView)) {
	    	selectDetailView = "service"; 
	    } else if("여행".equals(selectDetailView)|| "숙박".equals(selectDetailView)|| "항공".equals(selectDetailView)) {
	    	selectDetailView = "travel"; 
	    } else if("안전거래".equals(selectDetailView)) {
	    	selectDetailView = "safe"; 
	    } else if("주문".equals(selectDetailView)|| "결제".equals(selectDetailView)) {
	    	selectDetailView = "order"; 
	    } else if("쿠폰".equals(selectDetailView)) {
	    	selectDetailView = "coupon"; 
	    }
	
	
	
		
		
		//DTO생성
		CsArticleDTO dto = new CsArticleDTO();
		dto.setCateDetail(selectDetailView);
		dto.setType(type);
		dto.setGroup(group);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oriName);
		dto.setWriter(writer);
		dto.setRegip(regip);
		dto.setuLevel(Integer.parseInt(uLevel));

		
		FileDTO fdto = null;
		
		//파일명 수정 및 파일 Insert
		if(oriName != null) {
			
			String sName = aService.renameToFile(req, path, oriName);
			
			// 파일 Insert
			fdto = new FileDTO();
			
			fdto.setOriname(oriName);
			fdto.setNewname(sName);
		
		}
		
		
		
		//글 Insert, file
		int no = aService.insertArticle(dto, fdto);
		

		
		logger.debug("라이트 컨트롤러   group: " + group);
		logger.debug("라이트 컨트롤러   cateDetail : " + cateDetail);
		
		
		
		
		
		

		
		
		// 리다이렉트
		resp.sendRedirect("/Kmarket/cs/qna.do?group="+group+"&cateDetail="+cateDetail);
	}
}
