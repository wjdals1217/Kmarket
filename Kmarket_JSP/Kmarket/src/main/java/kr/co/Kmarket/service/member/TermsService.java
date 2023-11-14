package kr.co.Kmarket.service.member;

import kr.co.Kmarket.dao.member.TermsDAO;
import kr.co.Kmarket.dto.member.TermsDTO;

public class TermsService {

	private TermsDAO dao = new TermsDAO();
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
	
}
