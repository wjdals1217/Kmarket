package kr.co.Kmarket.service;


import kr.co.Kmarket.dao.OrderItemDAO;
import kr.co.Kmarket.dto.OrderItemDTO;

public class OrderItemService {
	OrderItemDAO dao = new OrderItemDAO();
	
	public void insertOrderItem (OrderItemDTO dto2) {
		dao.insertOrderItem(dto2);
	}
}
