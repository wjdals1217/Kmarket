package kr.co.Kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.db.DBHelper;
import kr.co.Kmarket.dto.OrderDTO;
import kr.co.Kmarket.dto.OrderItemDTO;
import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.SearchDTO;

public class OrderDAO extends DBHelper{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String sql;
	public int insertOrder (OrderDTO dto) {
		int ordNo=0;
		conn = getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql="INSERT INTO `km_product_order` SET "
					+ "`ordUid`=?,"
					+ "`ordCount`=?,"
					+ "`ordPrice`=?,"
					+ "`ordDiscount`=?,"
					+ "`ordDelivery`=?,"
					+ "`savePoint`=?,"
					+ "`usedPoint`=?,"
					+ "`ordTotPrice`=?,"
					+ "`recipName`=?,"
					+ "`recipHp`=?,"
					+ "`recipZip`=?,"
					+ "`recipAddr1`=?,"
					+ "`recipAddr2`=?,"
					+ "`ordStatus`='success',"
					+ "`ordPayment`=2,"
					+ "`ordComplete`=1,"
					+ "`deliveryStatus`='yet',"
					+ "`ordDate`=NOW()";
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getOrdUid());
			psmt.setInt(2, dto.getOrdCount());
			psmt.setInt(3, dto.getOrdPrice());
			psmt.setInt(4, dto.getOrdDiscount());
			psmt.setInt(5, dto.getOrdDelivery());
			psmt.setInt(6, dto.getSavePoint());
			psmt.setInt(7, dto.getUsedPoint());
			psmt.setInt(8, dto.getOrdTotPrice());
			psmt.setString(9, dto.getRecipName());
			psmt.setString(10, dto.getRecipHp());
			psmt.setString(11, dto.getRecipZip());
			psmt.setString(12, dto.getRecipAddr1());
			psmt.setString(13, dto.getRecipAddr2());
			//psmt.setString(14, dto.getOrdStatus());
			//psmt.setInt(15, dto.getOrdPayment());
			//psmt.setInt(16, dto.getOrdComplete());
			//psmt.setString(17, dto.getDeliveryStatus());
			psmt.executeUpdate();
			rs = stmt.executeQuery("SELECT MAX(`ordNo`) FROM `km_product_order`");
			conn.commit();
			if(rs.next()) {
				ordNo = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("insertOrder error : "+e.getMessage());
		}
		return ordNo;
		
	}
	public OrderDTO selectOrder (String ordNo) {
		return null;
	}
	public List<OrderDTO> selectOrders (int start, SearchDTO searchDTO) {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		sql = "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
			+ "FROM `km_product_order_item` AS a "
			+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
			+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
			+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
			+ "WHERE d.`company`=? "
			+ "ORDER BY b.`ordNo` DESC LIMIT ?, 10";
		String sql_sort1 = "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.`uid` "
						+ "WHERE d.`company`=? AND b.`ordComplete`=1 "
						+ "ORDER BY b.`ordNo` DESC LIMIT ?, 10";
		String sql_sort2 = "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? "
						+ "ORDER BY c.`sold` DESC LIMIT ?, 10";
		String sql_sort3 = "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? AND b.`ordStatus`='cancel' "
						+ "ORDER BY b.`ordNo` DESC LIMIT ?, 10";
		String sql_sort4 = "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? AND b.`ordStatus`='return' "
						+ "ORDER BY b.`ordNo` DESC LIMIT ?, 10";
		String sql_sort5 = "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? AND b.`ordStatus`='exchange' "
						+ "ORDER BY b.`ordNo` DESC LIMIT ?, 10";
		String sql_search1 =  "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
								+ "FROM `km_product_order_item` AS a "
								+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
								+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
								+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
								+ "WHERE d.`company`=? AND c.`prodName` LIKE ? "
								+ "ORDER BY b.`ordNo` DESC LIMIT ?, 10";
		String sql_search2 =  "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
									+ "FROM `km_product_order_item` AS a "
									+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
									+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
									+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
									+ "WHERE d.`company`=? AND a.`prodNo` LIKE ? "
									+ "ORDER BY b.`ordNo` DESC LIMIT ?, 10";
		String sql_search3 =  "SELECT a.`prodNo`, b.*, c.`prodCate1`, c.`prodCate2`, c.`newThumb1`, c.`prodName` "
									+ "FROM `km_product_order_item` AS a "
									+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
									+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
									+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
									+ "WHERE d.`company`=? AND b.`ordNo` LIKE ? "
									+ "ORDER BY b.`ordNo` DESC LIMIT ?, 10";
		conn = getConnection();
		try {
			if(searchDTO.getSearch() == null || searchDTO.getSearch().equals("")) {
				if(searchDTO.getSort() == 0) {
					logger.debug("sort : "+searchDTO.getSort());
					psmt = conn.prepareStatement(sql);
					logger.debug("psmt 생성...");
				}else if(searchDTO.getSort() == 1) {
					psmt = conn.prepareStatement(sql_sort1);
				}else if(searchDTO.getSort() == 2) {
					psmt = conn.prepareStatement(sql_sort2);
				}else if(searchDTO.getSort() == 3) {
					psmt = conn.prepareStatement(sql_sort3);
				}else if(searchDTO.getSort() == 4) {
					psmt = conn.prepareStatement(sql_sort4);
				}else if(searchDTO.getSort() == 5) {
					psmt = conn.prepareStatement(sql_sort5);
				}
				psmt.setString(1, searchDTO.getCompany());
				psmt.setInt(2, start);
				logger.debug("psmt 정보 get...");
				logger.debug("searchDTO.getCompany() : "+searchDTO.getCompany());
				logger.debug("start: "+start);
				
			}else {
				if(searchDTO.getSearch().equals("search1")) {
					logger.debug("search1...상품명");;
					psmt = conn.prepareStatement(sql_search1);
					logger.debug("psmt 생성...상품명");
				}else if(searchDTO.getSearch().equals("search2")) {
					logger.debug("search2...상품코드");
					psmt = conn.prepareStatement(sql_search2);
					logger.debug("search2...psmt 생성");
				}else if(searchDTO.getSearch().equals("search3")) {
					psmt = conn.prepareStatement(sql_search3);
				}
				psmt.setString(1, searchDTO.getCompany());
				psmt.setString(2, "%"+searchDTO.getSearch_text()+"%");
				psmt.setInt(3, start);
				logger.debug("searchDTO.getCompany : "+searchDTO.getCompany());
				logger.debug("searchDTO.getSearch_text() : "+searchDTO.getSearch_text());
				logger.debug("start : "+start);
			}
			rs = psmt.executeQuery();
			logger.debug("rs 생성...");
			while(rs.next()) {
				logger.debug("rs while문 시작...");
				OrderDTO dto = new OrderDTO();
				OrderItemDTO orderItemDTO = new OrderItemDTO();
				ProductDTO productDTO = new ProductDTO();
				orderItemDTO.setProdNo(rs.getInt(1));
				dto.setOrdNo(rs.getInt(2));
				dto.setOrdUid(rs.getString(3));
				dto.setOrdCount(rs.getInt(4));
				dto.setOrdPrice(rs.getInt(5));
				dto.setOrdDiscount(rs.getInt(6));
				dto.setOrdDelivery(rs.getInt(7));
				dto.setSavePoint(rs.getInt(8));
				dto.setUsedPoint(rs.getInt(9));
				dto.setOrdTotPrice(rs.getInt(10));
				dto.setRecipName(rs.getString(11));
				dto.setRecipHp(rs.getString(12));
				dto.setRecipZip(rs.getString(13));
				dto.setRecipAddr1(rs.getString(14));
				dto.setRecipAddr2(rs.getString(15));
				dto.setOrdStatus(rs.getString(16));
				dto.setOrdPayment(rs.getInt(17));
				dto.setOrdComplete(rs.getInt(18));
				dto.setDeliveryStatus(rs.getString(19));
				dto.setOrdDate(rs.getString(20));
				productDTO.setProdCate1(rs.getInt(21));
				productDTO.setProdCate2(rs.getInt(22));
				productDTO.setNewThumb1(rs.getString(23));
				productDTO.setProdName(rs.getString(24));
				dto.setProductDTO(productDTO);
				dto.setOrderItemDTO(orderItemDTO);
				orders.add(dto);
				logger.debug("orders 추가...");
			}
			close();
		} catch (Exception e) {
			logger.error("selectOrders error : "+e.getMessage());
		}
		return orders;
	}
	public void updateOrder (OrderDTO dto) {
		
	}
	public void deleteOrder (String ordNo) {
		
	}
	public int selectCountTotal(SearchDTO dto) {
		int total = 0;
		sql = "SELECT COUNT(*) "
				+ "FROM `km_product_order_item` AS a "
				+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
				+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
				+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
				+ "WHERE d.`company`=? ";
		String sql_sort1 = "SELECT COUNT(*) "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? AND b.`ordComplete`=1 ";
		String sql_sort2 = "SELECT COUNT(*) "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? "
						+ "ORDER BY c.`sold`";
		String sql_sort3 = "SELECT COUNT(*) "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? AND b.`ordStatus`='cancel' ";
		String sql_sort4 = "SELECT COUNT(*) "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? AND b.`ordStatus`='return' ";
		String sql_sort5 = "SELECT COUNT(*) "
						+ "FROM `km_product_order_item` AS a "
						+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
						+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
						+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
						+ "WHERE d.`company`=? AND b.`ordStatus`='exchange' ";
		String sql_search1 =  "SELECT COUNT(*) "
									+ "FROM `km_product_order_item` AS a "
									+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
									+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
									+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
									+ "WHERE d.`company`=? AND c.`prodName` LIKE ? ";
	
		String sql_search2 ="SELECT COUNT(*) "
									+ "FROM `km_product_order_item` AS a "
									+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
									+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
									+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
									+ "WHERE d.`company`=? AND a.`prodNo` LIKE ? ";
		
		String sql_search3 = "SELECT COUNT(*)  "
									+ "FROM `km_product_order_item` AS a "
									+ "JOIN `km_product_order` AS b ON a.`ordNo`=b.`ordNo` "
									+ "JOIN `km_product` AS c ON a.`prodNo`= c.`prodNo` "
									+ "JOIN `km_member` AS d ON c.`seller`=d.uid "
									+ "WHERE d.`company`=? AND b.`ordNo` LIKE ? ";
		conn = getConnection();
		try {
			if(dto.getSearch() == null || dto.getSearch().equals("")) {
				if(dto.getSort() == 0) {
					psmt = conn.prepareStatement(sql);
				}else if(dto.getSort() == 1) {
					psmt = conn.prepareStatement(sql_sort1);
				}else if(dto.getSort() == 2){
					psmt = conn.prepareStatement(sql_sort2);
				}else if(dto.getSort() == 3){
					psmt = conn.prepareStatement(sql_sort3);
				}else if(dto.getSort() == 4){
					psmt = conn.prepareStatement(sql_sort4);
				}else if(dto.getSort() == 5){
					psmt = conn.prepareStatement(sql_sort5);
				}
				psmt.setString(1, dto.getCompany());
			}else {
				if(dto.getSearch().equals("search1")) {
					psmt = conn.prepareStatement(sql_search1);
				}else if(dto.getSearch().equals("search2")) {
					psmt = conn.prepareStatement(sql_search2);
				}else if(dto.getSearch().equals("search3")) {
					psmt = conn.prepareStatement(sql_search3);
				}
				psmt.setString(1, dto.getCompany());
				psmt.setString(2, "%"+dto.getSearch_text()+"%");
			}
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCountTotal : "+e.getMessage());
		}
		return total;
	}
	
	//추가
	public List<OrderDTO> ordersSelect(String orduid) {
		List<OrderDTO> orders = new ArrayList<>();
		conn = getConnection();
		try {
			sql= "SELECT * FROM `km_product_order` AS a JOIN `km_product_order_item` AS b ON a.`ordNo` = b.`ordNo` "
					+ "JOIN `km_product` AS c ON b.`prodNo`= c.`prodNo` "
					+ "WHERE `ordUid`=?";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, orduid);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setOrdNo(rs.getInt("ordNo"));
				dto.setOrdUid(rs.getString("ordUid"));
				dto.setOrdCount(rs.getInt("ordCount"));
				dto.setOrdPrice(rs.getInt("ordPrice"));
				dto.setOrdDiscount(rs.getInt("ordDiscount"));
				dto.setOrdDelivery(rs.getInt("ordDelivery"));
				dto.setSavePoint(rs.getInt("savePoint"));
				dto.setUsedPoint(rs.getInt("usedPoint"));
				dto.setOrdTotPrice(rs.getInt("ordTotPrice"));
				dto.setRecipName(rs.getString("recipName"));
				dto.setRecipHp(rs.getString("recipHp"));
				dto.setRecipZip(rs.getString("recipZip"));
				dto.setRecipAddr1(rs.getString("recipAddr1"));
				dto.setRecipAddr2(rs.getString("recipAddr2"));
				dto.setOrdStatus(rs.getString("ordStatus"));
				dto.setOrdPayment(rs.getInt("ordPayment"));
				dto.setOrdComplete(rs.getInt("ordComplete"));
				dto.setDeliveryStatus(rs.getString("deliveryStatus"));
				dto.setOrdDate(rs.getString("ordDate"));
				dto.setProdCate1(rs.getInt("prodCate1"));
				dto.setProdCate2(rs.getInt("prodCate2"));
				dto.setNewThumb1(rs.getString("newThumb1"));
				dto.setProdName(rs.getString("prodName"));
				dto.setDescript(rs.getString("descript"));
				orders.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("ordersSelect : "+e.getMessage());
		}
		return orders;
		
	}
	
}
