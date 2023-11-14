package kr.co.Kmarket.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.db.DBHelper;
import kr.co.Kmarket.dto.OrderItemDTO;

public class OrderItemDAO extends DBHelper{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String sql;
	
	
	public void insertOrderItem (OrderItemDTO dto2) {
		conn= getConnection();
		try {
			sql="INSERT INTO `km_product_order_item` SET "
					+ "`ordNo`=?,"
					+ "`prodNo`=?,"
					+ "`count`=?,"
					+ "`price`=?,"
					+ "`discount`=?,"
					+ "`point`=?,"
					+ "`delivery`=?,"
					+ "`total`=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto2.getOrdNo());
			psmt.setInt(2, dto2.getProdNo());
			psmt.setInt(3, dto2.getCount());
			psmt.setInt(4, dto2.getPrice());
			psmt.setInt(5, dto2.getDiscount());
			psmt.setInt(6, dto2.getPoint());
			psmt.setInt(7, dto2.getDelivery());
			psmt.setInt(8, dto2.getTotal());
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			logger.error("insertOrderItem : "+e.getMessage());
		}
	}
}
