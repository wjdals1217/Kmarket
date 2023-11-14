package kr.co.Kmarket.service;

import java.util.List;

import kr.co.Kmarket.dao.OrderDAO;
import kr.co.Kmarket.dto.OrderDTO;
import kr.co.Kmarket.dto.OrderItemDTO;
import kr.co.Kmarket.dto.SearchDTO;

public class OrderService {
	OrderDAO dao = new OrderDAO();
	
	public int insertOrder (OrderDTO dto) {
		return dao.insertOrder(dto);
	}
	public OrderDTO selectOrder (String ordNo) {
		return dao.selectOrder(ordNo);
	}
	public List<OrderDTO> selectOrders (int start, SearchDTO searchDTO) {
		return dao.selectOrders(start, searchDTO);
	}
	public void updateOrder (OrderDTO dto) {
		dao.updateOrder(dto);
	}
	public void deleteOrder (String ordNo) {
		dao.deleteOrder(ordNo);
	}
	public int selectCountTotal(SearchDTO dto) {
		return dao.selectCountTotal(dto);
	}
	
	//추가 
	
	public List<OrderDTO> ordersSelect (String orduid){
		return dao.ordersSelect(orduid);
	}
	

}
