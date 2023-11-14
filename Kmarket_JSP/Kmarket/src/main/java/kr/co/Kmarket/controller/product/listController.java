package kr.co.Kmarket.controller.product;

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

import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.seller.Cate2DTO;
import kr.co.Kmarket.service.PageService;
import kr.co.Kmarket.service.ProductService;
import kr.co.Kmarket.service.seller.Cate2Service;

/* 
	날짜 : 2023/09/14
	이름 : 김무현
	내용 : Controller 기본셋팅
*/
@WebServlet("/product/list.do")
public class listController extends HttpServlet{
	

	private static final long serialVersionUID = -9106083246555768149L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ProductService pService = new ProductService();
	
	private PageService pageService = new PageService();
	
	private Cate2Service Ct2Service = new Cate2Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodCate1 = req.getParameter("prodCate1");
		String prodCate2 = req.getParameter("prodCate2");
		String pg = req.getParameter("pg");
		String type = req.getParameter("type");
		logger.debug("prodCate1 : " + prodCate1);
		logger.debug("prodCate2 : " + prodCate2);
		logger.debug("pg : " + pg);
		logger.debug("type : " + type);
		
		if(prodCate2 == null) {
			prodCate2 = "0";
		}
		
		if(prodCate1 == null) {
			prodCate1 = "0";
		}
		
		
		// 현재 페이지 계산
		int currentPage = pageService.getCurrentPage(pg);
		
		// Limit 시작값 계산
		int start = pageService.getStartNum(currentPage);
		
		// 전체 게시물 개수 조회
		int total = pService.selectCountTotalProdCate(prodCate1, prodCate2);
		
		// 마지막 페이지 번호 계산
		int lastPageNum = pageService.getLastPageNum(total);
		
		// 페이지 그룹 계산
		int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호 계산
		int pageStartNum = pageService.getPageStartNum(total, currentPage);
		
		// 현재 페이지 게시물 조회
		List<ProductDTO> products = pService.selectProductsAll(prodCate1, prodCate2, start ,type);

		//aside 카테고리
		List<List<Cate2DTO>> categories = Ct2Service.selectCategories();
		
		List<ProductDTO> productsaside = pService.selectProductBest();
		
		logger.debug(products.toString());
		
		req.setAttribute("prodCate1", prodCate1);
		req.setAttribute("prodCate2", prodCate2);
		req.setAttribute("pg", pg);
		req.setAttribute("total", total);
		req.setAttribute("type", type);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("products", products);
		req.setAttribute("categories", categories);
		req.setAttribute("productsaside", productsaside);
		
		logger.debug("categories : "+ categories);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp");
		dispatcher.forward(req, resp);
	}

}
