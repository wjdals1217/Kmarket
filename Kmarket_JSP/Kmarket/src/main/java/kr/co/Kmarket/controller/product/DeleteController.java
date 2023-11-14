package kr.co.Kmarket.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.service.CartService;


@WebServlet("/product/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = -135337984009977457L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CartService cService = new CartService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid =req.getParameter("uid");
		String cartNo = req.getParameter("cartNo");
		
		logger.info("cartNo: " + cartNo);
		logger.info("uid: " + uid);
		
		cService.deleteCart(cartNo);
		
		resp.sendRedirect("/Kmarket/product/cart.do?uid="+uid);
	}
}
