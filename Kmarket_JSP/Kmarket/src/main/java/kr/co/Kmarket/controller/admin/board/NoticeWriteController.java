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

import kr.co.Kmarket.dto.cs.CsCateAsideDTO;
import kr.co.Kmarket.dto.cs.CsCateDetailDTO;
import kr.co.Kmarket.service.CsArticleService;

@WebServlet("/admin/notice/write.do")
public class NoticeWriteController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4556421890857105736L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsArticleService service = CsArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String group = req.getParameter("group");
		String cateDetail = req.getParameter("cateDetail");
		
		
		List<CsCateAsideDTO> cateDto = service.selectCsWriteCateNotice();
		List<CsCateDetailDTO> cateDetailDto = service.selectCsWriteCateDeNotice();
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/board/write.jsp?");
		dispatcher.forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
	
}
