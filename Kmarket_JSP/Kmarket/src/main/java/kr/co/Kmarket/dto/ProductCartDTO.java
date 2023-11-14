package kr.co.Kmarket.dto;

public class ProductCartDTO {
	
	private int cartNo;
	private String uid;
	private int ProdNo;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int finalPrice;
	private String rdate;
	
	//추가필드 
	
	private int prodCate1;
	private int prodCate2;
	private String prodName;
	private String descript;
	private String newThumb1;
	
	public int getProdCate1() {
		return prodCate1;
	}
	public void setProdCate1(int prodCate1) {
		this.prodCate1 = prodCate1;
	}
	
	public void setProdCate1(String prodCate1) {
		this.prodCate1 = Integer.parseInt(prodCate1);
	}
	public int getProdCate2() {
		return prodCate2;
	}
	public void setProdCate2(int prodCate2) {
		this.prodCate2 = prodCate2;
	}
	public void setProdCate2(String prodCate2) {
		this.prodCate2 = Integer.parseInt(prodCate2);
	}
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	public String getNewThumb1() {
		return newThumb1;
	}
	public void setNewThumb1(String newThumb1) {
		this.newThumb1 = newThumb1;
	}
	
	
	
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getProdNo() {
		return ProdNo;
	}
	public void setProdNo(int prodNo) {
		ProdNo = prodNo;
	}
	public void setProdNo(String prodNo) {
		ProdNo = Integer.parseInt(prodNo);
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public void setDiscount(String discount) {
		this.discount = Integer.parseInt(discount);
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setPoint(String point) {
		this.point = Integer.parseInt(point);
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = Integer.parseInt(delivery);
	}
	
	public int getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}
	public void setFinalPrice(String finalPrice) {
		this.finalPrice = Integer.parseInt(finalPrice);
	}

	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	@Override
	public String toString() {
		return "ProductCartDTO [cartNo=" + cartNo + ", uid=" + uid + ", ProdNo=" + ProdNo + ", count=" + count
				+ ", price=" + price + ", discount=" + discount + ", point=" + point + ", delivery=" + delivery
				+ ", finalPrice=" + finalPrice + ", rdate=" + rdate + ", prodCate1=" + prodCate1 + ", prodCate2="
				+ prodCate2 + "]";
	}
	
	
	
	
	
	
	

}
