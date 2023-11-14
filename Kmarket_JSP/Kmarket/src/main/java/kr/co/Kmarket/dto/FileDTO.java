package kr.co.Kmarket.dto;

/*
 * 날짜 : 2023-09-14
 * 이름 : 최정민
 * 내용 : 파일 업로드용 파일 DTO
 */

public class FileDTO {

	private int fno;
	private int ano;
	private String oriname;
	private String newname;
	private String rdate;
	
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	
}
