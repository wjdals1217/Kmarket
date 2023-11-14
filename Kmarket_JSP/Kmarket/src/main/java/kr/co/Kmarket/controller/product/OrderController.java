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

import kr.co.Kmarket.dto.OrderDTO;
import kr.co.Kmarket.dto.OrderItemDTO;
import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.seller.Cate2DTO;
import kr.co.Kmarket.service.OrderItemService;
import kr.co.Kmarket.service.OrderService;
import kr.co.Kmarket.service.ProductService;
import kr.co.Kmarket.service.seller.Cate2Service;

/* 
	날짜 : 2023/09/14
	이름 : 김무현
	내용 : Controller 기본셋팅
*/
@WebServlet("/product/order.do")
public class OrderController extends HttpServlet{
	private static final long serialVersionUID = 563678025604427289L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderService oService = new OrderService();
	
	private OrderItemService otService = new OrderItemService();
	
	private Cate2Service Ct2Service = new Cate2Service();
	
	private ProductService pService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodCate1 = req.getParameter("prodCate1");
		String prodCate2 = req.getParameter("prodCate2");
		String orduid = req.getParameter("orduid");
		
		logger.info("orduid : "+ orduid);
		
		List<OrderDTO> orders = oService.ordersSelect(orduid);
		
		logger.info("---------------------"+orders.toString());
		
		//aside 카테고리
		
		List<List<Cate2DTO>> categories = Ct2Service.selectCategories();
									
		List<ProductDTO> productsaside = pService.selectProductBest();
				
		req.setAttribute("prodCate1", prodCate1);
		req.setAttribute("prodCate2", prodCate2);
		req.setAttribute("categories", categories);
		req.setAttribute("productsaside", productsaside);
		req.setAttribute("orders", orders);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/order.jsp");
		dispatcher.forward(req, resp);
	}
	
	/**
	 *
	 */
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String newThumb1 = req.getParameter("newThumb1");
		
		String prodNo= req.getParameter("prodNo");
		String orduid =req.getParameter("orduid");
		String recipName =req.getParameter("recipName");
		String recipHp =req.getParameter("recipHp");
		String recipZip =req.getParameter("recipZip");
		String recipAddr1 =req.getParameter("recipAddr1");
		String recipAddr2 =req.getParameter("recipAddr2");
		String savePoint = req.getParameter("savePoint");
		String pName = req.getParameter("pName");
		String pDescript = req.getParameter("pDescript");
		String price = req.getParameter("price");
		String discount = req.getParameter("discount");
		String point = req.getParameter("point");
		String delivery = req.getParameter("delivery");
		String total = req.getParameter("total");
		String finalPrice = req.getParameter("final");
		String count = req.getParameter("count");
		
		
		logger.debug("prodNo : " + prodNo);
		logger.debug("orduid : " + orduid);
		logger.debug("recipName : " + recipName);
		logger.debug("recipHp : " + recipHp);
		logger.debug("recipZip : " + recipZip);
		logger.debug("recipAddr1 : " + recipAddr1);
		logger.debug("recipAddr2 : " + recipAddr2);
		logger.debug("pName : " + pName);
		logger.debug("pDescript : " + pDescript);
		logger.debug("price : " + price);
		logger.debug("discount : " + discount);
		logger.debug("point : " + point);
		logger.debug("delivery : " + delivery);
		logger.debug("total : " + total);
		logger.debug("finalPrice : " + finalPrice);
		logger.debug("count : " + count);
		
		String finalPriceWithDelivery = String.valueOf(Integer.parseInt(finalPrice) + Integer.parseInt(delivery));
		
		OrderDTO dto = new OrderDTO();
		dto.setOrdUid(orduid);
		dto.setOrdCount(count);
		dto.setOrdPrice(price);
		dto.setOrdDiscount(discount);
		dto.setOrdDelivery(delivery);
		dto.setSavePoint(point);
		dto.setUsedPoint(savePoint);
		dto.setOrdTotPrice(finalPriceWithDelivery);
		dto.setRecipName(recipName);
		dto.setRecipHp(recipHp);
		dto.setRecipZip(recipZip);
		dto.setRecipAddr1(recipAddr1);
		dto.setRecipAddr2(recipAddr2);
		//dto.setOrdStatus("success");
		//dto.setOrdPayment(1);
		//dto.setOrdComplete(2);
		//dto.setDeliveryStatus("yet");
		
		int ordNo = oService.insertOrder(dto);
		
		
		logger.info("...insertOrder......."+dto.toString());
		OrderItemDTO dto2 = new OrderItemDTO();
		dto2.setOrdNo(ordNo);
		dto2.setProdNo(prodNo);
		dto2.setCount(count);
		dto2.setPrice(price);
		dto2.setDiscount(discount);
		dto2.setPoint(point);
		dto2.setDelivery(delivery);
		dto2.setTotal(finalPriceWithDelivery);
		
		
		otService.insertOrderItem(dto2);
		
		logger.info(".............insertOrderItem......."+dto2.toString());
	
		resp.sendRedirect("/Kmarket/product/order.do?orduid="+orduid);
		
		
		}

}
