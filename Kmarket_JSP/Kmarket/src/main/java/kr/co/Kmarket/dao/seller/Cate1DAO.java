package kr.co.Kmarket.dao.seller;

/*
 * 날짜 : 2023-09-14
 * 이름 : 최정민
 * 내용 : cate1 조회DAO 생성
 */

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.db.DBHelper;
import kr.co.Kmarket.dto.seller.Cate1DTO;

public class Cate1DAO extends DBHelper{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String sql = "";
	
	public void insertCate1(Cate1DTO dto) {
		conn = getConnection();
		try {
			sql = "INSERT INTO `km_product_cate1` SET `c1Name`=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getC1Name());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertCate1 error : "+e.getMessage());
		}
	}
	public Cate1DTO selectCate1(String cate1) {
		return null;
	}
	public List<Cate1DTO> selectCate1s() {
		List<Cate1DTO> cate1s = new ArrayList<Cate1DTO>(); 
		conn = getConnection();
		try {
			sql ="SELECT * FROM `km_product_cate1`";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Cate1DTO dto = new Cate1DTO();
				dto.setCate1(rs.getInt(1));
				dto.setC1Name(rs.getString(2));
				cate1s.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCate1 error : "+e.getMessage());
		}
		return cate1s;
	}
	public void updateCate1(Cate1DTO dto) {}
	public void deleteCate1(String cate1) {}
	
	
}
