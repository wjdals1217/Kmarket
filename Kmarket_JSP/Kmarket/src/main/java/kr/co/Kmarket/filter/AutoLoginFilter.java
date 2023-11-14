package kr.co.Kmarket.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dto.member.MemberDTO;
import kr.co.Kmarket.service.member.MemberService;



public class AutoLoginFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.getInstance();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
	    logger.info("AutoLoginFilter is processing...");
	    HttpSession session = ((HttpServletRequest) request).getSession();

	    MemberDTO filterSessUser = (MemberDTO) session.getAttribute("sessUser");
	    logger.info("sessUser : " + filterSessUser);

	    Cookie[] cookies = ((HttpServletRequest) request).getCookies();
	    
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("cid")) { // 쿠키 이름을 상수로 추출
	                try {
	                    String ck = cookie.getValue();
	                    MemberDTO sessUser = service.selectMember(ck); // 예제에서는 이런 메서드를 가정함

	                    if (sessUser != null) {
	                        session.setAttribute("sessUser", sessUser); // 세션에 사용자 정보 설정
	                        logger.info("Auto login successful for user: " + sessUser.getUid());
	                    }
	                } catch (Exception e) {
	                    logger.error("Error processing auto login cookie.", e);
	                }
	            }
	        }
	    }

	    chain.doFilter(request, response);
	}
}
