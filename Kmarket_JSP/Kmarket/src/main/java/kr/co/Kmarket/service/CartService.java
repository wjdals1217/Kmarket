package kr.co.Kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dao.CartDAO;
import kr.co.Kmarket.dto.ProductCartDTO;

public class CartService {
	
	private CartDAO dao = new CartDAO();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//cart에 추가
		public void insertProductCart(ProductCartDTO dto) {
			dao.insertProductCart(dto);
			
		}
		
		public List<ProductCartDTO> selectCarts(String uid){
			return dao.selectCarts(uid);
		}
		
		public void deleteCart(String cartNo) {
			dao.deleteCart(cartNo);
		}

}
