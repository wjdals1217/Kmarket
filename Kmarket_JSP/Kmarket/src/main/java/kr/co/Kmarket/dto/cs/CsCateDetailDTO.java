package kr.co.Kmarket.dto.cs;

import java.util.List;

public class CsCateDetailDTO {

	private int type;
	private String dName;
	private String aeName;
	
	private List<CsArticleDTO> dto;
	public List<CsArticleDTO> getDto() {
		return dto;
	}
	public void setDto(List<CsArticleDTO> dto) {
		this.dto = dto;
	}
	
	public String getAeName() {
		return aeName;
	}
	public void setAeName(String aeName) {
		this.aeName = aeName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setType(String type) {
		this.type = Integer.parseInt(type);
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	
	@Override
	public String toString() {
		return "CsCateDetailDTO [aeName=" + aeName + ", type=" + type + ", dName=" + dName + "]";
	}
	
	
}
