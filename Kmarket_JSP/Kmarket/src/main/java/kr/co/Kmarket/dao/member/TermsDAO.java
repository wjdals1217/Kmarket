package kr.co.Kmarket.dao.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Kmarket.db.DBHelper;
import kr.co.Kmarket.dto.member.TermsDTO;

public class TermsDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String SQL = "";
	
	
	public TermsDTO selectTerms() {
		
		TermsDTO dto = new TermsDTO();
		
		SQL = "SELECT * FROM `km_member_terms`";
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			if(rs.next()) {
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
				dto.setLocation(rs.getString(3));
				dto.setFinance(rs.getString(4));
				dto.setTax(rs.getString(5));
			}
			close();
		}catch(Exception e) {
			logger.error("selectTerms error : " + e.getMessage());
		}
		
		return dto;
	}
	
}
