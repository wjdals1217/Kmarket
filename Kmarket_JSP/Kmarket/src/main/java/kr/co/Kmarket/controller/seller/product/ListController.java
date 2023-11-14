package kr.co.Kmarket.controller.seller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.SearchDTO;
import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.service.PageService;
import kr.co.Kmarket.service.ProductService;

@WebServlet("/seller/product/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 7128712396351806024L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private PageService pageService = new PageService();
	private ProductService productService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pg = req.getParameter("pg");
		String search = req.getParameter("search");
		String search_text = req.getParameter("search_text");
		String seller = req.getParameter("seller");
		logger.debug("pg : "+pg);
		logger.debug("search : "+search);
		logger.debug("search_text : "+search_text);
		logger.debug("seller : "+seller);
		
		/*
		 * HttpSession session = req.getSession(); MemberDTO sessUser = (MemberDTO)
		 * session.getAttribute("sessUser"); String seller = sessUser.getUid();
		 * logger.debug("seller : "+seller);
		 */

		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setCompany(seller);
		searchDTO.setSearch(search);
		searchDTO.setSearch_text(search_text);
		
		// 현재 페이지 계산
		int currentPage = pageService.getCurrentPage(pg);
		
		// Limit 시작값 계산
		int start = pageService.getStartNum(currentPage);
		
		// 전체 게시물 개수 조회
		int total = productService.selectCountTotal(searchDTO);
		logger.debug("total : "+total);
		// 마지막 페이지 번호 계산
		int lastPageNum = pageService.getLastPageNum(total);
		
		// 페이지 그룹 계산
		int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호 계산
		int pageStartNum = pageService.getPageStartNum(total, currentPage);
		
		// 현재 페이지 게시물 조회
		List<ProductDTO> products = productService.selectProducts(start, searchDTO);
		
		req.setAttribute("products", products);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("search", search);
		req.setAttribute("search_text", search_text);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/seller/product/list.jsp");
		dispatcher.forward(req, resp);
	}
}
