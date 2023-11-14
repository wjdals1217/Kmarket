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

import kr.co.Kmarket.dto.cs.CsCateDetailDTO;
import kr.co.Kmarket.service.CsArticleService;



@WebServlet("/cs/writeCate.do")
public class CsWriteCateController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3935443053316299876L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CsCateDetailDTO dto = new CsCateDetailDTO();
	private final CsArticleService service = CsArticleService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("type");	
		String cateDetail = req.getParameter("cateDetail");

	
		

		List<CsCateDetailDTO> cateDto = service.selectCsWriteCate(cateDetail);
		
		/*
		 * resp.setContentType("application/json;charset=UTF-8"); Gson gson = new
		 * Gson(); String strJsons = gson.toJson(CateDto);
		 * resp.getWriter().print(strJsons); logger.info("ajax type Json" + strJsons);
		 */
		
		req.setAttribute("cateDto", cateDto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/write.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
