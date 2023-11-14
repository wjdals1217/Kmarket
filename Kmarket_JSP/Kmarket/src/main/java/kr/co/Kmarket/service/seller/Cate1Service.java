package kr.co.Kmarket.service.seller;

import java.util.ArrayList;
import java.util.List;

import kr.co.Kmarket.dao.seller.Cate1DAO;
import kr.co.Kmarket.dto.seller.Cate1DTO;

public class Cate1Service {
	Cate1DAO dao = new Cate1DAO();
	
	public void insertCate1(Cate1DTO dto) {
		dao.insertCate1(dto);
	}
	public Cate1DTO selectCate1(String cate1) {
		return dao.selectCate1(cate1);
	}
	public List<Cate1DTO> selectCate1s() {
		return dao.selectCate1s();
	}
	public void updateCate1(Cate1DTO dto) {}
	public void deleteCate1(String cate1) {}
	
	
}
