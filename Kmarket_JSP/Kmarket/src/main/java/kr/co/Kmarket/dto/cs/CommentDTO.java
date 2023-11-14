package kr.co.Kmarket.dto.cs;

public class CommentDTO {

	private int aNo;
	private int rNo;
	private String content;
	private String writer;
	private String rdate;
	
	
	
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setMaskingWriter(String writer) {
		if(writer != null && writer.length() >= 3) {
			String newLength = writer.substring(0, writer.length() - 3) ;
			
			this.writer = newLength + "***"  ;
		}else {
			this.writer = writer;

		}
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
	
	
	
	
	
}
