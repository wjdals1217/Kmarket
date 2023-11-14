package kr.co.Kmarket.controller.seller.order;

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

import kr.co.Kmarket.dto.OrderDTO;
import kr.co.Kmarket.dto.SearchDTO;
import kr.co.Kmarket.service.OrderService;
import kr.co.Kmarket.service.PageService;

@WebServlet("/seller/order/delivery.do")
public class DeliveryController extends HttpServlet{

	private static final long serialVersionUID = 99783944487634693L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private PageService pageService = new PageService();
	private OrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pg = req.getParameter("pg");
		String search = req.getParameter("search");
		String search_text = req.getParameter("search_text");
		String seller = req.getParameter("seller");
		String sort = req.getParameter("sort");
		logger.debug("pg : "+pg);
		logger.debug("search : "+search);
		logger.debug("search_text : "+search_text);
		logger.debug("seller : "+seller);
		logger.debug("sort : "+sort);
		
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setSearch(search);
		searchDTO.setSearch_text(search_text);
		searchDTO.setCompany(seller);
		searchDTO.setSort(sort);
		
		
		// 현재 페이지 계산
		int currentPage = pageService.getCurrentPage(pg);
		
		// Limit 시작값 계산
		int start = pageService.getStartNum(currentPage);
		
		// 전체 게시물 개수 조회
		int total = orderService.selectCountTotal(searchDTO);
		logger.debug("total : "+total);
		
		// 마지막 페이지 번호 계산
		int lastPageNum = pageService.getLastPageNum(total);
		
		// 페이지 그룹 계산
		int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호 계산
		int pageStartNum = pageService.getPageStartNum(total, currentPage);
		
		// 현재 페이지 게시물 조회
		List<OrderDTO> orders = orderService.selectOrders(start, searchDTO);
		
		req.setAttribute("orders", orders);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("search", search);
		req.setAttribute("search_text", search_text);
		req.setAttribute("sort", sort);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/seller/order/delivery.jsp");
		dispatcher.forward(req, resp);
	}
}
