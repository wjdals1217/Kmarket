package kr.co.Kmarket.controller.admin.user;

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

import kr.co.Kmarket.dto.SearchDTO;
import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.service.PageService;
import kr.co.Kmarket.service.member.MemberService;

@WebServlet("/admin/user/user.do")
public class UserController extends HttpServlet{

	private static final long serialVersionUID = -5911717381881731732L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService memberService = MemberService.getInstance();
	private PageService pageService = new PageService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String level = req.getParameter("level");
		String pg = req.getParameter("pg");
		String search = req.getParameter("search");
		String search_text = req.getParameter("search_text");

		logger.debug("type : "+type);
		logger.debug("level : "+level);
		logger.debug("pg : "+pg);
		logger.debug("search : "+search);
		logger.debug("search_text : "+search_text);
		
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setSearch(search);
		searchDTO.setSearch_text(search_text);
		searchDTO.setLevel(level);
		
		logger.debug("searchDTO : "+searchDTO);
		
		// 현재 페이지 계산
		int currentPage = pageService.getCurrentPage(pg);
		logger.debug("currentPage : "+currentPage);
		
		// Limit 시작값 계산
		int start = pageService.getStartNum(currentPage);
		logger.debug("start : "+start);
		
		// 전체 게시물 개수 조회
		int total = memberService.selectCountTotal(searchDTO, type);
		logger.debug("total : "+total);
		
		// 마지막 페이지 번호 계산
		int lastPageNum = pageService.getLastPageNum(total);
		
		// 페이지 그룹 계산
		int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호 계산
		int pageStartNum = pageService.getPageStartNum(total, currentPage);
		
		List<MemberDTO> users = memberService.selectMembers(start, searchDTO, type);
		
		req.setAttribute("users", users);
		req.setAttribute("level", level);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("search", search);
		req.setAttribute("search_text", search_text);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/user/user.jsp");
		dispatcher.forward(req, resp);
	}
}
