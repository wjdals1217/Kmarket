package kr.co.Kmarket.dto;

public class OrderDTO {
	 private int ordNo;
	 private String ordUid;
	 private int ordCount;
	 private int ordPrice;
	 private int ordDiscount;
	 private int ordDelivery;
	 private int savePoint;
	 private int usedPoint;
	 private int ordTotPrice;
	 private String recipName;
	 private String recipHp;
	 private String recipZip;
	 private String recipAddr1;
	 private String recipAddr2;
	 private String ordStatus;
	 private int ordPayment;
	 private int ordComplete;
	 private String deliveryStatus;
	 private String ordDate;
	 private int prodCate1;
	 private int prodCate2;
	 private String newThumb1;
	 private String prodName;
	 private String descript;
	 private OrderItemDTO orderItemDTO = new OrderItemDTO();
	 private ProductDTO productDTO = new ProductDTO();
	 
	 
	 
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public OrderItemDTO getOrderItemDTO() {
		return orderItemDTO;
	}
	public void setOrderItemDTO(OrderItemDTO orderItemDTO) {
		this.orderItemDTO = orderItemDTO;
	}
	public int getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}
	public String getOrdUid() {
		return ordUid;
	}
	public void setOrdUid(String ordUid) {
		this.ordUid = ordUid;
	}
	public int getOrdCount() {
		return ordCount;
	}
	public void setOrdCount(int ordCount) {
		this.ordCount = ordCount;
	}
	public void setOrdCount(String ordCount) {
		this.ordCount = Integer.parseInt(ordCount);
	}
	public int getOrdPrice() {
		return ordPrice;
	}
	public void setOrdPrice(int ordPrice) {
		this.ordPrice = ordPrice;
	}
	public void setOrdPrice(String ordPrice) {
		this.ordPrice = Integer.parseInt(ordPrice);
	}
	public int getOrdDiscount() {
		return ordDiscount;
	}
	public void setOrdDiscount(int ordDiscount) {
		this.ordDiscount = ordDiscount;
	}
	public void setOrdDiscount(String ordDiscount) {
		this.ordDiscount = Integer.parseInt(ordDiscount);
	}
	public int getOrdDelivery() {
		return ordDelivery;
	}
	public void setOrdDelivery(int ordDelivery) {
		this.ordDelivery = ordDelivery;
	}
	public void setOrdDelivery(String ordDelivery) {
		this.ordDelivery = Integer.parseInt(ordDelivery);
	}
	public int getSavePoint() {
		return savePoint;
	}
	public void setSavePoint(int savePoint) {
		this.savePoint = savePoint;
	}
	public void setSavePoint(String savePoint) {
		this.savePoint = Integer.parseInt(savePoint);
	}
	public int getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}
	public void setUsedPoint(String usedPoint) {
		this.usedPoint = Integer.parseInt(usedPoint);
	}
	public int getOrdTotPrice() {
		return ordTotPrice;
	}
	public void setOrdTotPrice(int ordTotPrice) {
		this.ordTotPrice = ordTotPrice;
	}
	public void setOrdTotPrice(String ordTotPrice) {
		this.ordTotPrice = Integer.parseInt(ordTotPrice);
	}
	public String getRecipName() {
		return recipName;
	}
	public void setRecipName(String recipName) {
		this.recipName = recipName;
	}
	public String getRecipHp() {
		return recipHp;
	}
	public void setRecipHp(String recipHp) {
		this.recipHp = recipHp;
	}
	public String getRecipZip() {
		return recipZip;
	}
	public void setRecipZip(String recipZip) {
		this.recipZip = recipZip;
	}
	public String getRecipAddr1() {
		return recipAddr1;
	}
	public void setRecipAddr1(String recipAddr1) {
		this.recipAddr1 = recipAddr1;
	}
	public String getRecipAddr2() {
		return recipAddr2;
	}
	public void setRecipAddr2(String recipAddr2) {
		this.recipAddr2 = recipAddr2;
	}
	public int getOrdPayment() {
		return ordPayment;
	}
	public void setOrdPayment(int ordPayment) {
		this.ordPayment = ordPayment;
	}
	public int getOrdComplete() {
		return ordComplete;
	}
	public void setOrdComplete(int ordComplete) {
		this.ordComplete = ordComplete;
	}
	public String getFullOrdDate() {
		return ordDate;
	}
	public String getOrdDate() {
		return ordDate.substring(2, 11);
	}
	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}
	
	public int getProdCate1() {
		return prodCate1;
	}
	public void setProdCate1(int prodCate1) {
		this.prodCate1 = prodCate1;
	}
	public int getProdCate2() {
		return prodCate2;
	}
	public void setProdCate2(int prodCate2) {
		this.prodCate2 = prodCate2;
	}
	
	public void setNewThumb1(String newThumb1) {
		this.newThumb1 = newThumb1;
	}
	public String getNewThumb1() {
		return newThumb1;
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
	
	public String getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	@Override
	public String toString() {
		return "OrderDTO [ordNo=" + ordNo + ", ordUid=" + ordUid + ", ordCount=" + ordCount + ", ordPrice=" + ordPrice
				+ ", ordDiscount=" + ordDiscount + ", ordDelivery=" + ordDelivery + ", savePoint=" + savePoint
				+ ", usedPoint=" + usedPoint + ", ordTotPrice=" + ordTotPrice + ", recipName=" + recipName
				+ ", recipHp=" + recipHp + ", recipZip=" + recipZip + ", recipAddr1=" + recipAddr1 + ", recipAddr2="
				+ recipAddr2 + ", ordStatus=" + ordStatus + ", ordPayment=" + ordPayment + ", ordComplete="
				+ ordComplete + ", deliveryStatus=" + deliveryStatus + ", ordDate=" + ordDate + ", prodCate1="
				+ prodCate1 + ", prodCate2=" + prodCate2 + ", newThumb1=" + newThumb1 + ", prodName=" + prodName
				+ ", descript=" + descript + ", orderItemDTO=" + orderItemDTO + ", productDTO=" + productDTO + "]";
	}
	
	
	
	
	

	
	 
	 
}
