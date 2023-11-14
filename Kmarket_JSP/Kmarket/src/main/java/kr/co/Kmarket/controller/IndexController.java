package kr.co.Kmarket.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.seller.Cate1DTO;
import kr.co.Kmarket.dto.seller.Cate2DTO;
import kr.co.Kmarket.service.ProductService;
import kr.co.Kmarket.service.seller.Cate1Service;
import kr.co.Kmarket.service.seller.Cate2Service;

/* 
	날짜 : 2023/09/15
	이름 : 김무현
	내용 : 카테고리 출력
*/

@WebServlet(value= {"","/index.do"})  // 체크 로그인 필터 임시로 막아놓음(web.xml)
public class IndexController extends HttpServlet{

	
	private static final long serialVersionUID = -8413698060204945933L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Cate2Service Ct2Service = new Cate2Service();
	
	private ProductService pService = new ProductService();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<List<Cate2DTO>> categories = Ct2Service.selectCategories();
		
		List<ProductDTO> productsaside = pService.selectProductBest();
		
		List<ProductDTO> productshit = pService.selectProductHit();
		
		List<ProductDTO> productsScore = pService.selectProductsScore();
		
		List<ProductDTO> productsRdate = pService.selectProductsRdate();
		
		List<ProductDTO> productsDiscount = pService.selectProductsDiscount();
		
		
		logger.debug("productsaside : "+ productsaside);
		logger.debug("categories : "+ categories);
		logger.debug("productshit : "+ productshit);
		logger.debug("productsScore : "+ productsScore);
		logger.debug("productsRdate : "+ productsRdate);
		logger.debug("productsDiscount : "+ productsDiscount);
		
		//로그인 필터
		String success = req.getParameter("success");
		
		req.setAttribute("success", success);
		
		req.setAttribute("categories", categories);
		req.setAttribute("productsaside", productsaside);
		req.setAttribute("productshit", productshit);
		req.setAttribute("productsScore", productsScore);
		req.setAttribute("productsRdate", productsRdate);
		req.setAttribute("productsDiscount", productsDiscount);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
