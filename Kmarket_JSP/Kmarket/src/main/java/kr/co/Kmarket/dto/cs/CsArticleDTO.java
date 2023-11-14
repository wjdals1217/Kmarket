package kr.co.Kmarket.dto.cs;

import kr.co.Kmarket.dto.FileDTO;

public class CsArticleDTO {

	private int aNo;
	private String group;
	private String cateDetail;
	private String title;
	private String content;
	private int file;
	private String writer;
	private String regip;
	private String rdate;
	private int type;
	private int uLevel;
	private String dName;
	private int aStatus;
	
	
	private FileDTO fileDto;
	
	
	
	
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
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
	public int getuLevel() {
		return uLevel;
	}
	public void setuLevel(int uLevel) {
		this.uLevel = uLevel;
	}
	public FileDTO getFileDto() {
		return fileDto;
	}
	public void setFileDto(FileDTO fileDto) {
		this.fileDto = fileDto;
	}
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}

	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getCateDetail() {
		return cateDetail;
	}
	public void setCateDetail(String cateDetail) {
		this.cateDetail = cateDetail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	public void setFile(String oriName) {
		
		if(oriName != null) {
			this.file = 1;	
		}else {
			this.file = 0;
		}
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setMaskingWriter(String writer) {
		if(writer != null && writer.length() >= 3) {
			String newLength = writer.substring(0, writer.length() - 2) ;
			
			this.writer = newLength + "**"  ;
		}else {
			this.writer = writer;

		}
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public void setRdateYYMMDD(String rdate) {
		this.rdate = rdate.substring(2, 10);
	}

	public int getdType() {
		return type;
	}
	public void setdType(int type) {
		this.type = type;
	}

	public int getaStatus() {
		return aStatus;
	}
	public void setaStatus(int aStatus) {
		this.aStatus = aStatus;
	}
	
	@Override
	public String toString() {
		return "CsArticleDTO [aNo=" + aNo + ", group=" + group + ", cateDetail=" + cateDetail + ", title=" + title
				+ ", content=" + content + ", file=" + file + ", writer=" + writer + ", regip=" + regip + ", rdate="
				+ rdate + ", type=" + type + ", uLevel=" + uLevel + ", dName=" + dName + ", aStatus=" + aStatus
				+ ", fileDto=" + fileDto + "]";
	}


	
}
