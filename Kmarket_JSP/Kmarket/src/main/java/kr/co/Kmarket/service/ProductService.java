package kr.co.Kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.dao.ProductDAO;
import kr.co.Kmarket.dto.ProductDTO;
import kr.co.Kmarket.dto.SearchDTO;

public class ProductService {
	private ProductDAO dao = new ProductDAO();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	public ProductDTO selectProduct(String prodNo) {
		return dao.selectProduct(prodNo);
	}
	public List<ProductDTO> selectProducts(int start, SearchDTO search) {
		return dao.selectProducts(start, search);
	}
	public List<ProductDTO> selectProductsAll(int start, SearchDTO search) {
		return dao.selectProductsAll(start, search);
	}
	public ProductDTO selectImages(String prodNo) {
		logger.debug(prodNo);
		return dao.selectImages(prodNo);
	}
	public void updateProduct(ProductDTO dto) {
		dao.updateProduct(dto);
	}
	public void deleteProduct(String prodNo) {
		dao.deleteProduct(prodNo);
	}
	public int selectCountTotal(SearchDTO dto) {
		logger.debug("dto : "+dto);
		return dao.selectCountTotal(dto);
		
	}
	public int selectCountAll(SearchDTO dto) {
		logger.debug("dto : "+dto);
		return dao.selectCountAll(dto);
		
	}
	
	//추가 무현 베스트 상품 5개
	public List<ProductDTO> selectProductBest (){
		return dao.selectProductsBest();
	}
	
	//추가 무현 히트상품 8개 출력
	public List<ProductDTO> selectProductHit(){
		return dao.selectProductsHit();
	}
	
	//추가 무현 추천상품 8개 출력
	public List<ProductDTO> selectProductsScore(){
		return dao.selectProductsScore();
	}
	
	//추가 무현 추천상품 8개 출력
	public List<ProductDTO> selectProductsRdate(){
		return dao.selectProductsRdate();
	}
	
	//추가 무현 할인상품 8개 출력
	public List<ProductDTO> selectProductsDiscount(){
		return dao.selectProductsDiscount();
	}
	
	//추가 무현 카테고리1별 상품전체 출력
	public List<ProductDTO> selectProductsAll(String prodCate1, String prodCate2, int start , String type){
		return dao.selectProductsAll(prodCate1, prodCate2, start, type);
	}
	//게시판 총 갯수 카운트
	public int selectCountTotalProdCate(String prodCate1, String prodCate2) {
		return dao.selectCountTotalProdCate(prodCate1, prodCate2);
	}
	
	
}
