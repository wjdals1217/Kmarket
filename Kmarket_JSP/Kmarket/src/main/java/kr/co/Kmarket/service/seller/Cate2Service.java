package kr.co.Kmarket.service.seller;

import java.util.List;

import kr.co.Kmarket.dao.seller.Cate2DAO;
import kr.co.Kmarket.dto.seller.Cate2DTO;

public class Cate2Service {

	private Cate2DAO dao = new Cate2DAO();
	
	public void insertCate2(Cate2DTO dto) {
		dao.insertCate2(dto);
	}
	public Cate2DTO selectCate2(String cate1) {
		return dao.selectCate2(cate1);
	}
	public List<Cate2DTO> selectCate2s(String cate1) {
		return dao.selectCate2s(cate1);
	}
	public void updateCate2(Cate2DTO dto) {
		dao.updateCate2(dto);
	}
	public void deleteCate2(String cate1) {
		dao.deleteCate2(cate1);
	}
	
	//추가 index 카테고리 출력 (김무현)
	public List<List<Cate2DTO>> selectCategories() {
		return dao.selectCategories();
	}
	
}
