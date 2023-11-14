package kr.co.Kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.db.DBHelper;
import kr.co.Kmarket.dto.ProductCartDTO;

public class CartDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String sql = "";
	
	public void insertProductCart (ProductCartDTO dto) {
		conn = getConnection();
		try {
			
			sql ="INSERT INTO `km_product_cart` SET "
					+ "`uid`=?,"
					+ "`prodNo`=?,"
					+ "`count`=?,"
					+ "`price`=?,"
					+ "`discount`=?,"
					+ "`point`=?,"
					+ "`delivery`=?,"
					+ "`finalPrice`=?,"
					+ "`rdate`=NOW()";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUid());
			psmt.setInt(2, dto.getProdNo());
			psmt.setInt(3, dto.getCount());
			psmt.setInt(4, dto.getPrice());
			psmt.setInt(5, dto.getDiscount());
			psmt.setInt(6, dto.getPoint());
			psmt.setInt(7, dto.getDelivery());
			psmt.setInt(8, dto.getFinalPrice());
			psmt.executeUpdate();
			
			close();
			
			
		} catch (Exception e) {
			logger.error("insertProductCart error : "+e.getMessage());
		}
		
	}
	
	public List<ProductCartDTO> selectCarts(String uid) {
		List<ProductCartDTO> carts = new ArrayList<>();
			conn = getConnection();
		try {
			
			sql = "SELECT * FROM `km_product_cart` AS a JOIN `km_product` AS b ON a.`prodNo` = b.`prodNo` WHERE `uid`=?";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductCartDTO dto = new ProductCartDTO();
				dto.setCartNo(rs.getInt("cartNo"));
				dto.setUid(rs.getString("uid"));
				dto.setProdNo(rs.getInt("prodNo"));
				dto.setCount(rs.getInt("count"));
				dto.setPrice(rs.getInt("price"));
				dto.setDiscount(rs.getInt("discount"));
				dto.setPoint(rs.getInt("point"));
				dto.setDelivery(rs.getInt("delivery"));
				dto.setFinalPrice(rs.getInt("finalPrice"));
				dto.setProdCate1(rs.getInt("prodCate1"));
				dto.setProdCate2(rs.getInt("prodCate2"));
				dto.setNewThumb1(rs.getString("newThumb1"));
				dto.setProdName(rs.getString("prodName"));
				dto.setDescript(rs.getString("descript"));
				carts.add(dto);
				
			}
			close();
		} catch (Exception e) {
			logger.error("selectCarts error : "+e.getMessage());
		}
		return carts;
	}
	
	public void deleteCart(String cartNo) {
		
		try {
			sql= "DELETE FROM `km_product_cart` WHERE `cartNo`=?";
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cartNo);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("deleteCart error : "+e.getMessage());
		}
	}
	
}
