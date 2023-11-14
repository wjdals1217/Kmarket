package kr.co.Kmarket.dao.seller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.db.DBHelper;
import kr.co.Kmarket.dto.seller.Cate2DTO;

public class Cate2DAO extends DBHelper{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String sql = "";
	
	public void insertCate2(Cate2DTO dto) {}
	public Cate2DTO selectCate2(String cate1) {
		return null;
	}
	public List<Cate2DTO> selectCate2s(String cate1) {
		List<Cate2DTO> cate2s = new ArrayList<Cate2DTO>();
		conn = getConnection();
		try {
			sql= "SELECT * FROM `km_product_cate2` WHERE `cate1`=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cate1);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Cate2DTO dto = new Cate2DTO();
				dto.setCate2(rs.getInt(1));
				dto.setC2Name(rs.getString(2));
				dto.setCate1(rs.getInt(3));
				cate2s.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCate2 error : "+e.getMessage());
		}
		return cate2s;
	}
	
	
	//카테고리 리스트안에 리스트 (김무현)
	public List<List<Cate2DTO>> selectCategories() {
		
		List<List<Cate2DTO>> categories = new ArrayList<>();
		
		conn = getConnection();
		try {
			sql = "SELECT * FROM `km_product_cate1` AS a "
					+ "JOIN `km_product_cate2` AS b "
					+ "ON a.cate1 = b.cate1;";
			
			psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = psmt.executeQuery();
			
			List<Cate2DTO> cate2s = new ArrayList<>();
	        int currentCate1 = 0;

	        while (rs.next()) {
	            int cate1 = rs.getInt(1);
	            
	            if (currentCate1 != cate1) {
	                if (!cate2s.isEmpty()) {
	                    categories.add(cate2s);
	                }
	                currentCate1 = cate1;
	                cate2s = new ArrayList<>();
	            }

	            Cate2DTO dto = new Cate2DTO();
	            dto.setCate1(cate1);
	            dto.setC1Name(rs.getString(2));
	            dto.setCate2(rs.getInt(3));
	            dto.setC2Name(rs.getString(4));
	            cate2s.add(dto);
	        }

	       
	        if (!cate2s.isEmpty()) {
	            categories.add(cate2s);
	        }
	        close();
	    } catch (Exception e) {
	        logger.error("selectCategories error: " + e.getMessage());
	    }

	    return categories;
	}
	
	public void updateCate2(Cate2DTO dto) {}
	public void deleteCate2(String cate1) {}
}
