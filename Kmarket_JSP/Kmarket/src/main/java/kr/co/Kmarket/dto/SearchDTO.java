package kr.co.Kmarket.dto;

public class SearchDTO {
	private String search;
	private String search_text;
	private String company;
	private int sort;
	private int level;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setLevel(String level) {
		this.level = Integer.parseInt(level);
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public void setSort(String sort) {
		this.sort = Integer.parseInt(sort);
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSearch_text() {
		return search_text;
	}
	public void setSearch_text(String search_text) {
		this.search_text = search_text;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "SearchDTO [search=" + search + ", search_text=" + search_text + ", company=" + company + ", sort="
				+ sort + "]";
	}
	
	
}
