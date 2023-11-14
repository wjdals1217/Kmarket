package kr.co.Kmarket.dto.seller;

public class Cate2DTO {
	private int cate2;
	private String c2Name;
	private int cate1;
	
	//추가(김무현)
	private String c1Name;
	
	public String getC1Name() {
		return c1Name;
	}
	public void setC1Name(String c1Name) {
		this.c1Name = c1Name;
	}
	
	public int getCate2() {
		return cate2;
	}
	public void setCate2(int cate2) {
		this.cate2 = cate2;
	}
	public String getC2Name() {
		return c2Name;
	}
	public void setC2Name(String c2Name) {
		this.c2Name = c2Name;
	}
	public int getCate1() {
		return cate1;
	}
	public void setCate1(int cate1) {
		this.cate1 = cate1;
	}
	@Override
	public String toString() {
		return "Cate2DTO [cate2=" + cate2 + ", c2Name=" + c2Name + ", cate1=" + cate1 + ", c1Name=" + c1Name + "]";
	}
	
	
	
}
