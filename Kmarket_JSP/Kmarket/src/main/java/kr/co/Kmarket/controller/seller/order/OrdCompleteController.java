package kr.co.Kmarket.controller.seller.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seller/order/ordComplete.do")
public class OrdCompleteController extends HttpServlet{

	private static final long serialVersionUID = 3593845057245665187L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ordNo = req.getParameter("ordNo");
		String seller = req.getParameter("seller");
		
		req.setAttribute("ordNo", ordNo);
		req.setAttribute("seller", seller);
		
		resp.sendRedirect("/seller/order/order.do?seller="+seller+"&sort=0");
	}
}
